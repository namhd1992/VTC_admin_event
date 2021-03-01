package com.vtc.gamerid.gateway.common.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vtc.gamerid.gateway.common.dao.entity.TblUserPointGame;

/**
 * Created by phucnguyen on 18/04/2018.
 */
@Repository
public interface UserPointGameRepository extends JpaRepository<TblUserPointGame, Long> {
}
