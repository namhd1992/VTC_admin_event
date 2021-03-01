/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vtc.gamerid.gateway.common.dao.entity.TblLuckySpinItemOfLuckySpin;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Apr 14, 2020
 */
@Repository
public interface LuckySpinItemOfLuckySpinRepository extends JpaRepository<TblLuckySpinItemOfLuckySpin, Long> {

}
