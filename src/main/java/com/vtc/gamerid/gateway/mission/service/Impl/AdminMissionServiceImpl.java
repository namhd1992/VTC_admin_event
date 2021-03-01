package com.vtc.gamerid.gateway.mission.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.vtc.gamerid.gateway.common.dao.entity.TblMission;
import com.vtc.gamerid.gateway.common.dao.entity.UserGameRID;
import com.vtc.gamerid.gateway.common.dao.repository.MissionRepository;
import com.vtc.gamerid.gateway.common.dto.response.ValidateBean;
import com.vtc.gamerid.gateway.common.service.AbstractService;
import com.vtc.gamerid.gateway.common.transformer.MissionTranformer;
import com.vtc.gamerid.gateway.exception.FailedToExecuteException;
import com.vtc.gamerid.gateway.exception.RollBackTransaction;
import com.vtc.gamerid.gateway.exception.ScoinInvalidDataRequestException;
import com.vtc.gamerid.gateway.exception.ScoinNotFoundEntityException;
import com.vtc.gamerid.gateway.exception.SplayBusinessException;
import com.vtc.gamerid.gateway.mission.bean.MissionBeanRequest;
import com.vtc.gamerid.gateway.mission.bean.MissionFilter;
import com.vtc.gamerid.gateway.mission.service.AdminMissionService;

/**
 * Created by phucnguyen on 21/11/2017.
 */
@Service("adminMissionFacade")
public class AdminMissionServiceImpl extends AbstractService<TblMission, Long, MissionRepository>
        implements AdminMissionService {

    @Autowired
    private MissionTranformer tranform;
    
//    @Autowired
//    private AdminSplayCardService adminSplayCardService;
//    
//    @Autowired
//    private UploadFileService uploadFileService;

    @Override
    @Transactional
    public List<TblMission> getMission(MissionFilter dataRequest) throws SplayBusinessException {
        verifyTokenAdmin();
        // Validate
        ValidateBean validateBean = dataRequest.validate();
        if (!validateBean.isSuccess())
            throw new ScoinInvalidDataRequestException(validateBean.getMessage());

        List<TblMission> response = repo.getMission(dataRequest.getSearchValue(), new PageRequest(dataRequest.getOffset(), dataRequest.getLimit()));
        for (TblMission instance : response) {
            instance.setCreateBy(null);
            instance.setUpdateBy(null);
        }

        return response;
    }

    @Override
    @Transactional
    public TblMission createMission(MissionBeanRequest dataRequest) {
        UserGameRID userInfo = verifyTokenAdmin();

        // Validate data
        ValidateBean validateBean = dataRequest.validate();
        if (!validateBean.isSuccess())
            throw new ScoinInvalidDataRequestException(validateBean.getMessage());
        
        // Get service response
        TblMission mission = tranform.toMission(null, dataRequest, userInfo);
        TblMission createMission = repo.save(mission);
        if (ObjectUtils.isEmpty(createMission)) {
            RollBackTransaction.callRollback();
            throw new FailedToExecuteException("Don't screat Mission");
        }
        
        createMission.setCreateBy(null);
        createMission.setUpdateBy(null);
        return createMission;
    }

    @Override
    @Transactional
    public TblMission updateMission(MissionBeanRequest dataRequest) throws SplayBusinessException {
        UserGameRID userInfo = verifyTokenAdmin();

        // Validate data
        ValidateBean validateBean = dataRequest.validate();
        if (!validateBean.isSuccess()) {
            throw new ScoinInvalidDataRequestException(validateBean.getMessage());
        }

        // Get service response
        TblMission mission = repo.findOne(dataRequest.getMissionId());
        if (ObjectUtils.isEmpty(mission)) {
            RollBackTransaction.callRollback();
            throw new ScoinNotFoundEntityException("Not found Mission by this Id");
        }

        mission = tranform.toMission(mission, dataRequest, userInfo);
        mission = repo.save(mission);
        if (ObjectUtils.isEmpty(mission)) {
            RollBackTransaction.callRollback();
            throw new FailedToExecuteException("Don't UPDATE Mission");
        }

        mission.setCreateBy(null);
        mission.setUpdateBy(null);

        return mission;
    }
    
    @Override
    @Transactional
    public String deleteMission(Long missionId) throws SplayBusinessException {
        verifyTokenAdmin();
        // Validate data
        if (missionId < 1)
            throw new ScoinInvalidDataRequestException();

        TblMission mission = repo.findOne(missionId);
        if (ObjectUtils.isEmpty(mission)) {
            RollBackTransaction.callRollback();
            throw new ScoinNotFoundEntityException("Not found entity by this id");
        }

        // Get service response
        repo.delete(mission);

        return "Delete Mission Successfull";
    }
    
    @Override
    public int countAllRecordMission() {
        return repo.countAllRecordMission();
    }

    @Override
    public TblMission getMissionById(Long missionId) {
        return repo.findOne(missionId);
    }
}
