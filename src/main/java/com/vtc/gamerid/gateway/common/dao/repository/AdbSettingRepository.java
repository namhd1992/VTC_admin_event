package com.vtc.gamerid.gateway.common.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vtc.gamerid.gateway.common.dao.entity.TblAdbSetting;

/**
 * Created by phucnguyen on 07/08/2017.
 */
@Repository
public interface AdbSettingRepository extends JpaRepository<TblAdbSetting, Long> {
    
}
