package com.vtc.gamerid.gateway.common.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vtc.gamerid.gateway.common.dao.entity.TblAuditLog;

/**
 * Created by phucnguyen on 03/03/2017.
 */
public interface AuditlogRepository extends JpaRepository<TblAuditLog, Long> {
}
