/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.game.service;

import java.util.List;

import com.vtc.gamerid.gateway.common.dao.entity.GameRankingItem;
import com.vtc.gamerid.gateway.common.dto.request.BaseRequest;
import com.vtc.gamerid.gateway.common.dto.request.GameRankingItemCreateAndUpdateRequest;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Apr 24, 2020
 */
public interface AdminGameRankingItemService {
    
    List<GameRankingItem> getByGameRankingRank(BaseRequest request, Long rankId);
    
    int countByGameRankingRank(Long rankId);
    
    GameRankingItem createGameRankingItem(GameRankingItemCreateAndUpdateRequest request);
    
    GameRankingItem UpdateGameRankingItem(GameRankingItemCreateAndUpdateRequest request);
    
    String deleteGameRankingItem(Long id);

}
