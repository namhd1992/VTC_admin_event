/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vtc.gamerid.gateway.common.dao.entity.LuckySpinGift;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Jan 4, 2020
 */
@Repository
public interface LuckySpinGiftRepository extends JpaRepository<LuckySpinGift, Long> {
    
    @Query(value = "select count(id) from LuckySpinGift")
    int countAllLuckySpinGift();

}
