package com.vtc.gamerid.gateway.common.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vtc.gamerid.gateway.common.dao.entity.TblLuckySpinSetting;

/**
 * Created by phucnguyen on 04/07/2017.
 */
@Repository
public interface LuckySpinSettingsRepository extends JpaRepository<TblLuckySpinSetting, Long> {
    public List<TblLuckySpinSetting> findByStatusAndType(String status, String type);

}
