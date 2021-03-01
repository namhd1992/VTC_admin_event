/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.game.service;

import com.vtc.gamerid.gateway.common.dto.request.ExchangeXORequest;
import com.vtc.gamerid.gateway.common.dto.response.GetBalanceXOResponse;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Nov 16, 2018
 */
public interface KNBGameService {
    
    public GetBalanceXOResponse getBalanceXO(long scoinId);

    public GetBalanceXOResponse exchangeXO(ExchangeXORequest request, String typeExchange);

}
