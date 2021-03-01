package com.vtc.gamerid.gateway.common.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vtc.gamerid.gateway.common.dao.entity.UserGameRID;

/**
 * Created by phucnguyen on 15/03/2017.
 */
@Repository
public interface UserGameRIDRepository extends JpaRepository<UserGameRID, Long> {
    
    UserGameRID findByUsernameAndPassword(String username, String password);
}
