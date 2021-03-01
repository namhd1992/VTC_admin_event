package com.vtc.gamerid.gateway.role.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vtc.gamerid.gateway.annotations.AuditLogAnnotation;
import com.vtc.gamerid.gateway.annotations.PermissionService;
import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.dao.entity.TblGroupRole;
import com.vtc.gamerid.gateway.common.dao.entity.UserInfo;
import com.vtc.gamerid.gateway.common.dao.repository.UserInfoRepository;
import com.vtc.gamerid.gateway.common.dto.response.BaseDataResponse;
import com.vtc.gamerid.gateway.common.dto.response.ValidateBean;
import com.vtc.gamerid.gateway.exception.RequestException;
import com.vtc.gamerid.gateway.role.bean.AdminUserRequest;
import com.vtc.gamerid.gateway.role.bean.GroupRoleRequestBean;
import com.vtc.gamerid.gateway.role.bean.UserInfoRequestBean;
import com.vtc.gamerid.gateway.role.facade._interface.RoleManagerFacade;
import com.vtc.gamerid.gateway.role.service._interface.FunctionManagerService;
import com.vtc.gamerid.gateway.role.service._interface.RoleManagerService;

/**
 * Created by phucnguyen on 25/04/2017.
 */
@Component
public class RoleManagerFacadeImpl implements RoleManagerFacade{
    private Logger logger = LoggerFactory.getLogger(RoleManagerFacadeImpl.class);

    @Autowired
    private RoleManagerService roleManagerService;
    @Autowired
    private FunctionManagerService functionManagerService;
    
    @Autowired
    UserInfoRepository userInfoRepo;

