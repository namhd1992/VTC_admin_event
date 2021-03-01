package com.vtc.gamerid.gateway.common.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vtc.gamerid.gateway.common.dao.entity.TblTransactionHistory;

/**
 * Created by phucnguyen on 16/01/2018.
 */
@Repository
public interface TransactionRepository extends JpaRepository<TblTransactionHistory, Long> {
}
