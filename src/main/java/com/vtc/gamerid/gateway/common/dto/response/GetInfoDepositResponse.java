/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dto.response;

import java.util.List;

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
public class GetInfoDepositResponse {
    
    private long  userBalance;
    
    private Long  recharge;

    private Long  rechargeLimits;
    
    private Long  withdraw;

    private Long  withdrawLimits;

    private double ratioExchange;
    
    private List<Long> packageExchangeXUs;

}
