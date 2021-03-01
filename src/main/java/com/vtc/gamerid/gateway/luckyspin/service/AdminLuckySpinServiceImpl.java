package com.vtc.gamerid.gateway.luckyspin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.dao.entity.LuckySpin;
import com.vtc.gamerid.gateway.common.dao.entity.TblLuckySpinItem;
import com.vtc.gamerid.gateway.common.dao.entity.TblLuckySpinItemOfLuckySpin;
import com.vtc.gamerid.gateway.common.dao.entity.UserGameRID;
import com.vtc.gamerid.gateway.common.dao.repository.LuckySpinItemOfLuckySpinRepository;
import com.vtc.gamerid.gateway.common.dao.repository.LuckySpinItemRepository;
import com.vtc.gamerid.gateway.common.dao.repository.LuckySpinRepository;
import com.vtc.gamerid.gateway.common.dto.response.ValidateBean;
import com.vtc.gamerid.gateway.common.service.AbstractService;
import com.vtc.gamerid.gateway.exception.FailedToExecuteException;
import com.vtc.gamerid.gateway.exception.ScoinInvalidDataRequestException;
import com.vtc.gamerid.gateway.exception.ScoinNotFoundEntityException;
import com.vtc.gamerid.gateway.luckyspin.bean.AdSEventManagerBean;
import com.vtc.gamerid.gateway.luckyspin.bean.AdminSpinEventFilterRequest;
import com.vtc.gamerid.gateway.luckyspin.bean.ItemOfSpinEventBean;
import com.vtc.gamerid.gateway.luckyspin.service._interface.AdminLuckySpinService;
import com.vtc.gamerid.gateway.luckyspin.service._interface.SpinValidate;
import com.vtc.gamerid.gateway.profile.service.AdminProfileService;

/**
 * Created by phucnguyen on 29/06/2017.
 */
