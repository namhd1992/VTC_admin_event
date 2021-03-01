package com.vtc.gamerid.gateway.role.facade;

import com.vtc.gamerid.gateway.annotations.AuditLogAnnotation;
import com.vtc.gamerid.gateway.annotations.PermissionService;
import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.dao.entity.TblFunction;
import com.vtc.gamerid.gateway.common.dto.response.BaseDataResponse;
import com.vtc.gamerid.gateway.role.facade._interface.FunctionManagerFacade;
import com.vtc.gamerid.gateway.role.service._interface.FunctionManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by phucnguyen on 03/05/2017.
 */
@Component
public class FunctionManagerFacadeImpl implements FunctionManagerFacade {
    private Logger logger = LoggerFactory.getLogger(FunctionManagerFacadeImpl.class);
    @Autowired
    private FunctionManagerService functionManagerService;

    @Override
    @AuditLogAnnotation(functionName = "getListFunction")
    @PermissionService(functionName = "roleManager")
    public BaseDataResponse getListFunction(Map<String, Object> dataRequest) {
        try{
            int limit = 10;
            int offset = 0;
            String status = Constants.STATUS_ACTIVE;

            try{
                limit = Integer.parseInt(dataRequest.get("limit")+"");
                offset = Integer.parseInt(dataRequest.get("offset")+"");
            }catch (Exception e){

            }

            BaseDataResponse baseDataResponse = new BaseDataResponse();
            baseDataResponse.setStatusCode(Constants.STATUS_CODE_TRUE);
            baseDataResponse.setOnlyMessage(Constants.SUCCESS);
            baseDataResponse.setDataArr(functionManagerService.
                    getListFunction(status, limit, offset));
            baseDataResponse.setTotalRecords(functionManagerService.countFunction(status));
            return baseDataResponse;
        }catch (Exception e){
            logger.error(e.toString());
        }
        BaseDataResponse baseDataResponse = new BaseDataResponse(Constants.MESS_UNKNOW,
                Constants.STATUS_CODE_FALSE);
        return baseDataResponse;
    }
}
