package com.vtc.gamerid.gateway.scoin.authorization.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vtc.gamerid.gateway.common.controller.AbstractController;
import com.vtc.gamerid.gateway.scoin.authorization.bean.AdbLoginResponse;
import com.vtc.gamerid.gateway.scoin.authorization.bean.AdbLoginScoinRequest;
import com.vtc.gamerid.gateway.scoin.authorization.facade.LoginScoinFacade;

/**
 * Created by phucnguyen on 22/05/2017.
 */
@RestController
@RequestMapping("/anonymous")
public class LoginScoinController extends AbstractController<LoginScoinFacade> {

    @PostMapping("/loginScoinFromAdb")
    public ResponseEntity<?> loginScoin(@RequestBody AdbLoginScoinRequest adbLoginScoinRequest) {
        AdbLoginResponse adbLoginResponse = service.loginScoinFromAdb(adbLoginScoinRequest);
        if (!ObjectUtils.isEmpty(adbLoginResponse.getAccess_token())) {
            return new ResponseEntity<>(adbLoginResponse, HttpStatus.OK);
        }
        return new ResponseEntity<Object>(adbLoginResponse, HttpStatus.UNAUTHORIZED);
    }
}
