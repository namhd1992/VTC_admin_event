package com.vtc.gamerid.gateway.profile.service;

import java.util.Map;

import com.vtc.gamerid.gateway.common.dao.entity.TblGroupRole;
import com.vtc.gamerid.gateway.common.dao.entity.UserGameRID;

/**
 * Created by phucnguyen on 15/03/2017.
 */
public interface AdminProfileService {
    
    public UserGameRID getSessionUserInfo();

    public TblGroupRole getDefaultRole();

    public UserGameRID updateUserInfo(UserGameRID userInfo);

    public Map<String, Object> getProfileFromScoin(String accessToken);

//    public SearchUserBeanResponse getUserFromAccountNumber(long accountNumber);

//    public void updateBalance(TblUserVTC userVTC);

//    public TblUserVTC getUserScoinByAccountId(long accountId);
}
