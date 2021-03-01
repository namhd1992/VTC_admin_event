package com.vtc.gamerid.gateway.common.dao.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vtc.gamerid.gateway.common.dao.entity.LuckySpin;
import com.vtc.gamerid.gateway.common.dto.response.ResultReportSpinGetResponseData;

/**
 * Created by phucnguyen on 14/07/2017.
 */
@Repository
public interface AdminReportSpinRepository extends JpaRepository<LuckySpin, Long>{
    
    @Query(value = "select new com.vtc.gamerid.gateway.common.dto.response.ResultReportSpinGetResponseData"
            + "(s.luckySpin.id, COUNT(s.item),s.item.type, s.item.value)"
            + " from LuckySpinHistory s" 
            + " WHERE s.luckySpin.id = ?1"
            + " AND s.createOn BETWEEN ?2 AND ?3"
            + " GROUP BY s.item.id ORDER BY s.item.value DESC" )
    public List<ResultReportSpinGetResponseData> getResultReportSpin(long luckySpinId , Date startDate, Date endDate);

//    public Map<String, Long> countTotalSpinReport(SpinReportBean spinReportBean);
    
    @Query(value = "select count(id) from TblLuckySpinUser where luckySpin.id=?1 and createOn between ?2 and ?3")
    public int countAllUserJoinEventBetweenDate(long luckySpinId, Date startDate, Date endDate);
}
