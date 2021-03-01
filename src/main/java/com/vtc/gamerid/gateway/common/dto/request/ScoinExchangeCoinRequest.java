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
 * Nov 19, 2018
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScoinExchangeCoinRequest {
    
    private Long   serviceId;

    private Long   packageExchangeXO;

    private Long   packageExchangeXU;

    private String action;

}
