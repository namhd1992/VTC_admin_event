/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.eventGame.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.dao.entity.Game;
import com.vtc.gamerid.gateway.common.dao.entity.GameEvent;
import com.vtc.gamerid.gateway.common.dao.repository.GameEventRepository;
import com.vtc.gamerid.gateway.common.dao.repository.GameRepository;
import com.vtc.gamerid.gateway.common.dto.request.CreateUpdateEventGameRequest;
import com.vtc.gamerid.gateway.common.dto.request.GetEventGameRequest;
import com.vtc.gamerid.gateway.common.service.AbstractService;
import com.vtc.gamerid.gateway.common.ultils.StringUtils;
import com.vtc.gamerid.gateway.eventGame.service.AdminEventGameService;
import com.vtc.gamerid.gateway.exception.FailedToExecuteException;
import com.vtc.gamerid.gateway.exception.ScoinInvalidDataRequestException;
import com.vtc.gamerid.gateway.exception.ScoinNotFoundEntityException;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Jan 3, 2019
 */
@Service("adminEventGameService")
public class AdminEventGameServiceImpl extends AbstractService<GameEvent, Long, GameEventRepository>
        implements AdminEventGameService {
    
    @Autowired
    GameRepository    splayGameRepo;

//    @Autowired
//    AdminShopingDao      shopingDao;
//
//    @Autowired
//    AdminShopingItemDao shopingItemDao;

    @Override
    public List<GameEvent> getEvent(GetEventGameRequest request) {
        if (StringUtils.isEmpty(request.getSearchValue())) {
            //TODO
        }
        
        return repo.findAll();
    }
    
    @Override
    public long countAllRecordEvent() {
      return repo.count();
    }

    @Override
    public GameEvent createEvent(CreateUpdateEventGameRequest request) {
        if (ObjectUtils.isEmpty(request) 
                || ObjectUtils.isEmpty(request.getName())
                || ObjectUtils.isEmpty(request.getServiceId())
                || ObjectUtils.isEmpty(request.getUrlBaseEvent())
                || ObjectUtils.isEmpty(request.getOncePoint())
                || ObjectUtils.isEmpty(request.getGiftEvent())
                || ObjectUtils.isEmpty(request.getFromDate())
                || ObjectUtils.isEmpty(request.getToDate())) {
            throw new ScoinInvalidDataRequestException();
        }

        Game service = splayGameRepo.findByScoinGameIdAndStatusNot(request.getServiceId(), Constants.STATUS_DELETED);
        if (ObjectUtils.isEmpty(service)) {
            throw new ScoinNotFoundEntityException("Not found service by this service id");
        }
        
        GameEvent eventGame = toTblEventGame(request, new GameEvent());
        
//        if (request.getGiftEvent().equals("SHOPING")) {
//            TblShoping shoping = shopingDao.findByScoinGameId(eventGame.getServiceId());
//            if (ObjectUtils.isEmpty(shoping)) {
//                throw new NotFoundEntityException("Chưa tồn tại shop của item");
//            }
//            
//            addAndUpdateItemEvent(shoping, request.getItemUpdates());
//        }
        GameEvent save = repo.save(eventGame);
        if (ObjectUtils.isEmpty(save)) {
            throw new FailedToExecuteException("Don't create Event Game");
        }
        return save;
    }
    
    @Override
    public GameEvent updateEvent(CreateUpdateEventGameRequest request) {
        if (ObjectUtils.isEmpty(request)
                || ObjectUtils.isEmpty(request.getId())
                || ObjectUtils.isEmpty(request.getName())
                || ObjectUtils.isEmpty(request.getServiceId())
                || ObjectUtils.isEmpty(request.getUrlBaseEvent())
                || ObjectUtils.isEmpty(request.getOncePoint())
                || ObjectUtils.isEmpty(request.getGiftEvent())
                || ObjectUtils.isEmpty(request.getFromDate())
                || ObjectUtils.isEmpty(request.getToDate())) {
            throw new ScoinInvalidDataRequestException();
        }
        
        Game service = splayGameRepo.findByScoinGameIdAndStatusNot(request.getServiceId(), Constants.STATUS_DELETED);
        if (ObjectUtils.isEmpty(service)) {
            throw new ScoinNotFoundEntityException("Not found service by this service id");
        }
        
        GameEvent event = repo.findOne(request.getId());
        
        GameEvent eventGame = toTblEventGame(request, event);
        eventGame.setUpdateOn(new Date());

        GameEvent update = repo.save(eventGame);
        if (ObjectUtils.isEmpty(update)) {
            throw new FailedToExecuteException("Don't update Event Game");
        }
        
//        if (request.getGiftEvent().equals("SHOPING")) {
//            TblShoping shoping = shopingDao.findByScoinGameId(eventGame.getServiceId());
//            if (ObjectUtils.isEmpty(shoping)) {
//                throw new NotFoundEntityException("Chưa tồn tại shop của item");
//            }
//            
//            addAndUpdateItemEvent(shoping, request.getItemUpdates());
//            
//            if(!CollectionUtils.isEmpty(request.getItemRemoveIds())) {
//                request.getItemRemoveIds().forEach(itemRemoveId -> {
//                    TblShopingItem item = shopingItemDao.getShopingItem(itemRemoveId,(long) 0);
//                    if(ObjectUtils.isEmpty(item)) {
//                        throw new NotFoundEntityException("Có item không tồn tại hãy tạo item trước");
//                    }
//                    
//                    if(item.getShopId() == shoping.getId()) item.setShopId(0);
//                    TblShopingItem updateItem = shopingItemDao.updateItem(item);
//                    if (ObjectUtils.isEmpty(updateItem)) {
//                        throw new FailedToExecuteException("Don't update Item to remove event");
//                    }
//                });
//            }
//            
//        }
        return update;
    }

    @Override
    public String deleteEvent(Long eventId) {
        if (ObjectUtils.isEmpty(eventId)){
            throw new ScoinInvalidDataRequestException();
        }
        
        repo.delete(eventId);
        return "Success";
    }
    
    private GameEvent toTblEventGame(CreateUpdateEventGameRequest request,
                                        GameEvent eventGame) {

        if (!ObjectUtils.isEmpty(request.getId()))
            eventGame.setId(request.getId());
        eventGame.setName(request.getName());
        eventGame.setPlayTutorial(request.getPlayTutorial());
        eventGame.setServiceId(request.getServiceId());
        eventGame.setUrlBaseEvent(request.getUrlBaseEvent());
        eventGame.setOncePoint(request.getOncePoint());
        eventGame.setGiftEvent(request.getGiftEvent());
        eventGame.setRatioGift(1);
        if (!ObjectUtils.isEmpty(request.getRatioGift()))
            eventGame.setRatioGift(request.getRatioGift());
        eventGame.setLimitGift(request.getLimitGift());
        eventGame.setGaveGift(request.getGaveGift());
        if(!StringUtils.isEmpty(request.getPackageGift())) 
            eventGame.setPackageGift(request.getPackageGift());
        eventGame.setFromDate(new Date(request.getFromDate()));
        eventGame.setToDate(new Date(request.getToDate()));
        eventGame.setStatus(request.isStatus());

        return eventGame;
    }
    
//    private void addAndUpdateItemEvent(TblShoping shoping,
//                                       List<UpdateItemShopEventRequest> iTems) {
//        if (!CollectionUtils.isEmpty(iTems)) {
//            iTems.forEach(itemUpdate -> {
//                TblShopingItem item = shopingItemDao.getShopingItem(itemUpdate.getId(),(long) 0);
//                if (ObjectUtils.isEmpty(item)) {
//                    throw new NotFoundEntityException("Có item không tồn tại hãy tạo item trước");
//                }
//
//                if (item.getShopId() != shoping.getId())
//                    item.setShopId(shoping.getId());
//
//                logger.info("=============itemUpdate.getNewPrice()============{}",
//                        itemUpdate.getNewPrice());
//                if (itemUpdate.getNewPrice() > 0)
////                    item.setPrice(itemUpdate.getNewPrice());
//                TblShopingItem updateItem = shopingItemDao.updateItem(item);
//                if (ObjectUtils.isEmpty(updateItem)) {
//                    throw new FailedToExecuteException("Don't update Item to update event");
//                }
//            });
//        }
//    }

}
