package com.vtc.gamerid.gateway.signin.facade;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.dao.entity.UserGameRID;
import com.vtc.gamerid.gateway.common.dao.entity.UserInfo;
import com.vtc.gamerid.gateway.common.dto.response.BaseDataResponse;
import com.vtc.gamerid.gateway.common.ultils.StringUtils;
import com.vtc.gamerid.gateway.exception.RegularExpression;
import com.vtc.gamerid.gateway.exception.RequestException;
import com.vtc.gamerid.gateway.signin.facade._interface.SigninFacade;
import com.vtc.gamerid.gateway.signin.service.SigninService;

/**
 * Created by phucnguyen on 09/03/2017.
 */
@Component
public class SigninFacadeImpl implements SigninFacade {
    private final Logger logger = LoggerFactory.getLogger(SigninFacadeImpl.class);

    @Autowired
    private SigninService signinService;

    @Override
    public BaseDataResponse signinGameRID(String username, String password) {
        BaseDataResponse baseDataResponse = new BaseDataResponse();
        try {
            if (!RegularExpression.matchText(username) || !RegularExpression.matchText(password)) {
                throw new RequestException("Username or password invalidate",
                        Constants.STATUS_CODE_FALSE);
            }
            UserGameRID userGameRID = signinService.signinGameRID(
                    username, StringUtils.toMD5(password));
            logger.info("username , username ============ {}",  username + ", " + StringUtils.toMD5(password));
            if (userGameRID == null)
                throw new RequestException("Username or password not exits",
                        Constants.STATUS_CODE_FALSE);
            baseDataResponse.setStatusCode(Constants.STATUS_CODE_TRUE);
            baseDataResponse.setOnlyMessage(Constants.SUCCESS);

            Map<String, Object> dateResponse = new HashMap<String, Object>();
            dateResponse.put("data", userGameRID);
            baseDataResponse.setData(dateResponse);
            return baseDataResponse;
        } catch (RequestException re) {
            baseDataResponse.setStatusCode(re.getStatusCode());
            baseDataResponse.setOnlyMessage(re.getMessage());
        }
        return baseDataResponse;
    }

    @Override
    public BaseDataResponse signinWithSSO(UserInfo tblUserInfo) {
        BaseDataResponse baseDataResponse = new BaseDataResponse();
        try {
            if (tblUserInfo.getEmail() == null) {
                throw new RequestException(Constants.BAD_REQ,
                        Constants.STATUS_CODE_FALSE);
            }
            UserInfo userResult = signinService.signinSSO(tblUserInfo);
            if (userResult == null) throw new RequestException(Constants.MESS_UNKNOW,
                    Constants.STATUS_CODE_FALSE);

            baseDataResponse.setStatusCode(Constants.STATUS_CODE_TRUE);
            baseDataResponse.setOnlyMessage(Constants.SUCCESS);

            Map<String, Object> dataResponse = new HashMap<String, Object>();
            dataResponse.put("data", userResult);
            baseDataResponse.setData(dataResponse);
            return baseDataResponse;
        } catch (RequestException re) {
            baseDataResponse.setStatusCode(re.getStatusCode());
            baseDataResponse.setOnlyMessage(re.getMessage());
        }
        return baseDataResponse;
    }
}
