/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vtc.gamerid.gateway.common.dao.entity.GameEvent;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Jan 9, 2019
 */
@Repository
public interface GameEventRepository extends JpaRepository<GameEvent, Long> {
    
}
