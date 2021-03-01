package com.vtc.gamerid.gateway.common.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vtc.gamerid.gateway.common.dao.entity.TblLuckySpinItem;

/**
 * Created by phucnguyen on 23/06/2017.
 */
@Repository
public interface LuckySpinItemRepository extends JpaRepository<TblLuckySpinItem, Long> {
    
//    List<TblLuckySpinItem> findAllOrderByType();
    
    @Query(value = "select count(id) from TblLuckySpinItem where status <> ?1")
    int countAllItemOfSpin(String status);
    
//    public TblLuckySpinItem getItemSpinById(Long itemId, Long userId);
    
//    public List<TblLuckySpinItem> getItemOfSpinByPublisher(SpinItemSqlRequest spinItemSqlRequest);
//    public int countItemOfSpinByPublisher(SpinItemSqlRequest spinItemSqlRequest);

    public List<TblLuckySpinItem> findByHighLights(boolean highLight);
}
