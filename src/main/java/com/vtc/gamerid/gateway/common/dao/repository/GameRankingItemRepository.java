/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dao.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vtc.gamerid.gateway.common.dao.entity.GameRankingItem;
import com.vtc.gamerid.gateway.common.dao.entity.GameRankingRank;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Apr 24, 2020
 */
@Repository
public interface GameRankingItemRepository extends JpaRepository<GameRankingItem, Long> {

    List<GameRankingItem> findByGameRankingRank(GameRankingRank gameRankingRank, Pageable pageable);
    
    GameRankingItem findByGameRankingRankAndItemType(GameRankingRank gameRankingRank, String itemType);
    
    int countByGameRankingRank(GameRankingRank gameRankingRank);

}
