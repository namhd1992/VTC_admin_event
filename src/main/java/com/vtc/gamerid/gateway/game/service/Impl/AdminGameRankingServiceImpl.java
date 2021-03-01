/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.game.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.dao.entity.GameRanking;
import com.vtc.gamerid.gateway.common.dao.entity.UserGameRID;
import com.vtc.gamerid.gateway.common.dao.repository.GameRankingRepository;
import com.vtc.gamerid.gateway.common.dto.request.GameRankingUpdateAndCreateRequest;
import com.vtc.gamerid.gateway.common.service.AbstractService;
import com.vtc.gamerid.gateway.common.ultils.StringUtils;
import com.vtc.gamerid.gateway.exception.FailedToExecuteException;
import com.vtc.gamerid.gateway.exception.ScoinDuplicateEntityException;
import com.vtc.gamerid.gateway.exception.ScoinInvalidDataRequestException;
import com.vtc.gamerid.gateway.exception.ScoinNotFoundEntityException;
import com.vtc.gamerid.gateway.game.service.AdminGameRankingService;
import com.vtc.gamerid.gateway.profile.service.AdminProfileService;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Apr 14, 2020
 */
@Service("adminGameRankingService")
public class AdminGameRankingServiceImpl extends AbstractService<GameRanking, Long, GameRankingRepository>
        implements AdminGameRankingService {
    
    @Autowired
    AdminProfileService adminProfileService;
    
    @Override
    public GameRanking getGameRankingById(Long id) {
        UserGameRID userInfo = adminProfileService.getSessionUserInfo();
        if (userInfo.getGroupRole().getName().equals(Constants.ROLE_ADMIN)) {
            return repo.findOne(id);
        }
        return repo.findByIdAndCreateBy(id, userInfo.getId());

    }

    @Override
    public List<GameRanking> getGameRanking() {
        UserGameRID userInfo = adminProfileService.getSessionUserInfo();
        List<GameRanking> responses = new ArrayList<GameRanking>();
        if (userInfo.getGroupRole().getName().equals(Constants.ROLE_ADMIN)) {
            responses = repo.findAll();
        } else {
            responses = repo.findAllByCreateBy(userInfo.getId());
        }
        responses.forEach(gameRanking -> {
            gameRanking.setGameRankingRanks(gameRanking.getGameRankingRanks().stream()
                    .sorted((o1, o2) -> Integer.valueOf(o1.getRankPosition())
                            .compareTo(Integer.valueOf(o2.getRankPosition())))
                    .collect(Collectors.toList()));
        });
        return responses;
    }
    
    @Override
    public String createGameRanking(GameRankingUpdateAndCreateRequest request) {
        if(StringUtils.isEmpty(request.getName())
                || StringUtils.isEmpty(request.getDescription())
                || request.getServiceId() < 1
                || !ObjectUtils.isEmpty(request.getId())
                || StringUtils.isEmpty(request.getStatus())){
            throw new ScoinInvalidDataRequestException();
        }
        
        UserGameRID userInfo = adminProfileService.getSessionUserInfo();
        
        GameRanking gameRankingByserviceId = repo.findByServiceId(request.getServiceId());
        if (!ObjectUtils.isEmpty(gameRankingByserviceId))
            throw new ScoinDuplicateEntityException("Đã tạo cho game này rồi");

        GameRanking gameRanking = new GameRanking();
        gameRanking.setDescription(request.getDescription());
        gameRanking.setName(request.getName());
        gameRanking.setServiceId(request.getServiceId());
        gameRanking.setCreateBy(userInfo.getId());
        gameRanking.setStatus(request.getStatus());
        gameRanking = repo.save(gameRanking);
        if (ObjectUtils.isEmpty(gameRanking))
            throw new FailedToExecuteException("Don't create or update Game Ranking");

        return "Successfull";
    }

    @Override
    public String updateGameRanking(GameRankingUpdateAndCreateRequest request) {
        if(StringUtils.isEmpty(request.getName())
                || StringUtils.isEmpty(request.getDescription())
                || request.getServiceId() < 1
                || ObjectUtils.isEmpty(request.getId())
                || StringUtils.isEmpty(request.getStatus())){
            throw new ScoinInvalidDataRequestException();
        }
        
        GameRanking gameRanking = repo.getOne(request.getId());
        if (ObjectUtils.isEmpty(gameRanking))
            throw new ScoinNotFoundEntityException("Not found Game Ranking by this id");

        gameRanking.setDescription(request.getDescription());
        gameRanking.setName(request.getName());
        gameRanking.setServiceId(request.getServiceId());
        gameRanking.setStatus(request.getStatus());
        gameRanking = repo.save(gameRanking);
        if (ObjectUtils.isEmpty(gameRanking))
            throw new FailedToExecuteException("Don't create or update Game Ranking");

        return "Successfull";
    }

    @Override
    public String deleteGameRanking(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new ScoinInvalidDataRequestException();
        }
        
        GameRanking gameRanking = repo.getOne(id);
        if (ObjectUtils.isEmpty(gameRanking))
            throw new ScoinNotFoundEntityException("Not found Game Ranking by this id");
        repo.delete(gameRanking);
        return "Successfull";
    }

}
