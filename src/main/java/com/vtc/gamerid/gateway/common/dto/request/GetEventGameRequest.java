/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dto.request;

import lombok.Getter;
import lombok.Setter;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Jan 9, 2019
 */
@Getter
@Setter
public class GetEventGameRequest {
    
    private int    limit       = 10;

    private int    offset      = 0;

    private String searchValue = null;

}
