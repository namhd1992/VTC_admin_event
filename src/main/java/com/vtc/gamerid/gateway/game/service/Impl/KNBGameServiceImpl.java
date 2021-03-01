/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.game.service.Impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.EnvironmentKey;
import com.vtc.gamerid.gateway.common.dto.request.ExchangeXORequest;
import com.vtc.gamerid.gateway.common.dto.request.GetBalanceXORequest;
import com.vtc.gamerid.gateway.common.dto.response.GetBalanceXOResponse;
import com.vtc.gamerid.gateway.common.service.AbstractService;
import com.vtc.gamerid.gateway.common.ultils.ApiExchangeServiceUtil;
import com.vtc.gamerid.gateway.common.ultils.JsonMapperUtils;
import com.vtc.gamerid.gateway.common.ultils.StringUtils;
import com.vtc.gamerid.gateway.exception.FailedToExecuteException;
import com.vtc.gamerid.gateway.exception.ScoinInvalidDataRequestException;
import com.vtc.gamerid.gateway.game.service.KNBGameService;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Nov 16, 2018
 */
@Service("knbGameService")
public class KNBGameServiceImpl implements KNBGameService {
    
    protected Logger  logger = LoggerFactory.getLogger(AbstractService.class);
    
    private static String LIVE_TOPGAME_API_URL;
    private static String LIVE_TOPGAME_SECRET_KEY;

    public KNBGameServiceImpl(Environment env) {
        LIVE_TOPGAME_API_URL = env.getProperty(EnvironmentKey.LIVE_TOPGAME_API_URL.getKey());
        LIVE_TOPGAME_SECRET_KEY = env.getProperty(EnvironmentKey.LIVE_TOPGAME_SECRET_KEY.getKey());
    }

    @Override
    public GetBalanceXOResponse getBalanceXO(long scoinId) {
        String url = LIVE_TOPGAME_API_URL + "knbgetbalance";
        Long unixTime = new Date().getTime();
        String sign = StringUtils.toMD5(LIVE_TOPGAME_SECRET_KEY + scoinId + unixTime);
        
        GetBalanceXORequest request = new GetBalanceXORequest();
        request.setAccountId(scoinId);
        request.setTimeStamp(unixTime);
        request.setSign(sign);
        String dateResponse = null;
        try {
            dateResponse = ApiExchangeServiceUtil.post(url, request, new TypeReference<String>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }
        GetBalanceXOResponse response = JsonMapperUtils.convertJsonToObject(dateResponse, GetBalanceXOResponse.class);
        
        if (response.getError() != 0) 
            throw new FailedToExecuteException("Don't call server top game");
        return response;
    }

    @Override
    public GetBalanceXOResponse exchangeXO(ExchangeXORequest request, String typeExchange) {
        if (StringUtils.isEmpty(typeExchange)
                || ObjectUtils.isEmpty(request.getAccountId())
                || ObjectUtils.isEmpty(request.getXU())
                || ObjectUtils.isEmpty(request.getXO())
                || ObjectUtils.isEmpty(request.getDescript())
                || ObjectUtils.isEmpty(request.getTransId()))
            return null;
        request.setAccountId(request.getAccountId());
        
        if (typeExchange.equals(Constants.XO_TOPUP) )
            request.setAction(1);
        if (typeExchange.equals(Constants.XO_DEDUCT)) {
            GetBalanceXOResponse balanceXO = getBalanceXO(request.getAccountId());
            if (request.getXO() > balanceXO.getXo()) {
                throw new ScoinInvalidDataRequestException("Số dư XO không đủ để thực hiện giao dịch");
            }
            request.setAction(2);
        }
        
        String url = LIVE_TOPGAME_API_URL + "knbexchange";
        Long unixTime = new Date().getTime();
        String sign = StringUtils.toMD5(LIVE_TOPGAME_SECRET_KEY 
                + request.getAccountId() 
                + request.getXU() 
                + request.getXO() 
                + request.getAction() 
                + request.getTransId() 
                + unixTime);
       
        request.setTimeStamp(unixTime);
        request.setSign(sign);
        String dateResponse = null;
        try {
            dateResponse = ApiExchangeServiceUtil.post(url, request, new TypeReference<String>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("exchangeXO RESPONSE =========================== {}", dateResponse);
        GetBalanceXOResponse response = JsonMapperUtils.convertJsonToObject(dateResponse, GetBalanceXOResponse.class);
        
        return response;
    }

}
