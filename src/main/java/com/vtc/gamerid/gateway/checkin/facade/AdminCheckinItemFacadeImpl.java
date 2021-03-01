package com.vtc.gamerid.gateway.checkin.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vtc.gamerid.gateway.annotations.AuditLogAnnotation;
import com.vtc.gamerid.gateway.annotations.PermissionService;
import com.vtc.gamerid.gateway.checkin.bean.AdminCheckinItemBean;
import com.vtc.gamerid.gateway.checkin.service.AdminCheckinItemService;
import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.dao.entity.TblCheckinItem;
import com.vtc.gamerid.gateway.common.dto.response.BaseDataResponse;
import com.vtc.gamerid.gateway.common.dto.response.ServiceResponse;
import com.vtc.gamerid.gateway.common.dto.response.ValidateBean;

/**
 * Created by phucnguyen on 05/12/2017.
 */
@Component
public class AdminCheckinItemFacadeImpl implements AdminCheckinItemFacade {
    @Autowired
    private AdminCheckinItemService adminCheckinItemService;

    @Override
    @AuditLogAnnotation(functionName = "updateCheckinItem")
    @PermissionService(functionName = "checkinManager")
    public BaseDataResponse updateCheckinItem(AdminCheckinItemBean dataRequest) {
        //Validate data
        ValidateBean validateBean = dataRequest.validate();
        if(!validateBean.isSuccess())
            return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
                    validateBean.getMessage());

        //Get service response
        ServiceResponse serviceResponse = adminCheckinItemService.updateCheckinItem(dataRequest);
        if(!serviceResponse.isSuccess())
            return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
                    serviceResponse.getMessage());

        return new BaseDataResponse(Constants.STATUS_CODE_TRUE,
                Constants.SUCCESS);
    }

    @SuppressWarnings("unchecked")
    @Override
    @AuditLogAnnotation(functionName = "getListItemCheckin")
    @PermissionService(functionName = "checkinManager")
    public BaseDataResponse getListItemCheckin() {
        //Get service response
        ServiceResponse serviceResponse = adminCheckinItemService.getCheckinItem();
        if(!serviceResponse.isSuccess())
            return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
                    serviceResponse.getMessage());
        return new BaseDataResponse(Constants.STATUS_CODE_TRUE,
                Constants.SUCCESS, (List<TblCheckinItem>) serviceResponse.getDataResponse());
    }
}
