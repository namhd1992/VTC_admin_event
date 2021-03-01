/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.game.service.Impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.dao.entity.GameRanking;
import com.vtc.gamerid.gateway.common.dao.entity.GameRankingHistory;
import com.vtc.gamerid.gateway.common.dao.entity.GameRankingRank;
import com.vtc.gamerid.gateway.common.dao.entity.UserInfo;
import com.vtc.gamerid.gateway.common.dao.repository.GameRankingHistoryRepository;
import com.vtc.gamerid.gateway.common.dao.repository.GameRankingItemRepository;
import com.vtc.gamerid.gateway.common.dao.repository.GameRankingRankRepository;
import com.vtc.gamerid.gateway.common.dao.repository.GameRankingRepository;
import com.vtc.gamerid.gateway.common.dao.repository.UserInfoRepository;
import com.vtc.gamerid.gateway.common.dto.response.AdminGameRankingItemReportResponse;
import com.vtc.gamerid.gateway.common.dto.response.AdminRankReportResponse;
import com.vtc.gamerid.gateway.common.dto.response.BasePhongMaChienResponse;
import com.vtc.gamerid.gateway.common.dto.response.ServerGamePhongMaChienResponseData;
import com.vtc.gamerid.gateway.common.dto.response.TopTopupPhongmachienResponse;
import com.vtc.gamerid.gateway.common.service.AbstractService;
import com.vtc.gamerid.gateway.common.ultils.ApiExchangeServiceUtil;
import com.vtc.gamerid.gateway.common.ultils.DateUtils;
import com.vtc.gamerid.gateway.exception.ScoinInvalidDataRequestException;
import com.vtc.gamerid.gateway.exception.ScoinNotFoundEntityException;
import com.vtc.gamerid.gateway.game.service.AdminGameRankingReportService;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * May 7, 2020
 */
