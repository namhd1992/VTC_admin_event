package com.vtc.gamerid.gateway.splay.transaction.facade;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.vtc.gamerid.gateway.annotations.AuditLogAnnotation;
import com.vtc.gamerid.gateway.annotations.PermissionService;
import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.dao.entity.TblSysConfig;
import com.vtc.gamerid.gateway.common.dao.repository.SysConfigRepository;
import com.vtc.gamerid.gateway.common.dto.response.BaseDataResponse;
import com.vtc.gamerid.gateway.common.ultils.ApiExchangeServiceUtil;
import com.vtc.gamerid.gateway.common.ultils.StringUtils;
import com.vtc.gamerid.gateway.splay.transaction.bean.AdminReportTransRequest;
import com.vtc.gamerid.gateway.splay.transaction.bean.AdminReportTransResponse;

/**
 * Created by phucnguyen on 11/06/2018.
 */
@Component
public class AdminSplayTransFacadeImpl implements AdminSplayTransFacade {
    private Logger logger = LoggerFactory.getLogger(AdminSplayTransFacadeImpl.class);
    @Autowired
    private SysConfigRepository sysConfigDao;

    @Override
    @AuditLogAnnotation(functionName = "admin-getTransFromSplay")
    @PermissionService(functionName = "splaySetting")
    public BaseDataResponse getTransFromSplay(AdminReportTransRequest dataRequest) {
        try{
            //Get base url
            TblSysConfig sysConfig = sysConfigDao.findByKeyName("splay_base_url");
            if(sysConfig == null){
                logger.error("Can not get splay config");
                return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
                        Constants.MESS_UNKNOW);
            }

            //Get api secret
            TblSysConfig api_secret = sysConfigDao.findByKeyName("splay_api_secret");
            if(api_secret == null){
                logger.error("Can not get splay config");
                return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
                        Constants.MESS_UNKNOW);
            }

            //Check date
            if(dataRequest.getFromDateInt() < 0 || dataRequest.getToDateInt() < 0){
                dataRequest.setFromDateInt(1483228800);
                dataRequest.setToDateInt((int) new Date().getTime());
            }

            //Signature
            dataRequest.setSign(StringUtils.toMD5("" + dataRequest.getFromDateInt()
                    + dataRequest.getToDateInt() +  dataRequest.getServiceId() +
                    api_secret.getValue()));

            AdminReportTransResponse adminReportTransResponse = ApiExchangeServiceUtil.
                    post(sysConfig.getValue()+"/api/v1/transactiongamelist", dataRequest, new TypeReference<AdminReportTransResponse>() {});
            
            if(adminReportTransResponse.getCode() != 1){
                logger.error("Call Splay transaction code: "+adminReportTransResponse.getCode() + "/"
                        + adminReportTransResponse.getMessage());
                return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
                        adminReportTransResponse.getMessage());
            }
            return new BaseDataResponse(Constants.STATUS_CODE_TRUE,
                    Constants.SUCCESS, adminReportTransResponse);
        }catch (Exception e){
            logger.error("getTransFromSplay", e);
            return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
                    Constants.MESS_UNKNOW);
        }
    }
}
