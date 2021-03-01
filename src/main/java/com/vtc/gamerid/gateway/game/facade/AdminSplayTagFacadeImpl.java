package com.vtc.gamerid.gateway.game.facade;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.vtc.gamerid.gateway.annotations.AuditLogAnnotation;
import com.vtc.gamerid.gateway.annotations.PermissionService;
import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.dao.entity.TblSplayTag;
import com.vtc.gamerid.gateway.common.dao.repository.GameTagRepository;
import com.vtc.gamerid.gateway.common.dto.response.BaseDataResponse;
import com.vtc.gamerid.gateway.common.dto.response.ServiceResponse;
import com.vtc.gamerid.gateway.common.dto.response.ValidateBean;
import com.vtc.gamerid.gateway.common.service.AbstractService;
import com.vtc.gamerid.gateway.exception.ScoinInvalidDataRequestException;
import com.vtc.gamerid.gateway.game.bean.AdminSplayTagBean;
import com.vtc.gamerid.gateway.game.bean.ClientSplayTagFilter;
import com.vtc.gamerid.gateway.game.service.AdminSplayTagService;

/**
 * Created by phucnguyen on 03/04/2018.
 */
@Component
public class AdminSplayTagFacadeImpl extends AbstractService<TblSplayTag, Long, GameTagRepository>
        implements AdminSplayTagFacade {
    @Autowired
    private AdminSplayTagService adminSplayTagService;
    
    @Autowired
    private GameTagRepository adminSplayTagDao;
    
    @Override
    public List<TblSplayTag> getSplayTag(ClientSplayTagFilter dataRequest) {
        //Validate data
        ValidateBean validateBean = dataRequest.validate();
        if(!validateBean.isSuccess()) {
            throw new ScoinInvalidDataRequestException();
        }
            
        List<TblSplayTag> responses = adminSplayTagDao.getSplayTagFilter(dataRequest.getSearchValue(), dataRequest.getTypeName());
        if(CollectionUtils.isEmpty(responses)) return new ArrayList<TblSplayTag>();

        return responses;
    }
    
    @Override
    public int countSplayTag(ClientSplayTagFilter dataRequest) {
        return adminSplayTagDao.countSplayTagFilter(dataRequest.getSearchValue(), dataRequest.getTypeName());
    }

    @SuppressWarnings("unchecked")
    @Override
    @AuditLogAnnotation(functionName = "admin-createSplayTag")
    @PermissionService(functionName = "splayGameManager")
    public BaseDataResponse createSplayTag(AdminSplayTagBean dataRequest) {
        //Validate data
        ValidateBean validateBean = dataRequest.validateBean();
        if(!validateBean.isSuccess())
            return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
                    validateBean.getMessage());

        ServiceResponse serviceResponse = adminSplayTagService.createSplayTag(dataRequest);
        if(!serviceResponse.isSuccess())
            return new BaseDataResponse(Constants.STATUS_CODE_FALSE, serviceResponse.getMessage());

        return new BaseDataResponse(Constants.STATUS_CODE_TRUE,
                Constants.SUCCESS, (List<TblSplayTag>) serviceResponse.getDataResponse());
    }

    @Override
    @AuditLogAnnotation(functionName = "admin-updateSplayTag")
    @PermissionService(functionName = "splayGameManager")
    public BaseDataResponse updateSplayTag(AdminSplayTagBean dataRequest) {
        //Validate data
        ValidateBean validateBean = dataRequest.validateBean();
        if(!validateBean.isSuccess())
            return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
                    validateBean.getMessage());

        ServiceResponse serviceResponse = adminSplayTagService.updateSplayTag(dataRequest);
        if(!serviceResponse.isSuccess())
            return new BaseDataResponse(Constants.STATUS_CODE_FALSE, serviceResponse.getMessage());

        return new BaseDataResponse(Constants.STATUS_CODE_TRUE,
                Constants.SUCCESS, serviceResponse.getDataResponse());
    }

    @Override
    @AuditLogAnnotation(functionName = "admin-deleteSplayTag")
    @PermissionService(functionName = "splayGameManager")
    public BaseDataResponse deleteSplayTag(long id) {
        //Validate
        if(id < 1)
            return new BaseDataResponse(Constants.STATUS_CODE_FALSE,
                    Constants.BAD_REQ);
        ServiceResponse serviceResponse = adminSplayTagService.deleteSplayTag(id);
        if(!serviceResponse.isSuccess())
            return new BaseDataResponse(Constants.STATUS_CODE_FALSE, serviceResponse.getMessage());

        return new BaseDataResponse(Constants.STATUS_CODE_TRUE,
                Constants.SUCCESS, serviceResponse.getDataResponse());
    }
}
