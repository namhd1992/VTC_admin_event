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
 * Oct 6, 2020
 */
@Setter
@Getter
@NoArgsConstructor
public class LuckySpinGiftBeanRequest {

    private Long   id;

    private String Name;

    private Long   luckySpinId;

    private int    setPoint;

    private String typeGift;

    private long   value;

    private int    quantity;

    private String description;

    private Long   luckySpinItemId;

}
