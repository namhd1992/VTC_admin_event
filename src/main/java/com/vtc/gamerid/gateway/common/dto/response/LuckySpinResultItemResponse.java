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
 * Jul 22, 2019
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LuckySpinResultItemResponse {
    
    private long   luckySpinId;

    private String resultItem;

}
