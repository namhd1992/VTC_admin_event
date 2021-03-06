/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Apr 15, 2020
 */
@Setter
@Getter
@NoArgsConstructor
public class GameRankingUpdateAndCreateRequest {

    private Long   id;

    private String name;

    private Long   createBy;

    private String description;

    private long   serviceId;
    
    private String status;

}
