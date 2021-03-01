package com.vtc.gamerid.gateway.common.dao.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vtc.gamerid.gateway.common.dao.entity.TblFunction;

/**
 * Created by phucnguyen on 03/05/2017.
 */
@Repository
public interface FunctionManagerRepository extends JpaRepository<TblFunction, Long> {
    public List<TblFunction> findByStatus(String status, Pageable pageable);

    public int countByStatus(String status);

}