@Service("adminGameRankingReportService")
public class AdminGameRankingReportServiceImpl
        extends AbstractService<GameRanking, Long, GameRankingRepository>
        implements AdminGameRankingReportService {
    
    @Autowired
    GameRankingRankRepository RankRepo;
    
    @Autowired
    GameRankingItemRepository itemRepo;
    
    @Autowired
    UserInfoRepository userInfoRepo;
    
    @Autowired
    GameRankingHistoryRepository historyRepo;

    @Override
    public List<AdminRankReportResponse> RankReport(Long gameRakingId, int weekOfYear) {
        if (ObjectUtils.isEmpty(gameRakingId)
                || weekOfYear < 1)
            throw new ScoinInvalidDataRequestException();
        
        GameRanking gameRanking = repo.getOne(gameRakingId);
        if (ObjectUtils.isEmpty(gameRanking)) {
            throw new ScoinNotFoundEntityException("This game don't have Ranking");
        }
        
        List<String> rankNameResponse = new ArrayList<String>();
        
        int rankQuantity = 0;
        for(GameRankingRank rank : gameRanking.getGameRankingRanks()) {
            for (int i = 0; i < rank.getRankQuantity(); i++) {
                rankQuantity += 1;
                rankNameResponse.add(rank.getRankName());
            }
        }
        
        List<AdminRankReportResponse> responses = new ArrayList<AdminRankReportResponse>();
        
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR,2020);
        cal.set(Calendar.WEEK_OF_YEAR, weekOfYear);
        cal.set(Calendar.DAY_OF_WEEK, 2);
        Date startDate = DateUtils.startDate(cal.getTime());
        
        cal.set(Calendar.WEEK_OF_YEAR, weekOfYear + 1);
        cal.set(Calendar.DAY_OF_WEEK, 1);
        Date endDate = DateUtils.endDate(cal.getTime());
        
        if (gameRanking.getServiceId() == Constants.SERVICE_GAME_ID_PHONG_MA_CHIEN) {
         
            BasePhongMaChienResponse<ServerGamePhongMaChienResponseData> serverInfos = ApiExchangeServiceUtil.
                    get("http://api.phongmachien.vn/game.php/Api/other/get_api_data?action=getServerList", 
                            new TypeReference<BasePhongMaChienResponse<ServerGamePhongMaChienResponseData>>() {});
            
            if (ObjectUtils.isEmpty(serverInfos))
                throw new ScoinNotFoundEntityException("Not found server of game");
            
            String UrlTopInfo = "http://api.phongmachien.vn/game.php/Api/other/get_api_data?action=getListOfRecharge"
                    + "&top=" + rankQuantity
                    + "&startdate=" + startDate.getTime()/1000
                    + "&enddate=" + endDate.getTime()/1000;
            BasePhongMaChienResponse<TopTopupPhongmachienResponse> topTopupGame = ApiExchangeServiceUtil.
                    get(UrlTopInfo, new TypeReference<BasePhongMaChienResponse<TopTopupPhongmachienResponse>>() {});
            
            if (!ObjectUtils.isEmpty(topTopupGame)) {
                topTopupGame.getContent().forEach(user -> {
                    AdminRankReportResponse response = new AdminRankReportResponse();
                    String server = serverInfos.getContent().stream()
                            .filter(serverInfo -> serverInfo.getServerid() == user.getServerid())
                            .collect(Collectors.toList()).get(0).getServername();
                    response.setUserName(user.getRolename());
                    response.setServer(server);
                    response.setPotsition(user.getPosition());
                    response.setRankName(rankNameResponse.get(user.getPosition() - 1));
                    response.setScoin(user.getTotalpayment());
                    responses.add(response);
                });
            }
        }
        
        return responses;
    }

    @Override
    public List<AdminGameRankingItemReportResponse> itemReport(Long gameRakingId, int weekOfYear) {
        if (ObjectUtils.isEmpty(gameRakingId)
                || weekOfYear < 1)
            throw new ScoinInvalidDataRequestException();
        
        GameRanking gameRanking = repo.getOne(gameRakingId);
        if (ObjectUtils.isEmpty(gameRanking)) {
            throw new ScoinNotFoundEntityException("This game don't have Ranking");
        }
        
        int rankQuantity = 0;
        List<GameRankingRank> realRanks = new ArrayList<GameRankingRank>();
        for(GameRankingRank rank : gameRanking.getGameRankingRanks()) {
            for (int i = 0; i < rank.getRankQuantity(); i++) {
                rankQuantity += 1;
                realRanks.add(rank);
            }
        }
        
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR,2020);
        cal.set(Calendar.WEEK_OF_YEAR, weekOfYear);
        cal.set(Calendar.DAY_OF_WEEK, 2);
        Date startDate = DateUtils.startDate(cal.getTime());
        
        cal.set(Calendar.WEEK_OF_YEAR, weekOfYear + 1);
        cal.set(Calendar.DAY_OF_WEEK, 1);
        Date endDate = DateUtils.endDate(cal.getTime());
        
        List<AdminGameRankingItemReportResponse> responses = new ArrayList<AdminGameRankingItemReportResponse>();
        if (gameRanking.getServiceId() == Constants.SERVICE_GAME_ID_PHONG_MA_CHIEN) {
            BasePhongMaChienResponse<ServerGamePhongMaChienResponseData> serverInfos = ApiExchangeServiceUtil.
                    get("http://api.phongmachien.vn/game.php/Api/other/get_api_data?action=getServerList", 
                            new TypeReference<BasePhongMaChienResponse<ServerGamePhongMaChienResponseData>>() {});
            
            if (ObjectUtils.isEmpty(serverInfos))
                throw new ScoinNotFoundEntityException("Not found server of game");
            
            String UrlTopInfo = "http://api.phongmachien.vn/game.php/Api/other/get_api_data?action=getListOfRecharge"
                    + "&top=" + rankQuantity
                    + "&startdate=" + startDate.getTime()/1000
                    + "&enddate=" + endDate.getTime()/1000;
            BasePhongMaChienResponse<TopTopupPhongmachienResponse> topTopupGame = ApiExchangeServiceUtil.
                    get(UrlTopInfo, new TypeReference<BasePhongMaChienResponse<TopTopupPhongmachienResponse>>() {});
            
            if (!ObjectUtils.isEmpty(topTopupGame)) {
//                realRanks.forEach(action);
                topTopupGame.getContent().forEach(user -> {
                    UserInfo userInfo = userInfoRepo.findByUserVTCScoinId((long) user.getAccountid());
                    realRanks.get(user.getPosition()).getGameRankingItems().forEach(item -> {
                        AdminGameRankingItemReportResponse response = new AdminGameRankingItemReportResponse();
                        response.setUserName(user.getRolename());
                        response.setRankName(realRanks.get(user.getPosition()).getRankName());
                        response.setItemName(item.getItemName());
                        response.setReceived(true);
                        GameRankingHistory gameRankingHistory = historyRepo.
                                findByUserInfoAndItemAndCreateOnBetween(userInfo, item, startDate, endDate);
                        if (ObjectUtils.isEmpty(gameRankingHistory)) {
                            if (item.getItemType().equals(Constants.GAME_RANKING_ITEM_TYPE_GIFTCODE)
                                    || item.getItemType().equals(Constants.GAME_RANKING_ITEM_TYPE_TURN_LUCKYSPIN)
                                    || item.getItemType().equals(Constants.GAME_RANKING_ITEM_TYPE_XU)
                                    || item.getItemType().equals(Constants.GAME_RANKING_ITEM_TYPE_SCOIN)
                                    || item.getItemType().equals(Constants.GAME_RANKING_ITEM_TYPE_CARD)) {
                                response.setReceived(false);
                            }
                        } else {
                            response.setReceivedDate(gameRankingHistory.getCreateOn());
                        }
                            
                       
                        
                        responses.add(response);
                    });
                });
            }
        }
        return responses;
    }
    
    

}
