///***************************************************************************
// * Product made by Quang Dat *
// **************************************************************************/
//package com.vtc.gamerid.gateway.eventGame.service;
//
//import com.vtc.gamerid.gateway.common.dto.request.BuyItemShopEventGameRequest;
//import com.vtc.gamerid.gateway.common.dto.request.GetEventGameRequest;
//import com.vtc.gamerid.gateway.common.dto.request.SplayExchangeGiftEventGameRequest;
//import com.vtc.gamerid.gateway.common.dto.response.ExchangeGiftEventGameResponse;
//import com.vtc.gamerid.gateway.common.dto.response.GetEventGameResponse;
//import com.vtc.gamerid.gateway.common.dto.response.JoinEventGameResponse;
//import com.vtc.gamerid.gateway.exception.SplayBusinessException;
//
///**
// * Author : Dat Le Quang
// * Email: Quangdat0993@gmail.com
// * Dec 26, 2018
// */
//public interface ClientEventGameService {
//
//    public GetEventGameResponse getEvent(GetEventGameRequest request);
//
//    public JoinEventGameResponse joinEventGame() throws SplayBusinessException;
//
//    public String useLink(Long parentScoinId, String facebookId) throws SplayBusinessException;
//
//    public ExchangeGiftEventGameResponse splayExchangeGift(SplayExchangeGiftEventGameRequest request);
//
//    public String buyItemShopEventGame(BuyItemShopEventGameRequest request)
//            throws SplayBusinessException;
//
//    public int countAllRecordEvent();
//
//}
