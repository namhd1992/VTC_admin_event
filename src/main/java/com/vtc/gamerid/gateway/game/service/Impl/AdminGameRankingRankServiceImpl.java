/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.game.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.vtc.gamerid.gateway.common.dao.entity.GameRanking;
import com.vtc.gamerid.gateway.common.dao.entity.GameRankingRank;
import com.vtc.gamerid.gateway.common.dao.repository.GameRankingRankRepository;
import com.vtc.gamerid.gateway.common.dto.request.BaseRequest;
import com.vtc.gamerid.gateway.common.dto.request.UpdateAndCreateGameRankingRankRequest;
import com.vtc.gamerid.gateway.common.service.AbstractService;
import com.vtc.gamerid.gateway.exception.FailedToExecuteException;
import com.vtc.gamerid.gateway.exception.ScoinDuplicateEntityException;
import com.vtc.gamerid.gateway.exception.ScoinInvalidDataRequestException;
import com.vtc.gamerid.gateway.exception.ScoinNotFoundEntityException;
import com.vtc.gamerid.gateway.game.service.AdminGameRankingRankService;
import com.vtc.gamerid.gateway.game.service.AdminGameRankingService;
import com.vtc.gamerid.gateway.profile.service.AdminProfileService;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Apr 16, 2020
 */
@Service("gameRankingRankService")
public class AdminGameRankingRankServiceImpl extends AbstractService<GameRankingRank, Long, GameRankingRankRepository>
        implements AdminGameRankingRankService {
    
    @Autowired
    AdminGameRankingService gameRankingService;
    
    @Autowired
    AdminProfileService adminProfileService;
    
//    @Override
//    public GameRankingRank getGameRankingRankById(Long id) {
//        return repo.findOne(id);
//    } 

    @Override
    public List<GameRankingRank> getByGameRanking(BaseRequest request, Long gameRankingId) {
        if (ObjectUtils.isEmpty(gameRankingId))
            throw new ScoinInvalidDataRequestException();
        
        GameRanking gameRanking = gameRankingService.get(gameRankingId);
        if (ObjectUtils.isEmpty(gameRanking))
            throw new ScoinNotFoundEntityException("Not found Game Ranking by this id");
        
        return repo.findByGameRanking(gameRanking, new PageRequest(request.getOffset(), request.getLimit()));
    }

    @Override
    public int countByGameRanking(Long gameRankingId) {
        if (ObjectUtils.isEmpty(gameRankingId))
            throw new ScoinInvalidDataRequestException();
        GameRanking gameRanking = gameRankingService.get(gameRankingId);
        if (ObjectUtils.isEmpty(gameRanking))
            throw new ScoinNotFoundEntityException("Not found Game Ranking by this id");
        return repo.countByGameRanking(gameRanking);
    }

    @Override
    public GameRankingRank updateRankingRank(UpdateAndCreateGameRankingRankRequest request) {
        if (ObjectUtils.isEmpty(request)
                || ObjectUtils.isEmpty(request.getId())
                || ObjectUtils.isEmpty(request.getGameRankingId())
                || ObjectUtils.isEmpty(request.getRankPosition())) {
            throw new ScoinInvalidDataRequestException();
        }
        
        GameRankingRank gameRankingRank = repo.getOne(request.getId());
        if (ObjectUtils.isEmpty(gameRankingRank)) 
            throw new ScoinNotFoundEntityException("Not found Rank by this id");
        
        GameRanking gameRanking = gameRankingService.get(request.getGameRankingId());
        if (ObjectUtils.isEmpty(gameRanking))
           throw new ScoinNotFoundEntityException("Not found Game Ranking by this id");
        
        GameRankingRank rankByPosition = repo.
                findByGameRankingAndRankPositionAndIdNot(gameRanking, request.getRankPosition(), gameRankingRank.getId());
        if (!ObjectUtils.isEmpty(rankByPosition))
            throw new ScoinDuplicateEntityException("Duplicate Rank by position");
        
        gameRankingRank.setRankName(request.getRankName());
        gameRankingRank.setGameRanking(gameRanking);
        gameRankingRank.setRankPosition(request.getRankPosition());
        gameRankingRank.setRankIconUrl(request.getRankIconUrl());
        gameRankingRank.setRankQuantity(request.getRankQuantity());
        gameRankingRank.setDecription(request.getDescription());
        gameRankingRank.setStatus(request.getStatus());
        
        gameRankingRank = repo.save(gameRankingRank);
        if (ObjectUtils.isEmpty(gameRankingRank))
            throw new FailedToExecuteException("Can't update rank");
        return gameRankingRank;
    }

    @Override
    public GameRankingRank createRankingRank(UpdateAndCreateGameRankingRankRequest request) {
        if (ObjectUtils.isEmpty(request)
                || !ObjectUtils.isEmpty(request.getId())
                || ObjectUtils.isEmpty(request.getGameRankingId())
                || ObjectUtils.isEmpty(request.getRankPosition())) {
            throw new ScoinInvalidDataRequestException();
        }
        
        GameRanking gameRanking = gameRankingService.get(request.getGameRankingId());
        if (ObjectUtils.isEmpty(gameRanking))
           throw new ScoinNotFoundEntityException("Not found Game Ranking by this id");
        
        GameRankingRank rankByPosition = repo.findByGameRankingAndRankPosition(gameRanking, request.getRankPosition());
        if (!ObjectUtils.isEmpty(rankByPosition))
            throw new ScoinDuplicateEntityException("Duplicate Rank by position");
        
        GameRankingRank gameRankingRank = new GameRankingRank();
        
        gameRankingRank.setRankName(request.getRankName());
        gameRankingRank.setGameRanking(gameRanking);
        gameRankingRank.setRankPosition(request.getRankPosition());
        gameRankingRank.setRankIconUrl(request.getRankIconUrl());
        gameRankingRank.setRankQuantity(request.getRankQuantity());
        gameRankingRank.setDecription(request.getDescription());
        gameRankingRank.setStatus(request.getStatus());
        
        gameRankingRank = repo.save(gameRankingRank);
        if (ObjectUtils.isEmpty(gameRankingRank))
            throw new FailedToExecuteException("Can't update rank");
        return gameRankingRank;
    }

    @Override
    public String deleteGameRankingRank(Long gameRankingRankId) {
        GameRankingRank gameRankingRank = repo.getOne(gameRankingRankId);
        if (ObjectUtils.isEmpty(gameRankingRank)) 
            throw new ScoinNotFoundEntityException("Not found Rank by this id");
        
        repo.delete(gameRankingRank);
        return "Successfull";
    }

}
