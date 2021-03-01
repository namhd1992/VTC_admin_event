package com.vtc.gamerid.gateway.role.facade._interface;

import com.vtc.gamerid.gateway.common.dto.response.BaseDataResponse;
import com.vtc.gamerid.gateway.role.bean.AdminUserRequest;
import com.vtc.gamerid.gateway.role.bean.GroupRoleRequestBean;
import com.vtc.gamerid.gateway.role.bean.UserInfoRequestBean;

import java.util.Map;

/**
 * Created by phucnguyen on 25/04/2017.
 */
public interface RoleManagerFacade {
    public BaseDataResponse getListRole(Map<String, Object> dataRequest);

    public BaseDataResponse createGroupRole(GroupRoleRequestBean groupRoleRequestBean);

    public BaseDataResponse updateGroupRole(GroupRoleRequestBean groupRoleRequestBean);

    public BaseDataResponse deleteGroupRole(GroupRoleRequestBean groupRoleRequestBean);

    public BaseDataResponse getAllUserInfo(AdminUserRequest dataRequest);

    public BaseDataResponse updateUserInfo(UserInfoRequestBean userInfoRequestBean);

    public BaseDataResponse deleteUserInfo(Map<String, Object> dataRequest);
}
