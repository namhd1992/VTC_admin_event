package com.vtc.gamerid.gateway.splay.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.amazonaws.util.json.JSONArray;
import com.fasterxml.jackson.core.type.TypeReference;
import com.vtc.gamerid.gateway.annotations.AuditLogAnnotation;
import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.dao.entity.TblSysConfig;
import com.vtc.gamerid.gateway.common.dao.entity.TblUserVTC;
import com.vtc.gamerid.gateway.common.dao.repository.SysConfigRepository;
import com.vtc.gamerid.gateway.common.dto.response.HistoryLoginGameResponse;
import com.vtc.gamerid.gateway.common.dto.response.ScoinBaseResponse;
import com.vtc.gamerid.gateway.common.dto.response.SplayBaseResponse;
import com.vtc.gamerid.gateway.common.ultils.ApiExchangeServiceUtil;
import com.vtc.gamerid.gateway.common.ultils.DateUtils;
import com.vtc.gamerid.gateway.common.ultils.JsonMapperUtils;
import com.vtc.gamerid.gateway.common.ultils.StringUtils;
import com.vtc.gamerid.gateway.exception.FailedToExecuteException;
import com.vtc.gamerid.gateway.exception.ScoinNotFoundEntityException;
import com.vtc.gamerid.gateway.splay.bean.SplayBuyItemBean;
import com.vtc.gamerid.gateway.splay.bean.SplayEventRequest;
import com.vtc.gamerid.gateway.splay.bean.SplayEventResponse;
import com.vtc.gamerid.gateway.splay.bean.SplayProfileRequest;
import com.vtc.gamerid.gateway.splay.bean.SplayTopupBean;

/**
 * Created by phucnguyen on 12/01/2018.
 */
@Service
public class SplayServiceImpl implements SplayService {
    private Logger logger = LoggerFactory.getLogger(SplayServiceImpl.class);

    private static final String SPLAY_API_KEY = "707fece431a0948c498d43e881acd2c5";
    @Autowired
    private SysConfigRepository sysConfigDao;

    @Override
    @AuditLogAnnotation(functionName = "splayTopup")
    public SplayBaseResponse splayTopup(SplayTopupBean dataRequest) {
        try{

            //Get base url
            TblSysConfig sysConfig = sysConfigDao.findByKeyName("splay_base_url");
            if(sysConfig == null)
                return new SplayBaseResponse(false, -1, Constants.MESS_UNKNOW, null);

            //Get api secret
            TblSysConfig api_secret = sysConfigDao.findByKeyName("splay_api_secret");
            if(api_secret == null)
                return new SplayBaseResponse(false, -1, Constants.MESS_UNKNOW, null);

            //Set api_key
            dataRequest.setApi_key(SPLAY_API_KEY);

            //Signature
            dataRequest.setSign(StringUtils.toMD5(dataRequest.getAmount() + ""
                    + dataRequest.getTransId() +  dataRequest.getServiceId() +
                    api_secret.getValue()).toLowerCase());

            SplayBaseResponse splayBaseResponse = ApiExchangeServiceUtil
                    .post(sysConfig.getValue()+"/api/v1/topup", dataRequest, new TypeReference<SplayBaseResponse>() {});
            return splayBaseResponse;
        }catch (Exception e){
            logger.error("splayTopup", e);
        }
        return new SplayBaseResponse(false, -1, Constants.MESS_UNKNOW, null);
    }

    @Override
    @AuditLogAnnotation(functionName = "splayBuyItem")
    public SplayBaseResponse splayBuyItem(SplayBuyItemBean dataRequest) {
        try{
            //Get base url
            TblSysConfig sysConfig = sysConfigDao.findByKeyName("splay_base_url");
            if(sysConfig == null)
                return new SplayBaseResponse(false, -1, Constants.MESS_UNKNOW, null);
            //Set api_key
            dataRequest.setApi_key(SPLAY_API_KEY);

            //Get api secret
            TblSysConfig api_secret = sysConfigDao.findByKeyName("splay_api_secret");
            if(api_secret == null)
                return new SplayBaseResponse(false, -1, Constants.MESS_UNKNOW, null);
            //Signature
            dataRequest.setSign(StringUtils.toMD5(dataRequest.getAmount() + ""
                    + dataRequest.getTransId() +  dataRequest.getServiceId() + dataRequest.getAccess_token()
                    + api_secret.getValue()).toLowerCase());

            SplayBaseResponse splayBaseResponse = ApiExchangeServiceUtil
                    .post(sysConfig.getValue()+"/api/v1/deduct", dataRequest, new TypeReference<SplayBaseResponse>() {});
            
            return splayBaseResponse;
        }catch (Exception e){
            logger.error("splayBuyItem", e);
        }
        return new SplayBaseResponse(false, -1, Constants.MESS_UNKNOW, null);
    }

