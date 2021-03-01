/***************************************************************************
 * Product 2018 by Quang Dat * 
 **************************************************************************/
package com.vtc.gamerid.gateway.common.service;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.ObjectUtils;

import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.dao.entity.UserGameRID;
import com.vtc.gamerid.gateway.exception.ScoinUnAuthorizationException;
import com.vtc.gamerid.gateway.profile.service.AdminProfileService;

/**
 * Creator : Le Quang Dat
 * Email   : quangdat0993@gmail.com
 * Date    : Aug 27, 2018
 */
public abstract class AbstractService<T, ID extends Serializable, R extends JpaRepository<T, ID>> {
  
    @Autowired
    protected R                 repo;

    protected Logger            logger = LoggerFactory.getLogger(AbstractService.class);

    @Autowired
    protected Environment       env;

    @Autowired
    private AdminProfileService adminProfileService;
    
    public T get(ID id) {
        T t =  repo.findOne(id);
        return t;
    }
    
    protected void verifyAdminToken() {
        UserGameRID admin = adminProfileService.getSessionUserInfo();
        if(ObjectUtils.isEmpty(admin)) {
            throw new ScoinUnAuthorizationException();
        }
    }
    
    protected UserGameRID verifyTokenAdmin() {
        UserGameRID admin = adminProfileService.getSessionUserInfo();
        if (ObjectUtils.isEmpty(admin)
                || !admin.getGroupRole().getName().equals(Constants.ROLE_ADMIN)) {
            throw new ScoinUnAuthorizationException();
        }
        return admin;
    }
    
    protected UserGameRID verifyTokenAdmin(String roleAdminName) {
        UserGameRID admin = adminProfileService.getSessionUserInfo();
        if (ObjectUtils.isEmpty(admin)
                || (!admin.getGroupRole().getName().equals(Constants.ROLE_ADMIN)
                        && !admin.getGroupRole().getName().equals(roleAdminName))) {
            throw new ScoinUnAuthorizationException();
        }
        return admin;
    }
    
    protected UserGameRID verifyTokenUser() {
        UserGameRID userInfo = adminProfileService.getSessionUserInfo();
        if (ObjectUtils.isEmpty(userInfo)) {
            throw new ScoinUnAuthorizationException();
        }
        return userInfo;
    }

}