    @Override
    @AuditLogAnnotation(functionName = "getListRole")
    @PermissionService(functionName = "roleManager")
    public BaseDataResponse getListRole(Map<String, Object> dataRequest) {
        try{
            int limit = 10;
            int offset = 0;
            try{
                limit = Integer.parseInt(dataRequest.get("limit")+"");
                offset = Integer.parseInt(dataRequest.get("offset")+"");
            }catch (Exception e){

            }
            return new BaseDataResponse(Constants.STATUS_CODE_TRUE,
                    Constants.SUCCESS, roleManagerService.getListRole(limit, offset),
                    roleManagerService.countGroupRole(null));
        }catch (Exception e){
            logger.error(e.toString());
        }
        return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
                Constants.MESS_UNKNOW);
    }

    @Override
    @AuditLogAnnotation(functionName = "createGroupRole")
    @PermissionService(functionName = "roleManager")
    public BaseDataResponse createGroupRole(GroupRoleRequestBean groupRoleRequestBean) {
        try{
            //Validate name
            if(groupRoleRequestBean.getName() == null
                    || groupRoleRequestBean.getName().length() < 1)
                throw new RequestException(Constants.BAD_REQ, Constants.STATUS_CODE_FALSE);

            //Validate status
            if(groupRoleRequestBean.getStatus() == null ||
                    (!groupRoleRequestBean.getStatus().equals(Constants.STATUS_ACTIVE)
                    && !groupRoleRequestBean.getStatus().equals(Constants.STATUS_INACTIVE)))
                throw new RequestException(Constants.BAD_REQ, Constants.STATUS_CODE_FALSE);

            //Create role
            TblGroupRole groupRole = roleManagerService.createGroupRole(groupRoleRequestBean);
            if(groupRole == null) throw new RequestException(Constants.OBJECT_NOT_CREATE,
                    Constants.STATUS_CODE_FALSE);

            return new BaseDataResponse(Constants.STATUS_CODE_TRUE,
                    Constants.SUCCESS);
        }catch (RequestException re){
            return new BaseDataResponse(re.getStatusCode(),re.getMessage());
        }catch (Exception e){

        }
        return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
                Constants.MESS_UNKNOW);
    }

    @Override
    @AuditLogAnnotation(functionName = "updateGroupRole")
    @PermissionService(functionName = "roleManager")
    public BaseDataResponse updateGroupRole(GroupRoleRequestBean groupRoleRequestBean) {
        try{
            //Validate id
            if(groupRoleRequestBean.getId() < 1)
                throw new RequestException(Constants.BAD_REQ, Constants.STATUS_CODE_FALSE);

            //Validate status
            if(groupRoleRequestBean.getStatus() != null &&
                    (!groupRoleRequestBean.getStatus().equals(Constants.STATUS_ACTIVE)
                            && !groupRoleRequestBean.getStatus().equals(Constants.STATUS_INACTIVE)))
                throw new RequestException(Constants.BAD_REQ, Constants.STATUS_CODE_FALSE);

            //Update role
            TblGroupRole groupRole = roleManagerService.updateGroupRole(groupRoleRequestBean);
            if(groupRole == null) throw new RequestException(Constants.OBJECT_NOT_UPDATE,
                    Constants.STATUS_CODE_FALSE);

            return new BaseDataResponse(Constants.STATUS_CODE_TRUE,
                    Constants.SUCCESS);
        }catch (RequestException re){
            return new BaseDataResponse(re.getStatusCode(),re.getMessage());
        }catch (Exception e){

        }
        return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
                Constants.MESS_UNKNOW);
    }

    @Override
    @AuditLogAnnotation(functionName = "deleteGroupRole")
    @PermissionService(functionName = "roleManager")
    public BaseDataResponse deleteGroupRole(GroupRoleRequestBean groupRoleRequestBean) {
        try{
            //Validate id
            if(groupRoleRequestBean.getId() < 1)
                throw new RequestException(Constants.BAD_REQ,
                        Constants.STATUS_CODE_FALSE);

            boolean tmp = roleManagerService.deleteGroupRole(groupRoleRequestBean.getId());
            if(tmp){
                return new BaseDataResponse(Constants.STATUS_CODE_TRUE,
                        Constants.SUCCESS);
            }
            return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
                    Constants.OBJECT_NOT_DELETE);
        }catch (RequestException re){
            return new BaseDataResponse(re.getStatusCode(),re.getMessage());
        }catch (Exception e){

        }
        return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
                Constants.MESS_UNKNOW);
    }

    @Override
    @AuditLogAnnotation(functionName = "getAllUserInfo")
    @PermissionService(functionName = "roleManager")
    public BaseDataResponse getAllUserInfo(AdminUserRequest adminUserRequest) {
        try{
            //Validate data
            ValidateBean validateBean = adminUserRequest.validate();
            if(!validateBean.isSuccess())
                return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
                        validateBean.getMessage());

//            List<UserInfo> userInfos = roleManagerService.getAllUserInfo(adminUserRequest);
//            for(UserInfo instance: userInfos){
//                if(instance.getUserGameRID() != null){
//                    instance.setUsername(instance.getUserGameRID().getUsername());
//                    instance.getUserGameRID().setUserInfo(null);
//                }
//                if(instance.getUserSSO() != null){
//                    instance.getUserSSO().setUserInfo(null);
//                }
//                if(instance.getUserVTC() != null){
//                    instance.setUsername(instance.getUserVTC().getUsername());
//                    instance.getUserVTC().setUserInfo(null);
//                }
//                instance.setOtpKey(null);
//            }
            return new BaseDataResponse(Constants.STATUS_CODE_TRUE,
                    Constants.SUCCESS, new ArrayList<UserInfo>(),
                    functionManagerService.countUserInfo(null, adminUserRequest.getSearchValue()));
        }catch (Exception e){
            logger.error(e.toString());
        }
        return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
                Constants.MESS_UNKNOW);
    }

    @Override
    @AuditLogAnnotation(functionName = "updateUserInfo")
    @PermissionService(functionName = "roleManager")
    public BaseDataResponse updateUserInfo(UserInfoRequestBean userInfoRequestBean) {
        try{
            //Validate id
            if(userInfoRequestBean.getId() < 1)
                throw new RequestException(Constants.BAD_REQ, Constants.STATUS_CODE_FALSE);

            //Validate status
            if(userInfoRequestBean.getStatus() != null &&
                    (!userInfoRequestBean.getStatus().equals(Constants.STATUS_ACTIVE)
                            && !userInfoRequestBean.getStatus().equals(Constants.STATUS_INACTIVE)))
                throw new RequestException(Constants.BAD_REQ, Constants.STATUS_CODE_FALSE);

            //Validate group role
            if(userInfoRequestBean.getGroupRoleId() < 1)
                throw new RequestException(Constants.BAD_REQ, Constants.STATUS_CODE_FALSE);

            //Update user info
            UserInfo userInfo = roleManagerService.updateUserInfo(userInfoRequestBean);
            if(userInfo == null) throw new RequestException(Constants.OBJECT_NOT_UPDATE,
                    Constants.STATUS_CODE_FALSE);

            return new BaseDataResponse(Constants.STATUS_CODE_TRUE,
                    Constants.SUCCESS);
        }catch (RequestException re){
            return new BaseDataResponse(re.getStatusCode(),re.getMessage());
        }catch (Exception e){

        }
        return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
                Constants.MESS_UNKNOW);
    }

    @Override
    @AuditLogAnnotation(functionName = "deleteUserInfo")
    @PermissionService(functionName = "roleManager")
    public BaseDataResponse deleteUserInfo(Map<String, Object> dataRequest) {
        try{
            Long userId = Long.parseLong(dataRequest.get("userId")+"");
            userInfoRepo.delete(userId);
            return new BaseDataResponse(Constants.STATUS_CODE_TRUE,
                    Constants.SUCCESS);
        }catch (Exception e){
            logger.error(e.toString());
        }
        return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
                Constants.BAD_REQ);
    }
}
