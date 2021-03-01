package com.vtc.gamerid.gateway.signin.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.vtc.gamerid.gateway.common.dao.entity.UserGameRID;
import com.vtc.gamerid.gateway.common.dao.entity.UserInfo;
import com.vtc.gamerid.gateway.common.dao.repository.UserGameRIDRepository;
import com.vtc.gamerid.gateway.common.dao.repository.UserInfoRepository;
import com.vtc.gamerid.gateway.common.ultils.StringUtils;
import com.vtc.gamerid.gateway.exception.RegularExpression;
import com.vtc.gamerid.gateway.exception.ScoinInvalidDataRequestException;
import com.vtc.gamerid.gateway.exception.ScoinUnAuthorizationException;
import com.vtc.gamerid.gateway.profile.service.AdminProfileService;

/**
 * Created by phucnguyen on 09/03/2017.
 */
@Service
public class SignServiceImpl implements SigninService{
    private final Logger logger = LoggerFactory.getLogger(SignServiceImpl.class);

    @Autowired
    private UserInfoRepository userInfoRepo;
    @Autowired
    private AdminProfileService adminProfileService;
    
    @Autowired
    UserGameRIDRepository userGameRIDRepo;

    @Override
    @Transactional
    public UserGameRID signinGameRID(String username, String password) {
        if (!RegularExpression.matchText(username) || !RegularExpression.matchText(password)) {
            throw new ScoinInvalidDataRequestException("Username or password không đúng định dạng");
        }
        
        logger.info("username , username ============ {}",  username + ", " + StringUtils.toMD5(password));
        UserGameRID userGamerId = userGameRIDRepo.findByUsernameAndPassword(username, StringUtils.toMD5(password));
        if (ObjectUtils.isEmpty(userGamerId))
            throw new ScoinUnAuthorizationException();

        return userGamerId;
    }

    @Override
    @Transactional
    public UserInfo signinSSO(UserInfo tblUserInfo) {
        try{
            UserInfo userSearch = userInfoRepo.findByEmail(tblUserInfo.getEmail());
            if(userSearch == null){
                tblUserInfo.setGroupRole(adminProfileService.getDefaultRole());
                return userInfoRepo.save(tblUserInfo);
            }
            userSearch.cloneObject(tblUserInfo);
            return userInfoRepo.save(userSearch);
        }catch (Exception e){
            logger.error(e.toString());
        }
        return null;
    }

}
