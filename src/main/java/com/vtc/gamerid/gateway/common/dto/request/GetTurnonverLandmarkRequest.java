/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Sep 9, 2019
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetTurnonverLandmarkRequest {
    
    private long   id;

    private String game;

    private long   gameId;

    private long   turnoverLandmark;

}
