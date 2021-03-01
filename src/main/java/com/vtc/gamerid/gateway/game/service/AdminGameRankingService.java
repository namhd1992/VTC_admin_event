/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.game.service;

import java.util.List;

import com.vtc.gamerid.gateway.common.dao.entity.GameRanking;
import com.vtc.gamerid.gateway.common.dto.request.GameRankingUpdateAndCreateRequest;
import com.vtc.gamerid.gateway.common.service.AbstractInterfaceService;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Apr 14, 2020
 */
public interface AdminGameRankingService extends AbstractInterfaceService<GameRanking, Long> {
    
    GameRanking getGameRankingById(Long id);
    
    List<GameRanking> getGameRanking();
    
    String createGameRanking(GameRankingUpdateAndCreateRequest request);
    
    String updateGameRanking(GameRankingUpdateAndCreateRequest request);
    
    String deleteGameRanking(Long id);

}
