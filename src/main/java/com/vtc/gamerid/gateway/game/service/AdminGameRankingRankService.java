/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.game.service;

import java.util.List;

import com.vtc.gamerid.gateway.common.dao.entity.GameRankingRank;
import com.vtc.gamerid.gateway.common.dto.request.BaseRequest;
import com.vtc.gamerid.gateway.common.dto.request.UpdateAndCreateGameRankingRankRequest;
import com.vtc.gamerid.gateway.common.service.AbstractInterfaceService;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Apr 16, 2020
 */
public interface AdminGameRankingRankService extends AbstractInterfaceService<GameRankingRank, Long> {
  
//    GameRankingRank getGameRankingRankById(Long id);
    
    List<GameRankingRank> getByGameRanking(BaseRequest request, Long gameRankingId);
    
    int countByGameRanking(Long gameRankingId);
    
    GameRankingRank updateRankingRank(UpdateAndCreateGameRankingRankRequest request);
    
    GameRankingRank createRankingRank(UpdateAndCreateGameRankingRankRequest request);
    
    String deleteGameRankingRank(Long gameRankingRankId);

}
