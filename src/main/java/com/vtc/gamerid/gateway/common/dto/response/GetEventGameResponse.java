/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dto.response;

import java.util.List;

import com.vtc.gamerid.gateway.common.dao.entity.GameEvent;
import com.vtc.gamerid.gateway.common.dao.entity.GameItemOfGameEvent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Apr 3, 2019
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetEventGameResponse {

    private GameEvent                 eventGame;

    private List<GameItemOfGameEvent> itemEvents;

}
