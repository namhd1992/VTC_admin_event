/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dao.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vtc.gamerid.gateway.common.dao.entity.LuckySpinHistory;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * May 19, 2020
 */
public interface LuckySpinHistoryRepository extends JpaRepository<LuckySpinHistory, Long> {
    
    @Query(value = "SELECT sum(value) from LuckySpinHistory s WHERE "
            + " s.luckySpin.id = ?1 "
            + " AND s.createOn BETWEEN ?2 AND ?3"
            + " AND value > 0")
    Long sumValueByLuckySpinAndValueNotNull(long luckySpinId, Date startDate, Date endDate);

}
