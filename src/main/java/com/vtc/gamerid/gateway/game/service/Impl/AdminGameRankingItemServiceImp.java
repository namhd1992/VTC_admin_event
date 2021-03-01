/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.game.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.dao.entity.GameRanking;
import com.vtc.gamerid.gateway.common.dao.entity.GameRankingItem;
import com.vtc.gamerid.gateway.common.dao.entity.GameRankingRank;
import com.vtc.gamerid.gateway.common.dao.repository.GameRankingItemRepository;
import com.vtc.gamerid.gateway.common.dto.request.BaseRequest;
import com.vtc.gamerid.gateway.common.dto.request.GameRankingItemCreateAndUpdateRequest;
import com.vtc.gamerid.gateway.common.service.AbstractService;
import com.vtc.gamerid.gateway.exception.FailedToExecuteException;
import com.vtc.gamerid.gateway.exception.ScoinDuplicateEntityException;
import com.vtc.gamerid.gateway.exception.ScoinInvalidDataRequestException;
import com.vtc.gamerid.gateway.exception.ScoinNotFoundEntityException;
import com.vtc.gamerid.gateway.game.service.AdminGameRankingItemService;
import com.vtc.gamerid.gateway.game.service.AdminGameRankingRankService;
import com.vtc.gamerid.gateway.game.service.AdminGameRankingService;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Apr 24, 2020
 */
@Service("gameRankingItemService")
public class AdminGameRankingItemServiceImp
        extends AbstractService<GameRankingItem, Long, GameRankingItemRepository>
        implements AdminGameRankingItemService {
    
    @Autowired
    AdminGameRankingRankService gameRankingRankService;
    
    @Autowired
    AdminGameRankingService gameRankingService;

    @Override
    public List<GameRankingItem> getByGameRankingRank(BaseRequest request, Long rankId) {
        if (ObjectUtils.isEmpty(rankId))
            throw new ScoinInvalidDataRequestException();
        
        GameRankingRank rank = gameRankingRankService.get(rankId);
        if (ObjectUtils.isEmpty(rank))
            throw new ScoinNotFoundEntityException("Not found Rank by this id");
        
        return repo.findByGameRankingRank(rank, new PageRequest(request.getOffset(), request.getLimit()));
    }

    @Override
    public int countByGameRankingRank(Long rankId) {
        if (ObjectUtils.isEmpty(rankId))
            throw new ScoinInvalidDataRequestException();
        GameRankingRank rank = gameRankingRankService.get(rankId);
        if (ObjectUtils.isEmpty(rank))
            throw new ScoinNotFoundEntityException("Not found Rank by this id");
        return repo.countByGameRankingRank(rank);
    }

    @Override
    public GameRankingItem createGameRankingItem(GameRankingItemCreateAndUpdateRequest request) {
        if (ObjectUtils.isEmpty(request) 
                || ObjectUtils.isEmpty(request.getGameRankingId())
                || ObjectUtils.isEmpty(request.getGameRankingRankId())
                || ObjectUtils.isEmpty(request.getItemName())
                || ObjectUtils.isEmpty(request.getItemType())) {
            throw new ScoinInvalidDataRequestException();
        }
        
        GameRanking gameRanking = gameRankingService.get(request.getGameRankingId());
        if (ObjectUtils.isEmpty(gameRanking)) {
            throw new ScoinNotFoundEntityException("Not found Game Ranking by this id");
        }
        
        GameRankingRank rank = gameRankingRankService.get(request.getGameRankingRankId());
        if (ObjectUtils.isEmpty(rank))
            throw new ScoinNotFoundEntityException("Not found Rank by this id");
        
        if(request.getItemType().equals(Constants.GAME_RANKING_ITEM_TYPE_GIFTCODE)) {
            GameRankingItem byType = repo.
                    findByGameRankingRankAndItemType(rank, Constants.GAME_RANKING_ITEM_TYPE_GIFTCODE);
            
            if (!ObjectUtils.isEmpty(byType)) {
                throw new ScoinDuplicateEntityException("Duplidate item has type Giftcode");
            }
        }
        
        GameRankingItem item = new GameRankingItem();
        item.setGameRanking(gameRanking);
        item.setGameRankingRank(rank);
        item.setItemIconUrl(request.getItemIconUrl());
        item.setItemName(request.getItemName());
        item.setItemType(request.getItemType());
        item.setItemQuantity(request.getItemQuantity());
        item.setObjectId(request.getObjectId());
        item.setItemValue(request.getItemValue());
        item.setDescription(request.getDescription());
        item = repo.save(item);
        if (ObjectUtils.isEmpty(item)) {
            throw new FailedToExecuteException();
        }
        
        return item;
    }

    @Override
    public GameRankingItem UpdateGameRankingItem(GameRankingItemCreateAndUpdateRequest request) {
        if (ObjectUtils.isEmpty(request) 
                || ObjectUtils.isEmpty(request.getId())
                || ObjectUtils.isEmpty(request.getGameRankingId())
                || ObjectUtils.isEmpty(request.getGameRankingRankId())
                || ObjectUtils.isEmpty(request.getItemName())
                || ObjectUtils.isEmpty(request.getItemType())) {
            throw new ScoinInvalidDataRequestException();
        }
        
        GameRanking gameRanking = gameRankingService.get(request.getGameRankingId());
        if (ObjectUtils.isEmpty(gameRanking)) {
            throw new ScoinNotFoundEntityException("Not found Game Ranking by this id");
        }
        
        GameRankingRank rank = gameRankingRankService.get(request.getGameRankingRankId());
        if (ObjectUtils.isEmpty(rank))
            throw new ScoinNotFoundEntityException("Not found Rank by this id");
        
        if(request.getItemType().equals(Constants.GAME_RANKING_ITEM_TYPE_GIFTCODE)) {
            GameRankingItem byType = repo.
                    findByGameRankingRankAndItemType(rank, Constants.GAME_RANKING_ITEM_TYPE_GIFTCODE);
            
            if (!ObjectUtils.isEmpty(byType)
                    && !byType.getId().equals(request.getId())) {
                throw new ScoinDuplicateEntityException("Duplidate item has type Giftcode");
            }
        }
        
        GameRankingItem item = repo.getOne(request.getId());
        if (ObjectUtils.isEmpty(item)) {
            throw new ScoinNotFoundEntityException("This item is empty");
        }
        
        item.setGameRanking(gameRanking);
        item.setGameRankingRank(rank);
        item.setItemIconUrl(request.getItemIconUrl());
        item.setItemName(request.getItemName());
        item.setItemType(request.getItemType());
        item.setItemQuantity(request.getItemQuantity());
        item.setObjectId(request.getObjectId());
        item.setItemValue(request.getItemValue());
        item.setDescription(request.getDescription());
        item = repo.save(item);
        if (ObjectUtils.isEmpty(item)) {
            throw new FailedToExecuteException();
        }
        
        return item;
    }

    @Override
    public String deleteGameRankingItem(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new ScoinInvalidDataRequestException();
        }

        GameRankingItem item = repo.getOne(id);
        if (ObjectUtils.isEmpty(item)) {
            throw new ScoinNotFoundEntityException("This item is empty");
        }

        repo.delete(item);
        return "Successfull";
    }
    
    

}
