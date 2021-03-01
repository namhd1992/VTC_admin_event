package com.vtc.gamerid.gateway.role.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.dao.entity.TblFunction;
import com.vtc.gamerid.gateway.common.dao.entity.TblGroupRole;
import com.vtc.gamerid.gateway.common.dao.entity.UserInfo;
import com.vtc.gamerid.gateway.common.dao.repository.FunctionManagerRepository;
import com.vtc.gamerid.gateway.common.dao.repository.GroupRoleRepository;
import com.vtc.gamerid.gateway.common.dao.repository.UserInfoRepository;
import com.vtc.gamerid.gateway.role.bean.GroupRoleRequestBean;
import com.vtc.gamerid.gateway.role.bean.UserInfoRequestBean;
import com.vtc.gamerid.gateway.role.service._interface.RoleManagerService;

/**
 * Created by phucnguyen on 25/04/2017.
 */
@Service
public class RoleManagerServiceImpl implements RoleManagerService {
    private Logger logger = LoggerFactory.getLogger(RoleManagerServiceImpl.class);

    @Autowired
    private GroupRoleRepository roleManagerDao;
    @Autowired
    private FunctionManagerRepository functionManagerDao;
    @Autowired
    private UserInfoRepository userInfoRepo;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<TblGroupRole> getListRole(int limit, int offset) {
        return roleManagerDao.findAllGroupRole(new PageRequest(offset, limit));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public TblGroupRole createGroupRole(GroupRoleRequestBean groupRoleRequestBean) {
        try{
            //Check name
            TblGroupRole searchGroupByName = roleManagerDao.findByName(
                    groupRoleRequestBean.getName());
            if(searchGroupByName != null){
                logger.info("==> Group role name ["+groupRoleRequestBean.getName()+"] does exist");
                return null;
            }

            List<TblFunction> listFunction = new ArrayList<>();
            //Update group role
            if(groupRoleRequestBean.getFunctionList() != null &&
                    groupRoleRequestBean.getFunctionList().size() > 0){
                listFunction = functionManagerDao.
                        findAll(groupRoleRequestBean.getFunctionList());
            }

            TblGroupRole createGroup = new TblGroupRole(groupRoleRequestBean.getName(),
                    groupRoleRequestBean.getPoint(), groupRoleRequestBean.getStatus());
            createGroup.setFunctionList(listFunction);
            return roleManagerDao.save(createGroup);
        }catch (Exception e){
            logger.error(e.toString());
        }
        return null;
    }

    @Override
    public TblGroupRole updateGroupRole(GroupRoleRequestBean groupRoleRequestBean) {
        try{
            //Get group role by id
            TblGroupRole groupRole = roleManagerDao.findOne(
                    groupRoleRequestBean.getId());
            if(groupRole == null){
                logger.info("==> Can not find group role by id: "+groupRoleRequestBean.getId());
                return null;
            }

            //Check name role
            TblGroupRole searchName = roleManagerDao.findByName(groupRoleRequestBean.getName());
            if(searchName != null && searchName.getId() != groupRole.getId()){
                logger.info(groupRoleRequestBean.getName() + " does exist !!!");
                return null;
            }
            //Set name
            if(groupRoleRequestBean.getName() != null &&
                    groupRoleRequestBean.getName().length() > 1){
                groupRole.setName(groupRoleRequestBean.getName());
            }

            //Set status
            if(groupRoleRequestBean.getStatus() != null){
                groupRole.setStatus(groupRoleRequestBean.getStatus());
            }

            //Set function
            //Update group role
            if(groupRoleRequestBean.getFunctionList() != null ){
                List<TblFunction> listFunction = functionManagerDao.
                        findAll(groupRoleRequestBean.getFunctionList());
                groupRole.setFunctionList(listFunction);
            }
            return roleManagerDao.save(groupRole);
        }catch (Exception e){
            logger.error(e.toString());
        }
        return null;
    }

//    @Override
//    public List<UserInfo> getAllUserInfo(AdminUserRequest adminUserRequest) {
//        return userInfoRepo.getAllUserInfo(adminUserRequest.getSearchValue(), 
//                                          adminUserRequest.getSearchValue(), 
//                                          adminUserRequest.getSearchValue(), 
//                                          adminUserRequest.getSearchValue(), 
//                                          adminUserRequest.getRoleName());
//    }

    @Override
    public UserInfo updateUserInfo(UserInfoRequestBean userInfoRequestBean) {
        try{
            //Get user info
            UserInfo userInfoSearch = userInfoRepo.findOne(userInfoRequestBean.getId());
            if(userInfoSearch == null){
                logger.error("Can not get userInfo by id: "+userInfoRequestBean.getId());
                return null;
            }
            //Set value
            if(userInfoRequestBean.getFirstName() != null &&
                    userInfoRequestBean.getFirstName().length() > 1){
                userInfoSearch.setFirstName(userInfoRequestBean.getFirstName());
            }

            if(userInfoRequestBean.getLastName() != null &&
                    userInfoRequestBean.getLastName().length() > 1){
                userInfoSearch.setLastName(userInfoRequestBean.getLastName());
            }

            if(userInfoRequestBean.getStatus() != null){
                userInfoSearch.setStatus(userInfoRequestBean.getStatus());
            }

            if(userInfoRequestBean.getPhoneNumber() != null &&
                    userInfoRequestBean.getPhoneNumber().length() > 1){
                userInfoSearch.setPhoneNumber(userInfoRequestBean.getPhoneNumber());
            }

            if(userInfoRequestBean.getAddress() != null &&
                    userInfoRequestBean.getAddress().length() > 1){
                userInfoSearch.setAddress(userInfoRequestBean.getAddress());
            }

            //Search role by Id
            if(userInfoRequestBean.getGroupRoleId() > -1){
                TblGroupRole groupRole = roleManagerDao.findOne(
                        userInfoRequestBean.getGroupRoleId());
                if(groupRole != null) userInfoSearch.setGroupRole(groupRole);
            }
            //Update
            return userInfoRepo.save(userInfoSearch);
        }catch (Exception e){
            logger.error(e.toString());
        }
        return null;
    }

    @Override
    public int countGroupRole(String status) {
        return roleManagerDao.countGroupRole(status);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean deleteGroupRole(Long groupRoleId) {
        try{
            TblGroupRole groupRoleDefault = roleManagerDao.findByName(
                    Constants.ROLE_USER.toLowerCase());
            List<UserInfo> userInfoList = userInfoRepo.findByGroupRoleId(groupRoleId);
            for(UserInfo instance: userInfoList){
                instance.setGroupRole(groupRoleDefault);
                userInfoRepo.save(instance);
            }
            roleManagerDao.delete(groupRoleId);
            return true;
        }catch (Exception e){
            logger.error(e.toString());
        }
        return false;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String deleteUserInfo(Long userId) {
        userInfoRepo.delete(userId);
        return "Delete userinfo success";
    }
}
