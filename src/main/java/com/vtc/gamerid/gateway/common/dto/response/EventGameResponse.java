/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Jan 15, 2019
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventGameResponse {
    
    private Integer               status;

    private String                description;

    private EventGameResponseData data;

}
