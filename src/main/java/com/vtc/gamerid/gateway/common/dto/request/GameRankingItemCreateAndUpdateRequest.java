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
 * Apr 25, 2020
 */
@Getter
@Setter
@NoArgsConstructor
public class GameRankingItemCreateAndUpdateRequest {
    
    private Long   id;

    private Long   gameRankingId;

    private Long   gameRankingRankId;

    private String itemIconUrl;

    private String itemName;

    private String itemType;

    private int    itemQuantity;

    private Long   objectId;

    private int    itemValue;
    
    private String description;

}
