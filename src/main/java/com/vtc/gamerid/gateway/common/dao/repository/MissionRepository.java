package com.vtc.gamerid.gateway.common.dao.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vtc.gamerid.gateway.common.dao.entity.TblMission;

/**
 * Created by phucnguyen on 21/11/2017.
 */
@Repository
public interface MissionRepository extends JpaRepository<TblMission, Long> {

    @Query(value = "from TblMission where status <> 'deleted' "
            + " and (name like %?1% or ?1 is null) "
            + " order by updateOn desc ")
    public List<TblMission> getMission(String searchValue, Pageable pageable);

    @Query(value = "select count(id) from TblMission")
    public int countAllRecordMission();
    
    @Query(value = "from TblMission where award = 'giftcode'")
    public List<TblMission> getAllMissionGiftcodeOrCard();
}