@Service
public class AdminLuckySpinServiceImpl extends
        AbstractService<LuckySpin, Long, LuckySpinRepository> implements AdminLuckySpinService {
    
    @Autowired
    private AdminProfileService adminProfileService;

    @Autowired
    private LuckySpinRepository adminSpinEventDao;
    @Autowired
    private LuckySpinItemRepository spinItemDao;
    
    @Autowired
    private LuckySpinItemOfLuckySpinRepository luckySpinItemOfLuckySpinRepo;
    
    @Autowired
    private SpinValidate spinValidate;

    @Override
    public List<LuckySpin> getListLuckySpin(AdminSpinEventFilterRequest request) {
        UserGameRID userInfo = adminProfileService.getSessionUserInfo();
        Long userId = (long) 0;
        if(!userInfo.getGroupRole().getName().equals("admin"))
            userId = userInfo.getId();
            
        if(request.getLimit() < 1) request.setLimit(10);
        return repo.adminGetListSpin(userId, 
                request.getSearchValue(),
                request.getStatus(),  
                new PageRequest(request.getOffset(), request.getLimit()));
    }

    @Override
    public int adminCountGetLuckySpin(AdminSpinEventFilterRequest request) {
        UserGameRID userInfo = adminProfileService.getSessionUserInfo();
        Long userId = (long) 0;
        if(!userInfo.getGroupRole().getName().equals("admin"))
            userId = userInfo.getId();
        if(request.getLimit() < 1) request.setLimit(10);
        return adminSpinEventDao.adminCountGetListSpin(userId, request.getSearchValue(), request.getStatus());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public LuckySpin createLuckySpin(AdSEventManagerBean request) {
        
        UserGameRID createBy = verifyTokenAdmin();
        //Validate data
        ValidateBean validateBean = spinValidate.validateSpinEventRequest(request);
        if(!validateBean.isSuccess())
            throw new ScoinInvalidDataRequestException();

        LuckySpin luckySpin = toLuckySpin(request, null, createBy);
        
        luckySpin = adminSpinEventDao.save(luckySpin);
        if(luckySpin == null){
            throw new FailedToExecuteException();
        }
        List<TblLuckySpinItemOfLuckySpin> spinItemList = new ArrayList<>();

        //Create item of luckyspin
        int totalPosition = 0;
        for(ItemOfSpinEventBean spinItemRequest: request.getItemOfSpin()){
            totalPosition += spinItemRequest.getPosition();
        }
        
        for(ItemOfSpinEventBean spinItemRequest: request.getItemOfSpin()){
            TblLuckySpinItem itemOfSpin = spinItemDao.findOne(spinItemRequest.getItemOfSpinId());
            if (ObjectUtils.isEmpty(itemOfSpin)) {
                throw new ScoinNotFoundEntityException("Find not found item of spin");
            }
            
            // add item
            TblLuckySpinItemOfLuckySpin spin_item = new TblLuckySpinItemOfLuckySpin(luckySpin, itemOfSpin,
                    spinItemRequest.getQuantity(), spinItemRequest.getPosition());
            spin_item.setRatio((double) spinItemRequest.getPosition() * 100 / totalPosition);
            spin_item = luckySpinItemOfLuckySpinRepo.save(spin_item);
            if (spin_item == null) {
                throw new FailedToExecuteException();
            }
            spinItemList.add(spin_item);
        }

        //Add item to lucky spin
        luckySpin.setSpinItems(spinItemList);
        luckySpin = adminSpinEventDao.save(luckySpin);

        return luckySpin;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public LuckySpin updateLuckySpin(AdSEventManagerBean request) {
        UserGameRID createBy = verifyTokenAdmin();
        //Validate data
        ValidateBean validateBean = spinValidate.validateSpinEventRequest(request);
        if(!validateBean.isSuccess())
            throw new ScoinInvalidDataRequestException();

        //Get luckyspin
        LuckySpin luckySpin = adminSpinEventDao.findByIdAndStatusNot(request.getSpinEventId(), Constants.STATUS_DELETED);
        if(luckySpin == null){
            throw new ScoinNotFoundEntityException("Can not find luckyspin");
        }

        luckySpin = toLuckySpin(request, luckySpin, createBy);

        //Update item of luckyspin
        int totalPosition = 0;
        for (ItemOfSpinEventBean spinItemRequest : request.getItemOfSpin()) {
            totalPosition += spinItemRequest.getPosition();
        }
        
        List<TblLuckySpinItemOfLuckySpin> newArrSpin = new ArrayList<>();
        for(ItemOfSpinEventBean spinItemRequest: request.getItemOfSpin()){
            TblLuckySpinItemOfLuckySpin spinItem = luckySpinItemOfLuckySpinRepo.findOne(spinItemRequest.getSpinItemId());
            
            TblLuckySpinItem itemOfSpin = spinItemDao.findOne(spinItemRequest.getItemOfSpinId());
            if(ObjectUtils.isEmpty(itemOfSpin)){
                throw new ScoinNotFoundEntityException("Find not found item of spin");
            }
            
            // add item if null
            if (ObjectUtils.isEmpty(spinItem)) {
                TblLuckySpinItemOfLuckySpin spin_item = new TblLuckySpinItemOfLuckySpin(luckySpin, itemOfSpin,
                        spinItemRequest.getQuantity(),
                        spinItemRequest.getPosition());
                spin_item.setRatio((double) spinItemRequest.getPosition() * 100 / totalPosition);
                spin_item = luckySpinItemOfLuckySpinRepo.save(spin_item);
                if(spin_item == null){
                    throw new FailedToExecuteException();
                }
                newArrSpin.add(spin_item);
                int stockQuantity = itemOfSpin.getQuantity() - spinItemRequest.getQuantity();
                itemOfSpin.setQuantity(stockQuantity);
                spinItemDao.save(itemOfSpin);
                continue;
            }
            
            spinItemDao.save(itemOfSpin);
            
            spinItem.setItem(itemOfSpin);
            spinItem.setTotalQuantity(spinItemRequest.getQuantity());
            spinItem.setRatioIndex(spinItemRequest.getPosition());
            double radioItem = (double) spinItemRequest.getPosition() * 100 / totalPosition;
            spinItem.setRatio(radioItem);
            luckySpinItemOfLuckySpinRepo.save(spinItem);
            newArrSpin.add(spinItem);
        }

        //Delete old item
        for(TblLuckySpinItemOfLuckySpin oldItem: luckySpin.getSpinItems()){
            boolean tmp = false;
            for(TblLuckySpinItemOfLuckySpin newItem: newArrSpin){
                if (oldItem.getId() == newItem.getId()){
                    tmp = true;
                    break;
                }
            }
            if(tmp) continue;
            luckySpinItemOfLuckySpinRepo.delete(oldItem);
        }
        luckySpin.setSpinItems(newArrSpin);

        //Update luckyspin
        adminSpinEventDao.save(luckySpin);
        return luckySpin;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String deleteLuckySpin(Long luckySpinId) {
        verifyTokenAdmin();
        if(luckySpinId < 0)
            throw new ScoinInvalidDataRequestException();

        //Get luckyspin
        LuckySpin luckySpin = adminSpinEventDao.findByIdAndStatusNot(luckySpinId, Constants.STATUS_DELETED);
        if(luckySpin == null){
            throw new ScoinNotFoundEntityException();
        }

        luckySpin.setStatus(Constants.STATUS_DELETED);
        luckySpin = adminSpinEventDao.save(luckySpin);
        return "Successful";
    }
    
    public LuckySpin toLuckySpin(AdSEventManagerBean request,
                                    LuckySpin luckySpin, 
                                    UserGameRID createBy) {
        if (ObjectUtils.isEmpty(luckySpin)) {
            luckySpin = new LuckySpin();
        } else {
            luckySpin.setId(luckySpin.getId());
        }

        luckySpin.setName(request.getName());
        luckySpin.setType(request.getType());
        luckySpin.setImage(request.getImage());
        luckySpin.setStartDate(request.getStartDate());
        luckySpin.setEndDate(request.getEndDate());
        luckySpin.setDescription(request.getDescription());
        luckySpin.setLinkLiveStream(request.getLinkLiveStream());
        luckySpin.setFreeSpinPerDay(request.getFreeSpinPerDay());
        luckySpin.setFreeSpinOnStart(request.getFreeSpinOnStart());
        luckySpin.setPricePerTurn(request.getPricePerTurn());
        luckySpin.setBuyTurnType(request.getBuyTurnType());
        luckySpin.setStatus(request.getStatus());
        luckySpin.setCreateBy(createBy.getId());
        if (ObjectUtils.isEmpty(request.getGoldTimeStart())) {
            luckySpin.setGoldTimeStart(new Date());
        } else {
          luckySpin.setGoldTimeStart(request.getGoldTimeStart());
        }
            
        if (ObjectUtils.isEmpty(request.getGoldTimeEnd())){
            luckySpin.setGoldTimeEnd(new Date());
        } else {
            luckySpin.setGoldTimeEnd(request.getGoldTimeEnd());
        }
        
        luckySpin.setGoldTimeItemIndex(request.getGoldTimeItemIndex());
        luckySpin.setGoldTimeStatus(request.isGoldTimeStatus());
        return luckySpin;
    }

//    @Override
//    public void addTurnInNewDay() {
//        logger.info("Update luckyspin in midnight");
//        adminSpinEventDao.addTurnInNewDay();
//    }
}
