package com.vtc.gamerid.gateway.role.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vtc.gamerid.gateway.common.ultils.JsonMapperUtils;
import com.vtc.gamerid.gateway.role.bean.AdminUserRequest;
import com.vtc.gamerid.gateway.role.bean.GroupRoleRequestBean;
import com.vtc.gamerid.gateway.role.bean.UserInfoRequestBean;
import com.vtc.gamerid.gateway.role.facade._interface.RoleManagerFacade;

/**
 * Created by phucnguyen on 25/04/2017.
 */
@RestController
public class RoleManagerController {
    
    @Autowired
    private RoleManagerFacade roleManagerFacade;

    @RequestMapping(method = RequestMethod.GET, value = "/admin/groupRole")
    public ResponseEntity<?> getAllRole(@RequestParam Map<String, Object> dataRequest) {
        return new ResponseEntity<>(roleManagerFacade.getListRole(dataRequest), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/admin/groupRole")
    public ResponseEntity<?> createGroupRole(@RequestBody Map<String, Object> dataRequest) {
        GroupRoleRequestBean groupRoleRequestBean = JsonMapperUtils.convertJsonToObject(
                JsonMapperUtils.toJson(dataRequest), GroupRoleRequestBean.class);
        return new ResponseEntity<>(
                roleManagerFacade.createGroupRole(groupRoleRequestBean), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/admin/groupRole")
    public ResponseEntity<?> updateGroupRole(@RequestBody Map<String, Object> dataRequest) {
        GroupRoleRequestBean groupRoleRequestBean = JsonMapperUtils.convertJsonToObject(
                JsonMapperUtils.toJson(dataRequest), GroupRoleRequestBean.class);
        return new ResponseEntity<>(
                roleManagerFacade.updateGroupRole(groupRoleRequestBean), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/admin/groupRole")
    public ResponseEntity<?> deleteGroupRole(@RequestBody Map<String, Object> dataRequest) {
        GroupRoleRequestBean groupRoleRequestBean = JsonMapperUtils.convertJsonToObject(
                JsonMapperUtils.toJson(dataRequest), GroupRoleRequestBean.class);
        return new ResponseEntity<>(
                roleManagerFacade.deleteGroupRole(groupRoleRequestBean), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/admin/user")
    public ResponseEntity<?> getAllUserInfo(AdminUserRequest dataRequest) {
        return new ResponseEntity<>(roleManagerFacade.getAllUserInfo(dataRequest),
                HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/admin/user")
    public ResponseEntity<?> updateUserInfo(@RequestBody Map<String, Object> dataRequest) {
        UserInfoRequestBean userInfoRequestBean = JsonMapperUtils.convertJsonToObject(
                JsonMapperUtils.toJson(dataRequest), UserInfoRequestBean.class);
        return new ResponseEntity<>(
                roleManagerFacade.updateUserInfo(userInfoRequestBean), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/admin/user")
    public ResponseEntity<?> deleteUserInfo(@RequestBody Map<String, Object> dataRequest) {
        return new ResponseEntity<>(
                roleManagerFacade.deleteUserInfo(dataRequest), HttpStatus.OK);
    }
}
