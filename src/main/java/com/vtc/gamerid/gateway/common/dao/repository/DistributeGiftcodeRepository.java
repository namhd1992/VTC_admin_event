package com.vtc.gamerid.gateway.common.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vtc.gamerid.gateway.common.dao.entity.TblDistributeGiftcodeHistory;

/**
 * Created by phucnguyen on 15/05/2018.
 */
@Repository
public interface DistributeGiftcodeRepository extends JpaRepository<TblDistributeGiftcodeHistory, Long>{
//    public List<TblDistributeGiftcodeHistory> getDistributeGiftcodeHistory(
//            DistributeGiftcodeFilter dataRequest);
}
