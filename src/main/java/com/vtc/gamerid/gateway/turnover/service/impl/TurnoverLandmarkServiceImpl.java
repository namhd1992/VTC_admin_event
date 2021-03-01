/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.turnover.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.vtc.gamerid.gateway.common.dao.entity.TblLuckySpinItem;
import com.vtc.gamerid.gateway.common.dao.entity.TblTurnoverLandmark;
import com.vtc.gamerid.gateway.common.dao.repository.LuckySpinItemRepository;
import com.vtc.gamerid.gateway.common.dao.repository.TurnoverLandmarkRepository;
import com.vtc.gamerid.gateway.common.dto.request.CreateUpdateTurnoverLandmarkRequest;
import com.vtc.gamerid.gateway.common.dto.request.GetTurnonverLandmarkRequest;
import com.vtc.gamerid.gateway.common.service.AbstractService;
import com.vtc.gamerid.gateway.common.ultils.StringUtils;
import com.vtc.gamerid.gateway.exception.ScoinInvalidDataRequestException;
import com.vtc.gamerid.gateway.exception.ScoinNotFoundEntityException;
import com.vtc.gamerid.gateway.turnover.service.TurnoverLandmarkService;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Sep 9, 2019
 */
@Service("turnoverLandmarkService")
public class TurnoverLandmarkServiceImpl
        extends AbstractService<TblTurnoverLandmark, Long, TurnoverLandmarkRepository>
        implements TurnoverLandmarkService {

    @Autowired
    LuckySpinItemRepository spinItemRepo;
    
    @Override
    public List<TblTurnoverLandmark> getTurnoverLandmark(GetTurnonverLandmarkRequest request) {
        return repo.
                getTurnoverLandmark(request.getId(), request.getGame(), request.getGameId(), request.getTurnoverLandmark());
    }

    @Override
    public List<TblTurnoverLandmark> createTurnoverLandmark(List<CreateUpdateTurnoverLandmarkRequest> requests) {
        if (CollectionUtils.isEmpty(requests)) {
            return new ArrayList<TblTurnoverLandmark>();
        }
        
        List<TblTurnoverLandmark> responses = new ArrayList<TblTurnoverLandmark>();
        for (CreateUpdateTurnoverLandmarkRequest request : requests) {
            if (ObjectUtils.isEmpty(request)
                    || ObjectUtils.isEmpty(request.getGame())
                    || ObjectUtils.isEmpty(request.getGameId())
                    || request.getItemId() < 1
                    || request.getLimitQuantity() < 0
                    || ObjectUtils.isEmpty(request.getLimitType())
                    || request.getTurnoverLandmark() < 1 ) {
                throw new ScoinInvalidDataRequestException();
            }
            
            TblLuckySpinItem itemOfSpin = spinItemRepo.findOne(request.getItemId());
            if (ObjectUtils.isEmpty(itemOfSpin)) 
                throw new ScoinNotFoundEntityException("Not found Item has id : " + request.getItemId());
            
            TblTurnoverLandmark turnoverLandmark = repo.findByGameIdAndItemAndTurnoverAndType(request.getGame(), 
                                        request.getGameId(), 
                                        request.getItemId(), 
                                        request.getTurnoverLandmark(),
                                        null);

            if (!ObjectUtils.isEmpty(turnoverLandmark))
                continue;
            
            TblTurnoverLandmark response = toTurnoverLandmark(request, new TblTurnoverLandmark());
            response.setCreateOn(new Date());
            response = repo.save(response);
            responses.add(response);
        };
        
        return responses;
    }

    @Override
    public List<TblTurnoverLandmark> updateTurnoverLandmark(List<CreateUpdateTurnoverLandmarkRequest> requests) {
        if (CollectionUtils.isEmpty(requests)) {
            return new ArrayList<TblTurnoverLandmark>();
        }
        
        List<TblTurnoverLandmark> responses = new ArrayList<TblTurnoverLandmark>();
        for (CreateUpdateTurnoverLandmarkRequest request : requests) {
            if (ObjectUtils.isEmpty(request.getId())
                    || StringUtils.isEmpty(request.getGame())
                    || ObjectUtils.isEmpty(request.getGameId())
                    || request.getItemId() < 1
                    || request.getLimitQuantity() < 0
                    || StringUtils.isEmpty(request.getLimitType())
                    || request.getTurnoverLandmark() < 1 ) {
                throw new ScoinInvalidDataRequestException();
            }
            
            TblLuckySpinItem itemOfSpin = spinItemRepo.findOne(request.getItemId());
            if (ObjectUtils.isEmpty(itemOfSpin)) 
                throw new ScoinNotFoundEntityException("Not found Item has id : " + request.getItemId());
            
            TblTurnoverLandmark turnoverLandmark = repo.findByGameIdAndItemAndTurnoverAndType(request.getGame(), 
                                        request.getGameId(), 
                                        request.getItemId(), 
                                        request.getTurnoverLandmark(),
                                        request.getLimitType());

            if (!ObjectUtils.isEmpty(turnoverLandmark))
                continue;
            
            GetTurnonverLandmarkRequest requestId = new GetTurnonverLandmarkRequest();
            requestId.setId(request.getId());
            List<TblTurnoverLandmark> turnoverLandmarks = repo.getTurnoverLandmark(requestId.getId(), null, 0, 0);
            if (CollectionUtils.isEmpty(turnoverLandmarks)) {
                throw new ScoinNotFoundEntityException("Not found Turnover Landmark by this id");
            }
            TblTurnoverLandmark response = toTurnoverLandmark(request, turnoverLandmarks.get(0));
            response = repo.save(response);
            responses.add(response);
        };

        return responses;
    }

    @Override
    public String deleteTurnoverLandmark(List<Long> turnoverLandmarkIds) {
        if (CollectionUtils.isEmpty(turnoverLandmarkIds)) {
            return "Successful";
        }

        turnoverLandmarkIds.forEach(turnoverLandmarkId -> {
            GetTurnonverLandmarkRequest request = new GetTurnonverLandmarkRequest();
            request.setId(turnoverLandmarkId);
            List<TblTurnoverLandmark> turnoverLandmarks = repo.
                    getTurnoverLandmark(request.getId(), request.getGame(), request.getGameId(), request.getTurnoverLandmark());
            if (CollectionUtils.isEmpty(turnoverLandmarks)) {
                throw new ScoinNotFoundEntityException("Not found Turnover Landmark by this id");
            }

            repo.delete(turnoverLandmarks.get(0));
        });
       
        return "Delete successfull id : " + turnoverLandmarkIds.toString();
    }

    @Override
    public int countAllRecordEvent() {
        return 0;
    }
    
    private TblTurnoverLandmark toTurnoverLandmark(CreateUpdateTurnoverLandmarkRequest request,
                                                   TblTurnoverLandmark response) {
        response.setId(request.getId());
        response.setUpdateOn(new Date());
        response.setGame(request.getGame());
        response.setGameId(request.getGameId());
        response.setItemId(request.getItemId());
        response.setLimitQuantity(request.getLimitQuantity());
        response.setLimitType(request.getLimitType());
        response.setTurnoverLandmark(request.getTurnoverLandmark());
        return response;
    }

}
