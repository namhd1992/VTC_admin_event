package com.vtc.gamerid.gateway.signin.service;

import com.vtc.gamerid.gateway.common.dao.entity.UserGameRID;
import com.vtc.gamerid.gateway.common.dao.entity.UserInfo;

/**
 * Created by phucnguyen on 07/03/2017.
 */
public interface SigninService {
    public UserGameRID signinGameRID(String username, String password);

    public UserInfo signinSSO(UserInfo tblUserInfo);

}
