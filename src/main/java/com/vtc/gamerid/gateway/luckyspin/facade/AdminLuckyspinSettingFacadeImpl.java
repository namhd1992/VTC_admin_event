package com.vtc.gamerid.gateway.luckyspin.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vtc.gamerid.gateway.annotations.PermissionService;
import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.dao.entity.TblLuckySpinSetting;
import com.vtc.gamerid.gateway.common.dto.response.BaseDataResponse;
import com.vtc.gamerid.gateway.common.dto.response.ServiceResponse;
import com.vtc.gamerid.gateway.luckyspin.facade._interface.AdminLuckyspinSettingFacade;
import com.vtc.gamerid.gateway.luckyspin.service._interface.AdminLuckySpinSettingsService;

/**
 * Created by phucnguyen on 14/09/2017.
 */
@Component
public class AdminLuckyspinSettingFacadeImpl implements AdminLuckyspinSettingFacade{
    @Autowired
    private AdminLuckySpinSettingsService spinSettingsService;

    @SuppressWarnings("unchecked")
    @Override
    @PermissionService(functionName = "miniGameManager")
    public BaseDataResponse getLuckyspinSetting() {
        //Get response from service
        ServiceResponse serviceResponse = spinSettingsService.getAllSpinSetting();
        if(!serviceResponse.isSuccess())
            return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
                    serviceResponse.getMessage());

        //Get data
        List<TblLuckySpinSetting> result =
                (List<TblLuckySpinSetting>) serviceResponse.getDataResponse();

        //Format data
        for(TblLuckySpinSetting instance: result){
            instance.setLastUpdateBy(null);
        }
        return new BaseDataResponse(Constants.STATUS_CODE_TRUE,
                Constants.SUCCESS, result);
    }

//    @Override
//    @PermissionService(functionName = "miniGameManager")
//    public BaseDataResponse updateLuckyspinSetting(List<LuckySpinSettingBean> dataRequest) {
//        //Get session user
//        TblUserInfo userInfo = adminProfileService.getSessionUserInfo();
//        if(userInfo == null)
//            return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
//                    Constants.NOT_PERMISSION);
//        if(!userInfo.getGroupRole().getName().equals("admin"))
//            return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
//                    Constants.NOT_PERMISSION);
//
//        //Validate data
//        for(LuckySpinSettingBean instance: dataRequest){
//            if(!instance.validate().isSuccess())
//                return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
//                        instance.validate().getMessage());
//        }
//
//        //Get response from service
//        ServiceResponse serviceResponse = spinSettingsService.updateSpinSetting(
//                dataRequest, userInfo);
//        if(!serviceResponse.isSuccess())
//            return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
//                    serviceResponse.getMessage());
//        return new BaseDataResponse(Constants.STATUS_CODE_TRUE,
//                Constants.SUCCESS);
//    }
}
