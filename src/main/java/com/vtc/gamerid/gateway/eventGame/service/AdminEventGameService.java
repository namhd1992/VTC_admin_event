/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.eventGame.service;

import java.util.List;

import com.vtc.gamerid.gateway.common.dao.entity.GameEvent;
import com.vtc.gamerid.gateway.common.dto.request.CreateUpdateEventGameRequest;
import com.vtc.gamerid.gateway.common.dto.request.GetEventGameRequest;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Jan 3, 2019
 */
public interface AdminEventGameService {

    public List<GameEvent> getEvent(GetEventGameRequest request);

    public GameEvent createEvent(CreateUpdateEventGameRequest request);

    public GameEvent updateEvent(CreateUpdateEventGameRequest request);

    public String deleteEvent(Long eventId);
    
    public long countAllRecordEvent();

}
