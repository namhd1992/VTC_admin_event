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
 * Feb 21, 2019
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SplayExchangeGiftEventGameRequest {
    
    private Long   eventGameId;

    private Long   giftValue;

}
