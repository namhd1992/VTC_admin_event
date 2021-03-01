package com.vtc.gamerid.gateway.luckyspin.facade;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.vtc.gamerid.gateway.annotations.PermissionService;
import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.dao.entity.LuckySpin;
import com.vtc.gamerid.gateway.common.dao.entity.TblTopupCardHistory;
import com.vtc.gamerid.gateway.common.dao.entity.UserGameRID;
import com.vtc.gamerid.gateway.common.dao.repository.AdminReportSpinRepository;
import com.vtc.gamerid.gateway.common.dao.repository.LuckySpinHistoryRepository;
import com.vtc.gamerid.gateway.common.dao.repository.LuckySpinRepository;
import com.vtc.gamerid.gateway.common.dao.repository.TopupCardHistoryRepository;
import com.vtc.gamerid.gateway.common.dto.response.ResultReportSpinGetResponse;
import com.vtc.gamerid.gateway.common.dto.response.ResultReportSpinGetResponseData;
import com.vtc.gamerid.gateway.common.service.AbstractService;
import com.vtc.gamerid.gateway.common.ultils.DateUtils;
import com.vtc.gamerid.gateway.exception.ScoinNotFoundEntityException;
import com.vtc.gamerid.gateway.exception.ScoinUnAuthorizationException;
import com.vtc.gamerid.gateway.luckyspin.facade._interface.AdminReportSpinFacade;
import com.vtc.gamerid.gateway.luckyspin.service._interface.AdminReportSpinService;
import com.vtc.gamerid.gateway.profile.service.AdminProfileService;

/**
 * Created by phucnguyen on 14/07/2017.
 */
@Component
public class AdminReportSpinFacadeImpl extends AbstractService<LuckySpin, Long, AdminReportSpinRepository> implements AdminReportSpinFacade {
    @Autowired
    private AdminReportSpinService adminReportSpinService;
    
    @Autowired
    private AdminProfileService adminProfileService;
    
    @Autowired
    private TopupCardHistoryRepository topupCardHistoryRepo;
    
    @Autowired
    LuckySpinRepository luckySpinRepo;
    
    @Autowired
    LuckySpinHistoryRepository luckySpinHistoryRepo;
    
