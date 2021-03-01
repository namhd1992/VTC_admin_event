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
 * Apr 23, 2020
 */
@Setter
@Getter
@NoArgsConstructor
public class UpdateAndCreateGameRankingRankRequest {

    private Long   id;

    private Long   gameRankingId;

    private String rankName;

    private int    rankPosition;

    private String rankIconUrl;

    private int    rankQuantity;

    private String description;

    private String status;

}
