package com.vtc.gamerid.gateway.scoin.authorization.facade;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.dao.entity.TblGroupRole;
import com.vtc.gamerid.gateway.common.dto.response.ValidateBean;
import com.vtc.gamerid.gateway.common.ultils.JsonMapperUtils;
import com.vtc.gamerid.gateway.exception.RequestException;
import com.vtc.gamerid.gateway.profile.facade.AdminProfileFacade;
import com.vtc.gamerid.gateway.profile.service.AdminProfileService;
import com.vtc.gamerid.gateway.scoin.authorization.bean.AdbLoginResponse;
import com.vtc.gamerid.gateway.scoin.authorization.bean.AdbLoginResponseData;
import com.vtc.gamerid.gateway.scoin.authorization.bean.AdbLoginScoinRequest;
import com.vtc.gamerid.gateway.scoin.authorization.service.LoginScoinService;

/**
 * Created by phucnguyen on 22/05/2017.
 */
@Component
public class LoginScoinFacadeImpl implements LoginScoinFacade {
    private Logger logger = LoggerFactory.getLogger(LoginScoinFacadeImpl.class);

    @Autowired
    private LoginScoinService   loginScoinService;

    @Autowired
    private AdminProfileService adminProfileService;

    @Autowired
    AuthenticationProvider      customAuthenticationProvider;
    
    @Autowired
    AdminProfileFacade          adminProfileFacade;

//    @Override
//    @AuditLogAnnotation(functionName = "loginScoinFromClient")
//    public BaseDataResponse loginScoin(Map<String, Object> dataRequest) {
//        try {
//            String code = dataRequest.get("code") + "";
//            String redirect_uri = dataRequest.get("redirect_uri") + "";
//            if (code == null || redirect_uri == null) throw new Exception("Bad request");
//            
//            // get scoin_token of user
//            Map<String, Object> response = loginScoinService.getAccessTokenFromScoin(code, redirect_uri);
//            if (response == null || Boolean.parseBoolean(response.get("status") + "") == false) {
//                return new BaseDataResponse(VariableConstant.STATUS_CODE_FALSE,
//                        VariableConstant.MESS_UNKNOW);
//            }
//
//            //get profile user by scoin_token
//            String profileJson = gson.toJson(adminProfileService.getProfileFromScoin(
//                    response.get("scoinAccessToken") + ""));
//            ScoinBasicBeanResponse profile = gson.fromJson(profileJson, ScoinBasicBeanResponse.class);
//            if (profile.get_code() != 1)
//                return new BaseDataResponse(VariableConstant.STATUS_CODE_FALSE,
//                        VariableConstant.CAN_NOT_GET_PROFILE);
//
//            //Create authentication
//            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
//                    response.get("userId"), 
//                    response.get("userName"));
//            
//            Map<String, Object> detailToken = new HashMap<String, Object>();
//            detailToken.put("loginType", VariableConstant.LOGIN_TYPE_VTC);
//            detailToken.put("loginSource", VariableConstant.LOGIN_FROM_APP);
//            detailToken.put("scoinAccessToken", response.get("scoinAccessToken") + "");
//            detailToken.putAll(extracted(profile));
//            detailToken.putAll(dataRequest);
//            token.setDetails(detailToken);
//            
//            // call servive authentiction custom by module
//            Authentication authentication = this.customAuthenticationProvider.authenticate(token);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            OAuth2AccessToken accessToken = createAccesstoken();
//            if (accessToken == null) throw new RequestException("Signup success but can not automation login.", "F");
//
//            Map<String, Object> dataResponse = new HashMap<>();
//            dataResponse.put("access_token", accessToken.getValue());
//            dataResponse.put("scoinAccessToken", response.get("scoinAccessToken"));
//            dataResponse.put("token_type", "bearer");
//            Map<String, Object> authen = (Map<String, Object>) authentication.getPrincipal();
//            dataResponse.put("id", authen.get("userInfoId"));
//            dataResponse.put("fullName", authen.get("fullName"));
//            dataResponse.put("urlAvatar", authen.get("urlAvatar"));
//            dataResponse.put("email", authen.get("email"));
//            dataResponse.put("username", authen.get("username"));
//            dataResponse.put("firstLogin", authen.get("firstLogin"));
//            
//            //Create login history
//            signinService.createLoginHistory(new TblLoginHistory(new Date(), (int) authen.get("userInfoId"),
//                    (long) authen.get("scoinId"), 1));
//            return new BaseDataResponse(VariableConstant.STATUS_CODE_TRUE,
//                    VariableConstant.SUCCESS, dataResponse);
//        } catch (RequestException re) {
//            SecurityContextHolder.getContext().setAuthentication(null);
//            return new BaseDataResponse(re.getStatusCode(), re.getMessage());
//        } catch (Exception e) {
//            logger.error(e.toString());
//        }
//        return new BaseDataResponse(VariableConstant.STATUS_CODE_FALSE,
//                VariableConstant.BAD_REQ);
//    }

//    @SuppressWarnings("unchecked")
//    private Map<String, ?> extracted(ScoinBasicBeanResponse profile) {
//        return new ObjectMapper().convertValue(profile.get_data(), Map.class);
//    }

