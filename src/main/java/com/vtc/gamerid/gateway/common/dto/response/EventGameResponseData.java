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
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EventGameResponseData {
    
    private Long account_id;

    private Long points;

}
