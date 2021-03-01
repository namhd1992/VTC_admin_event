/***************************************************************************
 * Product 2018 by Quang Dat * 
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Creator : Le Quang Dat
 * Email   : quangdat0993@gmail.com
 * Date    : Oct 18, 2018
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetSplayCardResponse {
    
    private Long   missionId;

    private String missionName;

    private String status;

    private int    total;

    private int    available;

    private int    given;

    private String expirationDate;

}
