package com.vtc.gamerid.gateway.common.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vtc.gamerid.gateway.common.dao.entity.TblCheckinItem;

/**
 * Created by phucnguyen on 04/12/2017.
 */
public interface CheckinItemRepository extends JpaRepository<TblCheckinItem, Long> {

    TblCheckinItem findByDay(int day);

}
