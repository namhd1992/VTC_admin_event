package com.vtc.gamerid.gateway.mission.service;

import java.util.List;

import com.vtc.gamerid.gateway.common.dao.entity.TblMission;
import com.vtc.gamerid.gateway.exception.SplayBusinessException;
import com.vtc.gamerid.gateway.mission.bean.MissionBeanRequest;
import com.vtc.gamerid.gateway.mission.bean.MissionFilter;

/**
 * Created by phucnguyen on 21/11/2017.
 */
public interface AdminMissionService {
    
    public List<TblMission> getMission(MissionFilter dataRequest) throws SplayBusinessException;

    public TblMission createMission(MissionBeanRequest dataRequest) throws SplayBusinessException;

    public TblMission updateMission(MissionBeanRequest dataRequest) throws SplayBusinessException;

    public String deleteMission(Long missionId) throws SplayBusinessException;

    public int countAllRecordMission();
    
    public TblMission getMissionById(Long missionId);
    
}
