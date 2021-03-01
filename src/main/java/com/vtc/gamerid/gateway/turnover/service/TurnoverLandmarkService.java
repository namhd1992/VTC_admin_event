/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.turnover.service;

import java.util.List;

import com.vtc.gamerid.gateway.common.dao.entity.TblTurnoverLandmark;
import com.vtc.gamerid.gateway.common.dto.request.CreateUpdateTurnoverLandmarkRequest;
import com.vtc.gamerid.gateway.common.dto.request.GetTurnonverLandmarkRequest;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Sep 9, 2019
 */
public interface TurnoverLandmarkService {
    
    public List<TblTurnoverLandmark> getTurnoverLandmark(GetTurnonverLandmarkRequest request);

    public List<TblTurnoverLandmark> createTurnoverLandmark(List<CreateUpdateTurnoverLandmarkRequest> requests);

    public List<TblTurnoverLandmark> updateTurnoverLandmark(List<CreateUpdateTurnoverLandmarkRequest> requests);

    public String deleteTurnoverLandmark(List<Long> eventId);
    
    public int countAllRecordEvent();

}
