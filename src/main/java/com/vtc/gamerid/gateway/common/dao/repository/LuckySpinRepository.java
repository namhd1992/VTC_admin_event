package com.vtc.gamerid.gateway.common.dao.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vtc.gamerid.gateway.common.dao.entity.LuckySpin;

/**
 * Created by phucnguyen on 29/06/2017.
 */
@Repository
public interface LuckySpinRepository extends JpaRepository<LuckySpin, Long> {
    
    @Query(value = "from LuckySpin where status <> 'deleted' "
            + " and (createBy = ?1 or ?1 < 1) "
            + " and (name like %?2% or ?2 is null) "
            + " and (status = ?3 or ?3 is null) "
//            + " and :current between a.startDate and a.endDate "
            + " order by createOn desc")
    public List<LuckySpin> adminGetListSpin(Long userInfoId, String searchValue ,String status, Pageable pageable);

    public LuckySpin findByIdAndStatusNot(Long luckySpinId, String status);

    @Query(value = "select count(a.id) from LuckySpin a where a.status <> 'deleted' "
            + " and (createBy = ?1 or ?1 < 1) "
            + " and (a.name like %?2% or ?2 is null) "
            + " and (a.status = ?3 or ?3 is null) "
//            + " and :current between a.startDate and a.endDate "
            + " order by a.createOn desc")
    public int adminCountGetListSpin(Long userInfoId, String searchValue ,String status);

//    public void sumSpinTimesForLuckySpin(Long luckySpinId, int newSpinTimes);

//    public void subQuantityforItem(int itemId, int newQuantity);

//    public void addTurnInNewDay();
    
}