    @Override
    @AuditLogAnnotation(functionName = "getProfileFromSplay")
    public SplayBaseResponse getProfileFromSplay(SplayProfileRequest dataRequest) {
        try{
            //Get url
            TblSysConfig baseUrlConfig = sysConfigDao.findByKeyName("splay_base_url");
            if(baseUrlConfig == null){
                logger.error("Can not get sysconfig");
                return new SplayBaseResponse(false, -1, Constants.MESS_UNKNOW, null);
            }
            //Set api_key
            dataRequest.setApi_key(SPLAY_API_KEY);

            SplayBaseResponse splayBaseResponse = ApiExchangeServiceUtil
                    .post(baseUrlConfig.getValue() + "/api/v1/getuserinfo", dataRequest, new TypeReference<SplayBaseResponse>() {});
           
            logger.info("UserInfo get from SAPI **** : {}", JsonMapperUtils.toJson(splayBaseResponse));
            if(splayBaseResponse.getError_code() > -1)
                return splayBaseResponse;
            return new SplayBaseResponse(false, -1, Constants.MESS_UNKNOW, null);
        }catch (Exception e){
            logger.error("getProfileFromSplay", e);
            return new SplayBaseResponse(false, -1, Constants.MESS_UNKNOW, null);
        }
    }

    @Override
    @AuditLogAnnotation(functionName = "splayGetEvent")
    public SplayEventResponse splayGetEvent(SplayEventRequest dataRequest) {
        try{
            //Get url
            TblSysConfig baseUrlConfig = sysConfigDao.findByKeyName("splay_base_url");
            if(baseUrlConfig == null){
                logger.error("Can not get sysconfig");
                return new SplayEventResponse(-1, Constants.MESS_UNKNOW);
            }

            SplayEventResponse splayBaseResponse = ApiExchangeServiceUtil
                    .post(baseUrlConfig.getValue()+"/api/v1/listevent", dataRequest, new TypeReference<SplayEventResponse>() {});
            
            return splayBaseResponse;
        }catch (Exception e){
            logger.error("splayGetEvent", e);
            return new SplayEventResponse(-1, Constants.MESS_UNKNOW);
        }
    }

    @Override
    public boolean checkLoginScoinInService(long serviceId, long scoinId, Date fromDate, Date toDate) {
        try{
            SimpleDateFormat dateformatYYYYMMDD = new SimpleDateFormat("yyyyMMdd");
            String url = "https://graph.vtcmobile.vn/accountapi/server/get_accountservice.aspx?";

            //Get api secret
            TblSysConfig api_secret = sysConfigDao.findByKeyName("splay_api_secret");
            if(api_secret == null){
                logger.error("Can not get api key");
                return false;
            }
            url += "api_key="+api_secret.getValue()+"&accountId="+scoinId;

            SplayBaseResponse splayBaseResponse = ApiExchangeServiceUtil.get(url, new TypeReference<SplayBaseResponse>() {});
            if(!splayBaseResponse.isStatus()) return false;
            JSONArray arr = new JSONArray(JsonMapperUtils.toJson(splayBaseResponse.getData()));
            if(arr.length() < 1) return false;

            long tmpToDate = -1;
            long tmpFromDate = -1;
            if(fromDate != null && toDate != null){
                tmpFromDate =  Long.parseLong(dateformatYYYYMMDD.format(fromDate));
                tmpToDate =  Long.parseLong(dateformatYYYYMMDD.format(toDate));
            }


            for (int i=0; i < arr.length(); i++) {
                if((tmpFromDate < 0 || tmpToDate < 1) &&
                        serviceId == arr.getJSONObject(i).getLong("ServiceId"))
                    return true;
                if(serviceId != arr.getJSONObject(i).getLong("ServiceId"))
                    continue;
                if(arr.getJSONObject(i).getLong("LastLogin") >= tmpFromDate
                        && arr.getJSONObject(i).getLong("LastLogin") <= tmpToDate)
                    return true;
            }
            return false;
        }catch (Exception e){
            logger.error("checkLoginScoinInService", e);
            return false;
        }
    }
    
    @Override
    public List<HistoryLoginGameResponse> getLoginGameFromScoin(TblUserVTC userVTC) {
        String url = "https://graph.vtcmobile.vn/accountapi/server/get_accountservice.aspx?";
//        String url = "http://sandbox.graph.vtcmobile.vn/accountapi/server/get_accountservice.aspx?";

        //Get api secret
        TblSysConfig api_secret = sysConfigDao.findByKeyName("splay_api_secret");
        if(api_secret == null){
            throw new ScoinNotFoundEntityException("Not fount System Congfig by this name");
        }
        url += "api_key="+api_secret.getValue()+"&accountId="+userVTC.getScoinId();

        ScoinBaseResponse<List<HistoryLoginGameResponse>> historyLoginGame = null;
        try {
            historyLoginGame = ApiExchangeServiceUtil.get(url, new TypeReference<ScoinBaseResponse<List<HistoryLoginGameResponse>>>() {});
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(historyLoginGame.getStatus() == false
                || historyLoginGame.getError_code() != 0)
            throw new FailedToExecuteException("Don't call to scoin get history login game");
        
        //get last login service (ref)
        if (CollectionUtils.isEmpty(historyLoginGame.getData())) return new ArrayList<>();
        
        List<HistoryLoginGameResponse> historyLogins = historyLoginGame.getData();
        Date lastLogin = historyLogins.get(0).getLastLogin();
        for (HistoryLoginGameResponse historyLogin : historyLogins) {
            if (DateUtils.compareDayOfMonth(lastLogin, historyLogin.getLastLogin()) == -1) {
                lastLogin = historyLogin.getLastLogin();
                userVTC.getUserInfo().setRef(historyLogin.getServiceId().toString());
            }
        }
        
        return historyLogins;
    }
}
