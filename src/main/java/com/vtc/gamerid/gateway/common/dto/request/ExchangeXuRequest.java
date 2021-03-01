/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dto.request;

import lombok.Getter;
import lombok.Setter;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Nov 14, 2018
 */
@Getter
@Setter
public class ExchangeXuRequest {
    
    private String clientId;

    private String clientSecret;

    private Integer userId;

    private long   amount;

    private String content;

    private String typeExchange;

    private Long   unixTime;
    
}
