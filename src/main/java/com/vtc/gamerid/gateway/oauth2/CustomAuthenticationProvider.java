package com.vtc.gamerid.gateway.oauth2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.vtc.gamerid.gateway.annotations.AuditLogAnnotation;
import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.dao.entity.TblFunction;
import com.vtc.gamerid.gateway.common.dao.entity.TblGroupRole;
import com.vtc.gamerid.gateway.common.dao.entity.UserGameRID;
import com.vtc.gamerid.gateway.common.ultils.JsonMapperUtils;
import com.vtc.gamerid.gateway.signin.service.SigninService;
import com.vtc.gamerid.gateway.splay.service.SplayService;

/**
 * Created by phucnguyen on 02/03/2017.
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private List<GrantedAuthority> grantedAuths;
    private final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);
    
    @Autowired
    public SplayService splayService;

    @Autowired
    private SigninService signinService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @SuppressWarnings("unchecked")
    @Override
    @AuditLogAnnotation(functionName = "login")
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // TODO Auto-generated method stub
        grantedAuths = new ArrayList<GrantedAuthority>();
        grantedAuths.add(new SimpleGrantedAuthority(Constants.ROLE_USER));

        try{
            Map<String, Object> detailAuthen = (Map<String, Object>) authentication.getDetails();
            String loginType = null;
            if(detailAuthen != null){
                loginType = (String) detailAuthen.get("loginType");
            }
            Map<String, Object> dataResponse = new HashMap<>();
            try{
                dataResponse.put("deviceId", detailAuthen.get("deviceId"));
                dataResponse.put("deviceType", detailAuthen.get("deviceType"));
                dataResponse.put("language", detailAuthen.get("language"));
                dataResponse.put("appVersion", detailAuthen.get("appVersion"));
            }catch (Exception e){

            }

            //If login with admin CMS
            if(loginType.equals(Constants.LOGIN_TYPE_GAMERID)){
                String username = authentication.getPrincipal().toString();
                String password = authentication.getCredentials().toString();
                UserGameRID userGameRID = signinService.signinGameRID(username, password);
                
                
                if(httpServletRequest.getHeader("origin") != null &&
                        httpServletRequest.getHeader("origin").indexOf(Constants.ADB_GAMERID) > -1){
                    if(userGameRID.getGroupRole() == null ||
                            !checkRoleLoginAdb(userGameRID.getGroupRole())){
                        throw new AuthenticationCredentialsNotFoundException("Can not permission");
                    }

                }
                //
                dataResponse.put("id",userGameRID.getId());
                dataResponse.put("username",userGameRID.getUsername()!=null
                        ?userGameRID.getUsername():"");
//                dataResponse.put("address",userGameRID.getUserInfo().getAddress()!=null
//                        ?userGameRID.getUserInfo().getAddress():"");
//                dataResponse.put("firstName",userGameRID.getUserInfo().getFirstName()!=null
//                        ?userGameRID.getUserInfo().getFirstName():"");
//                dataResponse.put("lastName",userGameRID.getUserInfo().getLastName()!=null
//                        ?userGameRID.getUserInfo().getLastName():"");
//                dataResponse.put("email",userGameRID.getUserInfo().getEmail()!=null
//                        ?userGameRID.getUserInfo().getEmail():"");
//                dataResponse.put("urlAvatar",userGameRID.getUserInfo().getUrlAvatar()!=null
//                        ?userGameRID.getUserInfo().getUrlAvatar():"");
//                dataResponse.put("userInfoId", userGameRID.getUserInfo().getId());
                List<String> groupRole = new ArrayList<>();
                if(!ObjectUtils.isEmpty(userGameRID.getGroupRole())){
                    for(TblFunction instance: userGameRID.getGroupRole().getFunctionList()){
                        groupRole.add(instance.getFunctionName());
                    }
                }

                dataResponse.put("groupRole", groupRole);

                Authentication responseAuth = new UsernamePasswordAuthenticationToken(
                        dataResponse, null, grantedAuths);

                return responseAuth;
            }

            //Login SPLAY user from scoin
//            if(loginType.equals(VariableConstant.LOGIN_TYPE_VTC)){
//                String userName = (String) authentication.getCredentials();
//                int userId = Integer.parseInt(authentication.getPrincipal()+"");
//                TblUserVTC userVTC = loginScoinService.loginScoin(userId, userName, detailAuthen);
//                if(userVTC != null){
//
//                    //Check service lastlogin
////                    loginScoinService.updateRef(userVTC);
//
//                    if(loginSource != null && loginSource.equals(VariableConstant.LOGIN_FROM_ADB)){
//                        if(userVTC.getTblUserInfo() == null ||
//                                userVTC.getTblUserInfo().getGroupRole() == null ||
//                                !checkRoleLoginAdb(userVTC.getTblUserInfo().getGroupRole())){
//                            throw new AuthenticationCredentialsNotFoundException("Can not permission");
//                        }
//                    }
//                    dataResponse.put("userInfoId", userVTC.getTblUserInfo().getId());
//                    dataResponse.put("id", userVTC.getId());
//                    dataResponse.put("scoinId", userVTC.getScoinId());
//                    dataResponse.put("username",userVTC.getUsername());
//                    dataResponse.put("fullName",userVTC.getTblUserInfo().getFullName());
//                    dataResponse.put("urlAvatar",userVTC.getTblUserInfo().getUrlAvatar());
//                    dataResponse.put("email",userVTC.getTblUserInfo().getEmail());
//                    
//                    //check login game history
//                    List<HistoryLoginGameResponse> loginHistoryGames = splayService
//                            .getLoginGameFromScoin(userVTC);
//                    
//                    
//                    if (!CollectionUtils.isEmpty(loginHistoryGames)) {
//                      loginHistoryGames.forEach(loginHistoryGame -> {
////                          String lastLoginGame = DateUtils.toStringFormDate(loginHistoryGame.getLastLogin(), DateUtils.DATE_MYSQL_FORMAT);
//                            if (ObjectUtils.isEmpty(LoginGameHistoryRepository.getByUserInfoAndScoinGameIdAndDay(
//                                            userVTC.getTblUserInfo().getId(),
//                                            loginHistoryGame.getServiceId(), 
//                                            loginHistoryGame.getLastLogin()))) {
//                                
//                                TblLoginGameHistory history = new TblLoginGameHistory();
//                                
//                                logger.info("service ID new game ======================== > : {}", loginHistoryGame.getServiceId());
//                                history.setUserInfo(userVTC.getTblUserInfo());
//                                history.setScoinGameId(loginHistoryGame.getServiceId());
//                                history.setFirstLogin(loginHistoryGame.getFirstLogin());
//                                history.setLastLogin(loginHistoryGame.getLastLogin());
//                                
//                                LoginGameHistoryRepository.create(history);
//                            }
//                        });
//                    }
//
//                    List<String> groupRole = new ArrayList<>();
//                    if(userVTC.getTblUserInfo().getGroupRole() != null){
//                        for(TblFunction instance: userVTC.getTblUserInfo()
//                                .getGroupRole().getFunctionList()){
//                            groupRole.add(instance.getFunctionName());
//                        }
//                    }
//                    dataResponse.put("groupRole", groupRole);
//                    dataResponse.put("firstLogin", userVTC.isFirstLogin());
//                    Authentication responseAuth = new UsernamePasswordAuthenticationToken(
//                            dataResponse, null, grantedAuths);
//                    return responseAuth;
//                }
//            }

            throw new BadCredentialsException(Constants.BAD_REQ);

        }catch (AuthenticationCredentialsNotFoundException notAuthen){
            logger.error(notAuthen.toString());
            return null;
        }catch (Exception e){
            logger.error(e.toString());
            return null;
        }

    }

    private boolean checkRoleLoginAdb(TblGroupRole groupRole) {
        try{
            for(TblFunction instance: groupRole.getFunctionList()){
                if(instance.getFunctionName().equals("loginAdb")) return true;
            }
        }catch (Exception e){
            logger.error(e.toString());
        }
        return false;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
