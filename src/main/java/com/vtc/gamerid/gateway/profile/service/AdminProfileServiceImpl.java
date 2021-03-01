package com.vtc.gamerid.gateway.profile.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.vtc.gamerid.gateway.annotations.AuditLogAnnotation;
import com.vtc.gamerid.gateway.common.dao.entity.TblGroupRole;
import com.vtc.gamerid.gateway.common.dao.entity.UserGameRID;
import com.vtc.gamerid.gateway.common.dao.repository.GroupRoleRepository;
import com.vtc.gamerid.gateway.common.dao.repository.UserGameRIDRepository;
import com.vtc.gamerid.gateway.common.ultils.JsonMapperUtils;
import com.vtc.gamerid.gateway.common.ultils.StringUtils;

/**
 * Created by phucnguyen on 15/03/2017.
 */
@Service
public class AdminProfileServiceImpl implements AdminProfileService {
    private static Logger logger = LoggerFactory.getLogger(AdminProfileServiceImpl.class);
    @Autowired
    private UserGameRIDRepository profileDao;

    @Autowired
    private UserGameRIDRepository gameRIDDao;

    @Autowired
    private GroupRoleRepository roleManagerDao;
//    @Autowired
//    private SysConfigDao sysConfigDao;

    @Override
    public UserGameRID getSessionUserInfo() {
        try{
            if (SecurityContextHolder.getContext().getAuthentication() != null &&
                    SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
                Map<String, Object> dataDetail = StringUtils.
                        convertStringToMap((String) SecurityContextHolder
                        .getContext().getAuthentication().getPrincipal());

                Long userGamerId = Long.parseLong((String) dataDetail.get("id"));
                UserGameRID admin = gameRIDDao.getOne(userGamerId);
                if(admin == null || admin.getGroupRole() == null) return null;
                return admin;
            }
            return null;
        }catch (Exception e){
        }
        return null;
    }

    @Override
    public TblGroupRole getDefaultRole() {
        return roleManagerDao.findOne((long) 1);
    }

    @Override
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UserGameRID updateUserInfo(UserGameRID userInfo) {
        return profileDao.save(userInfo);
    }

    @SuppressWarnings({ "unchecked", "unused" })
    @Override
    @AuditLogAnnotation(functionName = "getProfileFromScoin")
    public Map<String, Object> getProfileFromScoin(String accessToken) {
        try{
            String url = "https://graph.vtcmobile.vn/profile/get?access_token=" + accessToken;
//            String url = "http://sandbox.graph.vtcmobile.vn/profile/get?access_token=" + accessToken;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            logger.info("RESPONSE GET USERINFO FROM SCOIN_TOKEN ==============> {}", response);

            Map<String, Object> result = JsonMapperUtils.convertJsonToObject(response.toString(), Map.class);
            return result;
        }catch (Exception e){
            logger.error(e.toString());
        }
        return null;
    }

//    @Override
//    public SearchUserBeanResponse getUserFromAccountNumber(long accountNumber) {
//        return profileDao.getUserInfoByAccountNumber(accountNumber);
//    }

//    @Override
//    @Transactional(propagation = Propagation.REQUIRED)
//    public void updateBalance(TblUserVTC userVTC) {
//        profileDao.updateBalance(userVTC);
//    }

//    @Override
//    public TblUserVTC getUserScoinByAccountId(long accountId) {
//      return profileDao.getUserScoinByAccountId(accountId);
//    }
}
