/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dto.response;

import java.util.Map;

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
public class GetBalanceXOResponse {
    
    private Integer                                    error;

    private String                                     message;

    private long                                       xo;

    private Long                                       error_code;

    private String                                     error_desc;

    private Map<Integer, ExchangeXOLimitsValueRespone> rechargeLimits;

    private Map<Integer, ExchangeXOLimitsValueRespone> withdrawLimits;

}
