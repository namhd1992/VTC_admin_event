package com.vtc.gamerid.gateway.common.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vtc.gamerid.gateway.common.dao.entity.TblSplayTag;

/**
 * Created by phucnguyen on 03/04/2018.
 */
@Repository
public interface GameTagRepository extends JpaRepository<TblSplayTag, Long> {
    
    @Query(value = "from TblSplayTag where "
            + " (name like ?1 or ?1 is null ) "
            + " and (typeName like %?2% or ?2 is null) "
            + " order by id desc ")
    public List<TblSplayTag> getSplayTagFilter(String searchValue, String typeName);
    
    @Query(value = "select count(id) from TblSplayTag where "
            + " (name like ?1 or ?1 is null ) "
            + " and (typeName like ?2 or ?2 is null) "
            + " order by id desc ")
    public int countSplayTagFilter(String searchValue, String typeName);
    
    public TblSplayTag findByName(String name);
}
