package com.vtc.gamerid.gateway.adbSetting.facade;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vtc.gamerid.gateway.adbSetting.bean.AdbSettingBean;
import com.vtc.gamerid.gateway.adbSetting.bean.AdbSettingRequest;
import com.vtc.gamerid.gateway.adbSetting.service.AdbSettingService;
import com.vtc.gamerid.gateway.annotations.AuditLogAnnotation;
import com.vtc.gamerid.gateway.annotations.PermissionService;
import com.vtc.gamerid.gateway.common.dao.entity.TblAdbSetting;
import com.vtc.gamerid.gateway.common.dto.response.ValidateBean;
import com.vtc.gamerid.gateway.common.service.AbstractService;
import com.vtc.gamerid.gateway.exception.ScoinInvalidDataRequestException;

/**
 * Created by phucnguyen on 07/08/2017.
 */
@Component
public class AdbSettingFacadeImpl implements AdbSettingFacade{
    
  protected Logger            logger = LoggerFactory.getLogger(AbstractService.class);
  
    @Autowired
    private AdbSettingService adbSettingService;

    @Override
    @PermissionService(functionName = "adbSettingManager")
    public List<TblAdbSetting> getAdbSettings() {
        return adbSettingService.getAdbSetting();
    }

    @Override
    @PermissionService(functionName = "adbSettingManager")
    @AuditLogAnnotation(functionName = "updateAdbSettings")
    public List<TblAdbSetting> updateAdbSettings(AdbSettingRequest adbSettingRequest) {
        //Validate
        for(AdbSettingBean instance: adbSettingRequest.getDataArr()){
            ValidateBean validateBean = instance.validate();
            if(!validateBean.isSuccess())
                throw new ScoinInvalidDataRequestException();
        }

        return adbSettingService.updateAdbSetting(adbSettingRequest.getDataArr());
    }
}
