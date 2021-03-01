/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.game.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vtc.gamerid.gateway.common.controller.AbstractController;
import com.vtc.gamerid.gateway.common.dto.request.ExchangeXORequest;
import com.vtc.gamerid.gateway.game.service.KNBGameService;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Nov 16, 2018
 */
@RestController
public class KNBGameController extends AbstractController<KNBGameService> {
    
    @GetMapping("/get-balance-xo")
    public ResponseEntity<?> getBalanceXO(@RequestParam int scoinId){
        return new ResponseEntity<>(service.getBalanceXO(scoinId),
                HttpStatus.OK);
    }
    
    @PostMapping("/exchange-xo")
    public ResponseEntity<?> getBalanceXO(@RequestBody ExchangeXORequest request){
        return new ResponseEntity<>(service.exchangeXO(request, null),
                HttpStatus.OK);
    }

}
