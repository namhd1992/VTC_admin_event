package com.vtc.gamerid.gateway.scoin.authorization.service;

import static com.vtc.gamerid.gateway.common.Constants.sysConfig;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.jboss.aerogear.security.otp.api.Base32;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.amazonaws.util.json.JSONArray;
import com.amazonaws.util.json.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.dao.entity.TblGroupRole;
import com.vtc.gamerid.gateway.common.dao.entity.TblSysConfig;
import com.vtc.gamerid.gateway.common.dao.entity.UserInfo;
import com.vtc.gamerid.gateway.common.dao.entity.TblUserVTC;
import com.vtc.gamerid.gateway.common.dao.repository.GroupRoleRepository;
import com.vtc.gamerid.gateway.common.dao.repository.SysConfigRepository;
import com.vtc.gamerid.gateway.common.dao.repository.UserInfoRepository;
import com.vtc.gamerid.gateway.common.dao.repository.UserVTCRepository;
import com.vtc.gamerid.gateway.common.dto.response.SplayBaseResponse;
import com.vtc.gamerid.gateway.common.ultils.ApiExchangeServiceUtil;
import com.vtc.gamerid.gateway.common.ultils.JsonMapperUtils;
import com.vtc.gamerid.gateway.exception.RollBackTransaction;

/**
 * Created by phucnguyen on 22/05/2017.
 */
@Service
public class LoginScoinServiceImpl implements LoginScoinService {
    private Logger logger = LoggerFactory.getLogger(LoginScoinServiceImpl.class);

