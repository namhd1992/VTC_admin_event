package com.vtc.gamerid.gateway.game.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.vtc.gamerid.gateway.annotations.AuditLogAnnotation;
import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.dao.entity.Game;
import com.vtc.gamerid.gateway.common.dao.entity.UserGameRID;
import com.vtc.gamerid.gateway.common.dao.repository.GameRepository;
import com.vtc.gamerid.gateway.common.dto.response.ValidateBean;
import com.vtc.gamerid.gateway.common.service.AbstractService;
import com.vtc.gamerid.gateway.exception.ScoinInvalidDataRequestException;
import com.vtc.gamerid.gateway.exception.ScoinUnAuthorizationException;
import com.vtc.gamerid.gateway.game.bean.AdminSplayGameBeanRequest;
import com.vtc.gamerid.gateway.game.bean.AdminSplayGameFilter;
import com.vtc.gamerid.gateway.game.service.AdminSplayGameService;
import com.vtc.gamerid.gateway.profile.service.AdminProfileService;

/**
 * Created by phucnguyen on 06/12/2017.
 */
@Component
public class AdminSplayGameFacadeImpl extends AbstractService<Game, Long, GameRepository>
        implements AdminSplayGameFacade {
    @Autowired
    private AdminSplayGameService adminSplayGameService;
    @Autowired
    private AdminProfileService adminProfileService;

    @Override
    @AuditLogAnnotation(functionName = "getSplayGameList")
    public List<Game> getSplayGameList(AdminSplayGameFilter dataRequest) {
        //Get user session
        UserGameRID userInfo = adminProfileService.getSessionUserInfo();
        if(ObjectUtils.isEmpty(userInfo))
            throw new ScoinUnAuthorizationException();
        
        if(!userInfo.getGroupRole().getName().equals(Constants.ROLE_ADMIN)
                && !userInfo.getGroupRole().getName().equals(Constants.ROLE_ADMIN_EVENT_GAME))
            dataRequest.setCreateBy(userInfo.getId());

        //Validate data
        ValidateBean validateBean = dataRequest.validate();
        if(!validateBean.isSuccess())
            throw new ScoinInvalidDataRequestException();

        return adminSplayGameService.getListSplayGame(dataRequest);
    }

    @Override
    @AuditLogAnnotation(functionName = "createSplayGame")
//    @PermissionService(functionName = "splayGameManager")
    public Game  createSplayGame(AdminSplayGameBeanRequest dataRequest) {
        //Get user session
        UserGameRID userInfo = adminProfileService.getSessionUserInfo();
        if(ObjectUtils.isEmpty(userInfo))
            throw new ScoinUnAuthorizationException();
        
        dataRequest.setCreateBy(userInfo.getId());
        //Validate data
        ValidateBean validateBean = dataRequest.validate();
        if(!validateBean.isSuccess())
            throw new ScoinInvalidDataRequestException(validateBean.getMessage());

        //Get service response
        return adminSplayGameService.createSplayGame(dataRequest);
    }

    @Override
    @AuditLogAnnotation(functionName = "updateSplayGame")
//    @PermissionService(functionName = "splayGameManager")
    public Game updateSplayGame(AdminSplayGameBeanRequest dataRequest) {
        //Get user session
        UserGameRID userInfo = adminProfileService.getSessionUserInfo();
        if(ObjectUtils.isEmpty(userInfo))
            throw new ScoinUnAuthorizationException();
        
        if(!userInfo.getGroupRole().getName().equals("admin"))
            dataRequest.setCreateBy(userInfo.getId());

        //Validate data
        ValidateBean validateBean = dataRequest.validate();
        if(!validateBean.isSuccess())
            throw new ScoinInvalidDataRequestException();

        //Get service response
        return adminSplayGameService.updateSplayGame(dataRequest);
    }

    @Override
    @AuditLogAnnotation(functionName = "deleteSplayGame")
//    @PermissionService(functionName = "splayGameManager")
    public String deleteSplayGame(Long splayGameId) {
        //Get user session
        UserGameRID userInfo = adminProfileService.getSessionUserInfo();
        if(ObjectUtils.isEmpty(userInfo))
            throw new ScoinUnAuthorizationException();

        //Validate
        if(splayGameId < 1)
            throw new ScoinInvalidDataRequestException();

        //Get service response
        return adminSplayGameService.deleteSplayGame(splayGameId);
    }

//    @Override
//    @AuditLogAnnotation(functionName = "updatePositionGame")
//    @PermissionService(functionName = "splayGameManager")
//    public BaseDataResponse updatePositionGame(AdminPositionRequest dataRequest) {
//        try{
//            //Get service response
//            ServiceResponse serviceResponse = adminSplayGameService.updatePositionGame(dataRequest.getDataRequest());
//            if(!serviceResponse.isSuccess())
//                return new BaseDataResponse(Constants.STATUS_CODE_FALSE, serviceResponse.getMessage());
//
//            return new BaseDataResponse(Constants.STATUS_CODE_TRUE,
//                    Constants.SUCCESS);
//        }catch (Exception e){
//            logger.error("updatePositionGame", e);
//        }
//        return new BaseDataResponse(Constants.STATUS_CODE_FALSE, Constants.BAD_REQ);
//    }

}
