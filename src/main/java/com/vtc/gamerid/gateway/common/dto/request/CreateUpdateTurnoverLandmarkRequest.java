/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

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
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CreateUpdateTurnoverLandmarkRequest {
    
    private Long   id;

    private String game;

    private Long   gameId;

    private Long    itemId;

    private int    limitQuantity;

    private String limitType;

    private long   turnoverLandmark;

}