    @Autowired
    private UserVTCRepository loginScoinDao;
    @Autowired
    private GroupRoleRepository roleManagerDao;
    @Autowired
    private UserInfoRepository userInfoRepo;
    @Autowired
    private SysConfigRepository sysConfigDao;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public Map<String, Object> getAccessTokenFromScoin(String code, String redirect_uri) {
        Map<String, Object> dataResponse = new HashedMap();
        try {
            StringBuilder urlParameters = new StringBuilder("client_id=" +
                    sysConfig.get("scoin_live_clientId"));
//            StringBuilder urlParameters = new StringBuilder("client_id=" +
//                    sysConfig.get("scoin_sandbox_clientId"));
            urlParameters.append("&code=" + code);
            urlParameters.append("&client_secret=" + sysConfig.get("scoin_live_clientSecret"));
//            urlParameters.append("&client_secret=" + sysConfig.get("scoin_sandbox_clientSecret"));
            urlParameters.append("&agencyid=" + sysConfig.get("scoin_live_agencyId"));
            urlParameters.append("&redirect_uri=" + redirect_uri);
            byte[] postData = urlParameters.toString().getBytes(StandardCharsets.UTF_8);
            int postDataLength = postData.length;
            String request = "https://graph.vtcmobile.vn/oauth/access_token";
//            String request = "http://sandbox.graph.vtcmobile.vn/oauth/access_token";
            URL url = new URL(request);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");
            conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
            conn.setUseCaches(false);

            logger.info("Request authorization scoin =========> " + urlParameters.toString());
            try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
                wr.write(postData);
            }

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            logger.info("Response authorization scoin ===> " + response.toString());
            JSONObject jsonObject = new JSONObject(response.toString());

            dataResponse.put("scoinAccessToken", jsonObject.getString("access_token"));
            dataResponse.put("userId", jsonObject.getInt("UserId"));
            dataResponse.put("userName", jsonObject.getString("User"));
            dataResponse.put("status", true);
            return dataResponse;
        } catch (Exception e) {
            logger.error(e.toString());
        }
        dataResponse.put("status", false);
        return null;
    }

    @SuppressWarnings("cast")
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public TblUserVTC loginScoin(String userName, Map<String, Object> detailAuthen) {
        try {
            long scoinId = (long) Double.parseDouble(detailAuthen.get("accountId") + "");
            TblUserVTC userVTC = loginScoinDao.findByScoinId(scoinId);

//            int scoinBalance = 9;
            if (userVTC != null) {
                if (userVTC.getUserInfo().getOtpKey() == null) {
                    userVTC.getUserInfo().setOtpKey(Base32.random());
                }
                userVTC.getUserInfo().setDeviceType(
                        (String) detailAuthen.get("deviceType"));
                userVTC.getUserInfo().setDeviceId(
                        (String) detailAuthen.get("deviceId"));
                
                //update phone number and email
                if (!ObjectUtils.isEmpty(detailAuthen.get("mobile"))) {
                    if (ObjectUtils.isEmpty(userVTC.getUserInfo().getPhoneNumber())
                            || !userVTC.getUserInfo().getPhoneNumber().equals((String) detailAuthen.get("mobile")))
                        userVTC.getUserInfo().setPhoneNumber(detailAuthen.get("mobile").toString());
                }
                
                if (!ObjectUtils.isEmpty(detailAuthen.get("email"))) {
                    if (ObjectUtils.isEmpty(userVTC.getUserInfo().getEmail())
                            || !userVTC.getUserInfo().getEmail().equals(detailAuthen.get("email").toString()))
                        userVTC.getUserInfo().setEmail(detailAuthen.get("email").toString());
                }
                
                loginScoinDao.save(userVTC);

                //Get channel follow
//                List<TblChannel> getChannelFollow = channelManagerDao.getChannelFollow(userVTC.getUserInfo().getId());
//                if(getChannelFollow.size() < 1)
//                    userVTC.setFirstLogin(true);
//                else userVTC.setFirstLogin(false);
                return userVTC;
            }

//            int userLeagueId = Integer.parseInt(detailAuthen.get("userLeagueId")+"");

            UserInfo userInfo = new UserInfo();
            /*if(userLeagueId > 0){
                userInfo = profileDao.getUserInfoById(userLeagueId);
                if(userInfo == null){
                    logger.error("Can not find user league");
                    RollBackTransaction.callRollback();
                    return null;
                }
            }else{*/
            userInfo.setEmail((String) detailAuthen.get("email"));
            String fullName = (String) detailAuthen.get("facebookName");
            if (fullName == null) {
                fullName = (String) detailAuthen.get("accountName");
            }
//            String gender = Double.parseDouble(dataInfo.get("gender") + "") < 1 ? "male" : "female";
            userInfo.setFullName(fullName);
            if(userInfo.getPhoneNumber() == null || userInfo.getPhoneNumber().length() < 1)
                userInfo.setPhoneNumber((String) detailAuthen.get("mobile"));
//            userInfo.setGender(gender);
            userInfo.setOtpKey(Base32.random());
            userInfo.setDeviceType((String) detailAuthen.get("deviceType"));
            userInfo.setDeviceId((String) detailAuthen.get("deviceId"));
//            userInfo.setSplayPoint(((Double) detailAuthen.get("rewardPoints")).longValue());

            //Get role default
            TblGroupRole groupRole = roleManagerDao.findByName(Constants.ROLE_USER);
            userInfo.setGroupRole(groupRole);
            //Save user info
            userInfo = userInfoRepo.save(userInfo);
//            }

            //Create user scoin
            TblUserVTC createUserVTC = new TblUserVTC(scoinId, userName, Constants.STATUS_ACTIVE
                    , new Date(), userInfo);
            createUserVTC = loginScoinDao.save(createUserVTC);
            createUserVTC.setFirstLogin(true);
            return createUserVTC;
        } catch (Exception e) {
            logger.error("loginScoin", e);
            RollBackTransaction.callRollback();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public TblUserVTC checkUserScoin(SplayBaseResponse splayBaseResponse) {
        try{
            Map<String, Object> dataRequest = (Map<String, Object>) splayBaseResponse.getData();
            long scoinId = (long) Double.parseDouble(dataRequest.get("accountId") + "");

            TblUserVTC userVTC = loginScoinDao.findByScoinId(scoinId);
            if (userVTC != null) {
                if (userVTC.getUserInfo().getOtpKey() == null) {
                    userVTC.getUserInfo().setOtpKey(Base32.random());
                }
                loginScoinDao.save(userVTC);
                return userVTC;
            }

            UserInfo userInfo = new UserInfo();
            userInfo.setEmail((String) dataRequest.get("email"));
            String fullName = (String) dataRequest.get("facebookName");
            if (fullName == null) {
                fullName = (String) dataRequest.get("accountName");
            }
//            String gender = Double.parseDouble(dataInfo.get("gender") + "") < 1 ? "male" : "female";
            userInfo.setFullName(fullName);
            if(userInfo.getPhoneNumber() == null || userInfo.getPhoneNumber().length() < 1)
                userInfo.setPhoneNumber((String) dataRequest.get("phoneNumber"));
//            userInfo.setGender(gender);
            userInfo.setOtpKey(Base32.random());

            //Get role default
            TblGroupRole groupRole = roleManagerDao.findByName(Constants.ROLE_USER);
            userInfo.setGroupRole(groupRole);
            //Save user info
            userInfo = userInfoRepo.save(userInfo);

            //Create user scoin
            TblUserVTC createUserVTC = new TblUserVTC(scoinId, fullName, Constants.STATUS_ACTIVE
                    , new Date(), userInfo);
            createUserVTC = loginScoinDao.save(createUserVTC);
            createUserVTC.setFirstLogin(true);
            return createUserVTC;
        }catch (Exception e){
            RollBackTransaction.callRollback();
            logger.error("checkUserScoin", e);
        }
        return null;
    }

    @Override
    public void updateRef(TblUserVTC userVTC) {
        try{
            if(userVTC.getUserInfo().getRef() != null)
                return;
            String url = "https://graph.vtcmobile.vn/accountapi/server/get_accountservice.aspx?";
//            String url = "http://sandbox.graph.vtcmobile.vn/accountapi/server/get_accountservice.aspx?";
            //Get api secret
            TblSysConfig api_secret = sysConfigDao.findByKeyName("splay_api_secret");
            if(api_secret == null){
                logger.error("Can not get api key");
                return;
            }
            url += "api_key="+api_secret.getValue()+"&accountId="+userVTC.getScoinId();

            SplayBaseResponse splayBaseResponse = ApiExchangeServiceUtil.get(url, new TypeReference<SplayBaseResponse>() {});
            if(!splayBaseResponse.isStatus()) return;
            
            JSONArray arr = new JSONArray(JsonMapperUtils.toJson(splayBaseResponse.getData()));
            if(arr.length() < 1) return;
            
            userVTC.getUserInfo().setRef(arr.getJSONObject(0).getString("ServiceId"));
            
            long tmp = arr.getJSONObject(0).getInt("LastLogin");
            for (int i=1; i < arr.length(); i++) {
                if(arr.getJSONObject(i).getString("ServiceId").equals("330223"))
                    continue;
                if(tmp < arr.getJSONObject(i).getLong("LastLogin")){
                    userVTC.getUserInfo().setRef(arr.getJSONObject(i).getString("ServiceId"));
                    tmp = arr.getJSONObject(i).getInt("LastLogin");
                }else if(arr.getJSONObject(0).getString("ServiceId").equals("330223")){
                    userVTC.getUserInfo().setRef(arr.getJSONObject(i).getString("ServiceId"));
                    tmp = arr.getJSONObject(i).getInt("LastLogin");
                }
            }
            //get service user last login
            userInfoRepo.save(userVTC.getUserInfo());
        }catch (Exception e){
            logger.error("updateRef", e);
        }
    }
}