    @SuppressWarnings("unchecked")
    @Override
//    @AuditLogAnnotation(functionName = "loginScoinFromAdb")
    public AdbLoginResponse loginScoinFromAdb(AdbLoginScoinRequest adbLoginScoinRequest) {
        try {
            //Validate data
            ValidateBean validateBean = adbLoginScoinRequest.validate();
            if (!validateBean.isSuccess())
                return new AdbLoginResponse(null, null, null, null, Constants.STATUS_CODE_FALSE);

            Map<String, Object> response = loginScoinService.getAccessTokenFromScoin(
                    adbLoginScoinRequest.getCode(), adbLoginScoinRequest.getRedirect_uri());

            if (response == null || Boolean.parseBoolean(response.get("status") + "") == false) {
                return new AdbLoginResponse(null, null, null, null, Constants.STATUS_CODE_FALSE);
            }

            //Call profile
            Map<String, Object> profile = adminProfileService.getProfileFromScoin(
                    response.get("scoinAccessToken") + "");

            //Create authentication
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    response.get("userId"), response.get("userName"));
            Map<String, Object> detailToken = new HashMap<String, Object>();
            detailToken.put("loginType", Constants.LOGIN_TYPE_VTC);
            detailToken.put("loginSource", Constants.LOGIN_FROM_ADB);
            detailToken.put("userLeagueId", -1);
            detailToken.putAll(profile);
            detailToken.putAll(JsonMapperUtils.convertJsonToObject(JsonMapperUtils.toJson(adbLoginScoinRequest), Map.class));
            token.setDetails(detailToken);
            Authentication authentication = this.customAuthenticationProvider.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            OAuth2AccessToken accessToken = createAccesstoken();
            if (accessToken == null) {
                throw new RequestException("Signup success but can not automation login.", "F");
            }

            Map<String, Object> authen = (Map<String, Object>) authentication.getPrincipal();

            AdbLoginResponseData adbLoginResponseData = new AdbLoginResponseData(
                    (String) authen.get("fullName"), (String) authen.get("urlAvatar"),
                    Integer.parseInt(authen.get("userInfoId") + ""), (TblGroupRole) authen.get("groupRole"),
                    Integer.parseInt(authen.get("userInfoId") + ""),
                    (String) authen.get("email"), (String) authen.get("username"));
            AdbLoginResponse adbLoginResponse = new AdbLoginResponse(accessToken.getValue(),
                    response.get("scoinAccessToken") + "", "bearer", adbLoginResponseData,
                    Constants.STATUS_CODE_TRUE);

            return adbLoginResponse;
        } catch (RequestException re) {
            SecurityContextHolder.getContext().setAuthentication(null);
            return new AdbLoginResponse(null, null, null, null, Constants.STATUS_CODE_FALSE);
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return new AdbLoginResponse(null, null, null, null, Constants.STATUS_CODE_FALSE);
    }

    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private JwtAccessTokenConverter tokenEnhancer;
    @Autowired
    private ClientDetailsService clientDetailsService;

    private OAuth2AccessToken createAccesstoken() {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder
                    .getContext().getAuthentication();

            Map<String, String> requestParams = new HashMap<>();
            requestParams.put("grant_type", "password");
            /*requestParams.put("username","phucnguyen");
            requestParams.put("password","123456");*/
            requestParams.put("loginType", Constants.LOGIN_TYPE_VTC);

            String clientId = Constants.CLIENT_ID;

            Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("USER"));

            boolean approved = true;

            Set<String> scope = new HashSet<>();
            scope.add("scope");

            Set<String> resourceIds = new HashSet<>();
            scope.add(Constants.RESOURCE_ID);

            Set<String> responseTypes = new HashSet<>();
            responseTypes.add("code");
            Map<String, Serializable> extensionProperties = new HashMap<>();

            OAuth2Request authorizationRequest = new OAuth2Request(requestParams, clientId, authorities,
                    approved, scope, resourceIds, null, responseTypes, extensionProperties);


            OAuth2Authentication authenticationRequest = new OAuth2Authentication(authorizationRequest, authenticationToken);
            authenticationRequest.setAuthenticated(true);

            DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
            defaultTokenServices.setTokenStore(tokenStore);
            defaultTokenServices.setClientDetailsService(clientDetailsService);
            defaultTokenServices.setTokenEnhancer(tokenEnhancer);
            defaultTokenServices.setSupportRefreshToken(true);

            OAuth2AccessToken accessToken = defaultTokenServices.createAccessToken(authenticationRequest);
            return accessToken;
        } catch (Exception e) {
            logger.error(e.toString());
            return null;
        }
    }
}
