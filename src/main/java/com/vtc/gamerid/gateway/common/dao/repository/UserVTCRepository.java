package com.vtc.gamerid.gateway.common.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vtc.gamerid.gateway.common.dao.entity.TblUserVTC;

/**
 * Created by phucnguyen on 22/05/2017.
 */
@Repository
public interface UserVTCRepository extends JpaRepository<TblUserVTC, Long> {

    public TblUserVTC findByScoinId(long scoinId);
}
