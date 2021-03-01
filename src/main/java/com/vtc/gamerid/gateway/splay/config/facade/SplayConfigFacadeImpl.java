package com.vtc.gamerid.gateway.splay.config.facade;

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
import com.vtc.gamerid.gateway.splay.config.bean.SplayConfigResponse;

/**
 * Created by phucnguyen on 11/06/2018.
 */
@Component
public class SplayConfigFacadeImpl implements SplayConfigFacade {
    private Logger logger = LoggerFactory.getLogger(SplayConfigFacadeImpl.class);
    @Autowired
    private SysConfigRepository sysConfigDao;

    @Override
    @AuditLogAnnotation(functionName = "admin-getSplayConfig")
    @PermissionService(functionName = "splaySetting")
    public BaseDataResponse getSplayConfig() {
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

            SplayConfigResponse splayBaseResponse = ApiExchangeServiceUtil.get(sysConfig.getValue()+"/api/v1/getconfig", new TypeReference<SplayConfigResponse>() {});
            if(splayBaseResponse.getCode() != 1){
                logger.error("Call Splay config error code: "+splayBaseResponse.getCode() + "/"
                        + splayBaseResponse.getMessage());
                return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
                        splayBaseResponse.getMessage());
            }
            return new BaseDataResponse(Constants.STATUS_CODE_TRUE,
                    Constants.SUCCESS, splayBaseResponse);
        }catch (Exception e){
            logger.error("getSplayConfig", e);
            return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
                    Constants.MESS_UNKNOW);
        }
    }
}
