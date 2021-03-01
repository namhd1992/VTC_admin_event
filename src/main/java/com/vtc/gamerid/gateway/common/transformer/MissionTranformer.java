/***************************************************************************
 * Product 2018 by Quang Dat * 
 **************************************************************************/
package com.vtc.gamerid.gateway.common.transformer;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.vtc.gamerid.gateway.common.dao.entity.TblMission;
import com.vtc.gamerid.gateway.common.dao.entity.UserGameRID;
import com.vtc.gamerid.gateway.mission.bean.MissionBeanRequest;

/**
 * Creator : Le Quang Dat
 * Email   : quangdat0993@gmail.com
 * Date    : Oct 11, 2018
 */
@Component
public class MissionTranformer {
    
    public TblMission toMission(TblMission mission, MissionBeanRequest request, UserGameRID userInfo) {
        
        if(ObjectUtils.isEmpty(mission)) {
            mission = new TblMission();
        }
        
        mission.setName(request.getMissionName());
        mission.setDescription(request.getDescription());
        mission.setUpdateOn(new Date());
        mission.setUpdateBy(userInfo);
        mission.setStatus(request.getStatus());
        mission.setAction(request.getAction());
        mission.setAward(request.getAward());
        mission.setSpinAwardId(request.getSpinAwardId());
        mission.setValueAward(request.getValueAward());
        mission.setLinkToShare(request.getLinkToShare());
        mission.setTypeLimit(request.getTypeLimit());
        mission.setValueLimit(request.getValueLimit());
        mission.setFromDate(new Date(request.getFromDate()));
        mission.setToDate(new Date(request.getToDate()));
        mission.setCyclic(request.isCyclic());
        mission.setDefaultImage(request.getDefaultImage());
        mission.setObjectId(request.getObjectId());
        mission.setAndroidScheme(request.getAndroidScheme());
        mission.setIosScheme(request.getIosScheme());
        mission.setObjectValue(request.getObjectValue());
        mission.setHighLights(request.isHighLights());
        
        return mission;
    }
  
}