    @Override
    @PermissionService(functionName = "miniGameManager")
    public List<ResultReportSpinGetResponse> resultReportSpin(long luckySpinId, long startDateRequest, long endDateRequest) {
        // Get session user info
        UserGameRID userInfo = adminProfileService.getSessionUserInfo();
        if (userInfo == null)
            throw new ScoinUnAuthorizationException();
        
        LuckySpin luckySpin = luckySpinRepo.findOne(luckySpinId);
        if (ObjectUtils.isEmpty(luckySpin)) throw new ScoinNotFoundEntityException();
        Date startDate = new Date();
        Date endDate = new Date();
        if (startDateRequest > 0
                && endDateRequest > 0 ) {
            startDate = new Date(startDateRequest);
            endDate = new Date(endDateRequest);
        } else {
            startDate = luckySpin.getStartDate();
            if (luckySpin.getEndDate().getTime() < new Date().getTime()) endDate = luckySpin.getEndDate();
        }
        
        List<String> setTimeEvents = DateUtils.toStringDaysBetweenTwoDay(startDate, 
                                                                        endDate, 
                                                              DateUtils.DATE_DEFAULT_FORMAT);
        setTimeEvents.add("ALL");
        
        List<ResultReportSpinGetResponse> responses = new ArrayList<ResultReportSpinGetResponse>(); 
        for (String timeEvent : setTimeEvents) {
            logger.info("timeEvent ========== {}", timeEvent);
            Date startDateAccess = new Date();
            Date endDateAccess = new Date();
            
            if (timeEvent.equals("ALL")) {
                startDateAccess = startDate;
                endDateAccess = endDate;
            } else {
                Calendar cal = Calendar.getInstance();
                cal.setTime(DateUtils.toDateFromStr(timeEvent, DateUtils.DATE_DEFAULT_FORMAT));
                
                cal.set(Calendar.HOUR, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                startDateAccess = cal.getTime();
                
                cal.set(Calendar.HOUR, 23);
                cal.set(Calendar.MINUTE, 59);
                cal.set(Calendar.SECOND, 59);
                endDateAccess = cal.getTime();
            }
            
            logger.info("startDateAccess , endDateAccess ========== {}", startDateAccess + "========" + endDateAccess);
            
            //get resulf from history
            List<ResultReportSpinGetResponseData> dataResponses = repo
                    .getResultReportSpin(luckySpinId, startDateAccess, endDateAccess);
            
            //create total turn spin used
            int totalUsed = 0;
            if (!CollectionUtils.isEmpty(dataResponses)) {
                for (ResultReportSpinGetResponseData resultSpinHistory : dataResponses) {
                    totalUsed += resultSpinHistory.getTotal();
                }
            }
            
            //===================== Doanh thu và chi phí ===============
            
            // turnover từ việc nạp thẻ scoin
            long TURNOVER_BY_CARD = 0;
            List<TblTopupCardHistory> topupCardByCards = topupCardHistoryRepo
                    .getByPaymentTypeAndLuckyWheelUsedBetweenDate(Constants.SCOIN_TYPE_CARD, 
                                                                  null, 
                                                                  startDateAccess, 
                                                                  endDateAccess);
            if (!CollectionUtils.isEmpty(topupCardByCards)) {
                for (TblTopupCardHistory topupCardByCard : topupCardByCards) {
                  TURNOVER_BY_CARD += topupCardByCard.getTotalPayment();
                }
            }
            
            // turnover từ việc nạp ví
            long TURNOVER_BY_SCOIN = 0;
            List<TblTopupCardHistory> topupCardByScoins = topupCardHistoryRepo
                    .getByPaymentTypeAndLuckyWheelUsedBetweenDate(Constants.SCOIN_TYPE_SCOIN, 
                                                                  null, 
                                                                  startDateAccess, 
                                                                  endDateAccess);
            if (!CollectionUtils.isEmpty(topupCardByScoins)) {
                for (TblTopupCardHistory topupCardByScoin : topupCardByScoins) {
                  TURNOVER_BY_SCOIN += topupCardByScoin.getTotalPayment();
                }
            }
            
            // chi phí trả thưởng
            Long AWARDING_EXPENSES = luckySpinHistoryRepo.
                    sumValueByLuckySpinAndValueNotNull(luckySpinId, startDateAccess, endDateAccess);
            if (ObjectUtils.isEmpty(AWARDING_EXPENSES))
                AWARDING_EXPENSES = (long) 0;
            
            
            //=================== Lượt chơi====================
            
            //da nap the nhung chua vao trang sk
            long TOTAL_SPIN_DONOT_ACCESS_SK = 0;
            List<TblTopupCardHistory> topupCardDonotAccesss = topupCardHistoryRepo
                    .getByPaymentTypeAndLuckyWheelUsedBetweenDate(null, 
                                                                  false, 
                                                                  startDateAccess, 
                                                                  endDateAccess);
            if (!CollectionUtils.isEmpty(topupCardDonotAccesss)) {
                TOTAL_SPIN_DONOT_ACCESS_SK = turnSpinAddTopupCardScoin(topupCardDonotAccesss, luckySpin.getPricePerTurn());
            }
            
            //da nap the va da vao trang sk
            long TOTAL_SPIN_ACCESS_SK = 0;
            List<TblTopupCardHistory> topupCardAccesss = topupCardHistoryRepo
                    .getByPaymentTypeAndLuckyWheelUsedBetweenDate(null, 
                                                                  true, 
                                                                  startDateAccess, 
                                                                  endDateAccess);
            if (!CollectionUtils.isEmpty(topupCardAccesss)) {
                TOTAL_SPIN_ACCESS_SK = turnSpinAddTopupCardScoin(topupCardAccesss, luckySpin.getPricePerTurn());
            }
            
            // ==================USER===================
            
            // Số user tham gia event
            int TOTAL_USER_JOIN_EVENT = adminReportSpinService.countAllUserJoinEventBetweenDate(luckySpinId, 
                                                                      startDateAccess, 
                                                                      endDateAccess);
            
            dataResponses.add(new ResultReportSpinGetResponseData(luckySpinId, 0, "TURNOVER_SK", TURNOVER_BY_CARD + TURNOVER_BY_SCOIN));
            dataResponses.add(new ResultReportSpinGetResponseData(luckySpinId, 0, "TURNOVER_BY_CARD", TURNOVER_BY_CARD));
            dataResponses.add(new ResultReportSpinGetResponseData(luckySpinId, 0, "TURNOVER_BY_SCOIN", TURNOVER_BY_SCOIN));
            dataResponses.add(new ResultReportSpinGetResponseData(luckySpinId, 0, "AWARDING_EXPENSES", AWARDING_EXPENSES));
            
            dataResponses.add(new ResultReportSpinGetResponseData(luckySpinId, TOTAL_SPIN_DONOT_ACCESS_SK,"TOTAL_SPIN_DONOT_ACCESS_SK", 0));
            dataResponses.add(new ResultReportSpinGetResponseData(luckySpinId, TOTAL_SPIN_ACCESS_SK,"TOTAL_SPIN_ACCESS_SK", 0));
            dataResponses.add(new ResultReportSpinGetResponseData(luckySpinId, TOTAL_SPIN_DONOT_ACCESS_SK + TOTAL_SPIN_ACCESS_SK, "TOTAL_SPIN", 0));
            dataResponses.add(new ResultReportSpinGetResponseData(luckySpinId, totalUsed, "TOTAL_SPIN_USED", 0));
            
            dataResponses.add(new ResultReportSpinGetResponseData(luckySpinId, TOTAL_USER_JOIN_EVENT, "TOTAL_USER_JOIN_EVENT", 0));
            
            
            responses.add(new ResultReportSpinGetResponse(timeEvent,dataResponses));
            
        };
        
        // Report award
        // Map<String, Long> reportAward = adminReportSpinService.countTotalSpinReport(spinReportBean);
        return responses;

    }
    
    public long turnSpinAddTopupCardScoin(List<TblTopupCardHistory> topupCardHistories , int pricePerTurn) {
        long TOTAL_TOPUP_BY_CARD = 0;
        long TOTAL_TOPUP_BY_SCOIN = 0;
        for (TblTopupCardHistory topupCardHistory : topupCardHistories) {
            if (topupCardHistory.getPaymentType().equals(Constants.SCOIN_TYPE_CARD)) {
                TOTAL_TOPUP_BY_CARD += topupCardHistory.getTotalPayment();
            }
            
            if (topupCardHistory.getPaymentType().equals(Constants.SCOIN_TYPE_SCOIN)) {
                TOTAL_TOPUP_BY_SCOIN += topupCardHistory.getTotalPayment();
            }
        }
        
        return (TOTAL_TOPUP_BY_CARD*2 + TOTAL_TOPUP_BY_SCOIN)/pricePerTurn;
    }
}
