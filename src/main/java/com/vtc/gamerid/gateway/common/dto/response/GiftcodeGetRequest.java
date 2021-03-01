/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Apr 27, 2020
 */
@Setter
@Getter
@NoArgsConstructor
public class GiftcodeGetRequest {

    private String eventType;

    private Long   mainEventId;

    private Long   subEventId;

}
