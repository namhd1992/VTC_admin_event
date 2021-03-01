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
 * Nov 16, 2018
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeXOLimitsResponse {
    
    private int key;
    
    private ExchangeXOLimitsValueRespone value;
    
}
