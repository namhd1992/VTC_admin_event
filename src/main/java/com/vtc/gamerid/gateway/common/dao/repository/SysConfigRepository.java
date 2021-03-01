package com.vtc.gamerid.gateway.common.dao.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vtc.gamerid.gateway.common.dao.entity.TblSysConfig;

/**
 * Created by phucnguyen on 15/03/2017.
 */
@Repository
public interface SysConfigRepository extends JpaRepository<TblSysConfig, Long> {

    public TblSysConfig findByKeyName(String keyName);

}
