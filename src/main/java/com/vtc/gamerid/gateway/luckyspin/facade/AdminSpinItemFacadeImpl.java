package com.vtc.gamerid.gateway.luckyspin.facade;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vtc.gamerid.gateway.annotations.AuditLogAnnotation;
import com.vtc.gamerid.gateway.annotations.PermissionService;
import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.dao.entity.TblLuckySpinItem;
import com.vtc.gamerid.gateway.common.dao.entity.UserGameRID;
import com.vtc.gamerid.gateway.common.dao.repository.LuckySpinItemRepository;
import com.vtc.gamerid.gateway.common.dto.response.BaseDataResponse;
import com.vtc.gamerid.gateway.common.dto.response.ServiceResponse;
import com.vtc.gamerid.gateway.common.dto.response.ValidateBean;
import com.vtc.gamerid.gateway.luckyspin.bean.SpinItemBeanRequest;
import com.vtc.gamerid.gateway.luckyspin.bean.SpinItemSqlRequest;
import com.vtc.gamerid.gateway.luckyspin.facade._interface.AdminSpinItemFacade;
import com.vtc.gamerid.gateway.luckyspin.service._interface.AdminItemSpinService;
import com.vtc.gamerid.gateway.luckyspin.service._interface.SpinValidate;
import com.vtc.gamerid.gateway.profile.service.AdminProfileService;

/**
 * Created by phucnguyen on 03/07/2017.
 */
@Component
public class AdminSpinItemFacadeImpl implements AdminSpinItemFacade {
    private Logger logger = LoggerFactory.getLogger(AdminSpinItemFacadeImpl.class);

    @Autowired
    private AdminProfileService adminProfileService;
//    @Autowired
//    private SpinItemService spinItemService;
    
    @Autowired
    LuckySpinItemRepository spinItemDao;
    @Autowired
    private SpinValidate spinValidate;
    @Autowired
    private AdminItemSpinService adminItemSpinService;

    @Override
    @PermissionService(functionName = "miniGameManager")
    @AuditLogAnnotation(functionName = "getItemByPublisher")
    public BaseDataResponse getItemByPublisher(SpinItemSqlRequest spinItemSqlRequest) {
        try{
            UserGameRID userInfo = adminProfileService.getSessionUserInfo();
            if(!userInfo.getGroupRole().getName().equals("admin")){
                spinItemSqlRequest.setCreateBy(userInfo.getId());
            }else{
                spinItemSqlRequest.setCreateBy((long)-1);
            }
            List<TblLuckySpinItem> result = spinItemDao.findAll();
            for(TblLuckySpinItem instance: result){
                instance.setCreateBy(null);
            }
            return new BaseDataResponse(Constants.STATUS_CODE_TRUE, Constants.SUCCESS,
                    result, spinItemDao.countAllItemOfSpin(Constants.STATUS_DELETED));
        }catch (Exception e){
            logger.error(e.toString());
        }
        return new BaseDataResponse(Constants.STATUS_CODE_FALSE, Constants.MESS_UNKNOW);
    }

    @Override
    @PermissionService(functionName = "miniGameManager")
    @AuditLogAnnotation(functionName = "createItem")
    public BaseDataResponse createItem(SpinItemBeanRequest spinItemBeanRequest) {
        try{
            //Validate request
            ValidateBean validateBean = spinValidate.validateItemSpinRequest(spinItemBeanRequest);
            if(!validateBean.isSuccess()){
                return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
                        validateBean.getMessage());
            }
            if(spinItemBeanRequest.getType().equals("GIFTCODE") &&
                    spinItemBeanRequest.getGiftCodeFile() == null){
                return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
                        "Giftcode file null");
            }

            //Get current user
            UserGameRID userInfo = adminProfileService.getSessionUserInfo();

            //Check permission create lp
            if(spinItemBeanRequest.getType().equals("XU") &&
                    !userInfo.getGroupRole().getName().equals("admin")){
                return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
                        Constants.NOT_PERMISSION);
            }

            ServiceResponse serviceResponse = adminItemSpinService.
                    createItemSpin(spinItemBeanRequest);
            if(serviceResponse.isSuccess()){
                TblLuckySpinItem itemOfSpin = (TblLuckySpinItem) serviceResponse.getDataResponse();
                itemOfSpin.setCreateBy(null);
                return new BaseDataResponse(Constants.STATUS_CODE_TRUE, Constants.SUCCESS,
                        itemOfSpin);
            }
            return new BaseDataResponse(Constants.STATUS_CODE_FALSE, serviceResponse.getMessage());
        }catch (Exception e){
            logger.error(e.toString());
        }
        return new BaseDataResponse(Constants.STATUS_CODE_FALSE, Constants.MESS_UNKNOW);
    }

    @Override
    @PermissionService(functionName = "miniGameManager")
    @AuditLogAnnotation(functionName = "updateItem")
    public BaseDataResponse updateItem(SpinItemBeanRequest spinItemBeanRequest) {
        try{
            //Validate request
            ValidateBean validateBean = spinValidate.validateItemSpinRequest(spinItemBeanRequest);
            if(!validateBean.isSuccess()){
                return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
                        validateBean.getMessage());
            }

            //Get current user
            UserGameRID userInfo = adminProfileService.getSessionUserInfo();

            //Check permission create item XU
            if(spinItemBeanRequest.getType().equals("XU") &&
                    !userInfo.getGroupRole().getName().equals("admin")){
                return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
                        Constants.NOT_PERMISSION);
            }

            if(userInfo == null)
                return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
                        Constants.SESSION_TIMEOUT);
            ServiceResponse serviceResponse = adminItemSpinService.
                    updateItemSpin(spinItemBeanRequest);
            if(serviceResponse.isSuccess()){
                return new BaseDataResponse(Constants.STATUS_CODE_TRUE,
                        Constants.SUCCESS);
            }
            return new BaseDataResponse(Constants.STATUS_CODE_FALSE, serviceResponse.getMessage());
        }catch (Exception e){
            logger.error(e.toString());
        }
        return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
                Constants.MESS_UNKNOW);
    }

    @Override
    @PermissionService(functionName = "miniGameManager")
    @AuditLogAnnotation(functionName = "deleteItem")
    public BaseDataResponse deleteItem(Long itemSpinId) {
        if(itemSpinId < 0)
            new BaseDataResponse(Constants.STATUS_CODE_FALSE, Constants.BAD_REQ);

        UserGameRID userInfo = adminProfileService.getSessionUserInfo();
        if(userInfo == null)
            new BaseDataResponse(Constants.STATUS_CODE_FALSE,
                    Constants.SESSION_TIMEOUT);

        ServiceResponse serviceResponse = adminItemSpinService.deleteItemSpin(
                itemSpinId);
        if(serviceResponse.isSuccess()){
            return new BaseDataResponse(Constants.STATUS_CODE_TRUE,
                    Constants.SUCCESS);
        }
        return new BaseDataResponse(Constants.STATUS_CODE_TRUE,
                serviceResponse.getMessage());
    }
}
