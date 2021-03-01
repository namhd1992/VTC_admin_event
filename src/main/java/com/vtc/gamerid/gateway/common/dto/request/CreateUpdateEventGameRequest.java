/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dto.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Jan 10, 2019
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUpdateEventGameRequest {
    
    private Long                             id;

    private String                           name;

    private String                           playTutorial;

    private Long                             serviceId;

    private String                           urlBaseEvent;

    private long                             oncePoint;

    private String                           giftEvent;

    private float                            ratioGift;

    private Long                             limitGift;

    private Long                             gaveGift;

    private String                           packageGift;

    private Long                             fromDate;

    private Long                             toDate;

    private boolean                          status;

    private List<UpdateItemShopEventRequest> itemUpdates;

    private List<Long>                       itemRemoveIds;

}
