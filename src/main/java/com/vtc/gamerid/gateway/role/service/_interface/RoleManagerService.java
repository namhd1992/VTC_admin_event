package com.vtc.gamerid.gateway.role.service._interface;

import java.util.List;

import com.vtc.gamerid.gateway.common.dao.entity.TblGroupRole;
import com.vtc.gamerid.gateway.common.dao.entity.UserInfo;
import com.vtc.gamerid.gateway.role.bean.GroupRoleRequestBean;
import com.vtc.gamerid.gateway.role.bean.UserInfoRequestBean;

/**
 * Created by phucnguyen on 25/04/2017.
 */
public interface RoleManagerService {
    public List<TblGroupRole> getListRole(int limit, int offset);

    public TblGroupRole createGroupRole(GroupRoleRequestBean groupRoleRequestBean);

    public TblGroupRole updateGroupRole(GroupRoleRequestBean groupRoleRequestBean);

//    public List<UserInfo> getAllUserInfo(AdminUserRequest adminUserRequest);

    public UserInfo updateUserInfo(UserInfoRequestBean userInfoRequestBean);

    public int countGroupRole(String status);

    public boolean deleteGroupRole(Long groupRoleId);
    public String deleteUserInfo(Long userId);
}
