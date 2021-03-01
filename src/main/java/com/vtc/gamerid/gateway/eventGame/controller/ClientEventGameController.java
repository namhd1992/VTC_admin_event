///***************************************************************************
// * Product made by Quang Dat *
// **************************************************************************/
//package com.vtc.gamerid.gateway.eventGame.controller;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.vtc.gamerid.gateway.common.controller.AbstractController;
//import com.vtc.gamerid.gateway.common.dto.request.BuyItemShopEventGameRequest;
//import com.vtc.gamerid.gateway.common.dto.request.GetEventGameRequest;
//import com.vtc.gamerid.gateway.common.dto.request.SplayExchangeGiftEventGameRequest;
//import com.vtc.gamerid.gateway.eventGame.service.ClientEventGameService;
//
///**
// * Author : Dat Le Quang
// * Email: Quangdat0993@gmail.com
// * Dec 26, 2018
// */
//@RestController
//public class ClientEventGameController extends AbstractController<ClientEventGameService> {
//    
//    @GetMapping("/anonymous/event-game")
//    public ResponseEntity<?> getEvent(GetEventGameRequest request) {
//        return response(toResult(service.getEvent(request), service.countAllRecordEvent()));
//    }
//    
//    @GetMapping("/join-event")
//    public ResponseEntity<?> joinEventGame() {
//        return response(toResult(service.joinEventGame()));
//    }
//    
//    @GetMapping("/use-link")
//    public ResponseEntity<?> useLink(@RequestParam Long parentScoinId,
//                                     @RequestParam String facebookId) {
//        return response(toResult(service.useLink(parentScoinId, facebookId)));
//    }
//    
//    @PostMapping("/exchange-gift")
//    public ResponseEntity<?> exchangeGift(@RequestBody SplayExchangeGiftEventGameRequest request) {
//        logger.debug("Request exchange Gift ========== {}" , gson.toJson(request));
//        return response(toResult(service.splayExchangeGift(request)));
//    }
//    
//    @PostMapping("/buy-item")
//    public ResponseEntity<?> buyItemShopEvent(@RequestBody BuyItemShopEventGameRequest request) {
//        logger.debug("Request exchange Gift ========== {}" , gson.toJson(request));
//        return response(toResult(service.buyItemShopEventGame(request)));
//    }
//
//}
