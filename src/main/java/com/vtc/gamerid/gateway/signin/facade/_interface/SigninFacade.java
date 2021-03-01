package com.vtc.gamerid.gateway.signin.facade._interface;

import com.vtc.gamerid.gateway.common.dao.entity.UserInfo;
import com.vtc.gamerid.gateway.common.dto.response.BaseDataResponse;

/**
 * Created by phucnguyen on 09/03/2017.
 */
public interface SigninFacade {
    public BaseDataResponse signinGameRID(String username, String password);

    public BaseDataResponse signinWithSSO(UserInfo tblUserInfo);
}
