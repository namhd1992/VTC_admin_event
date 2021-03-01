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
 * Dec 25, 2018
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProviderExchangeXUReponse {

    public Long balance;
    
    public Long amountExchange;

}
