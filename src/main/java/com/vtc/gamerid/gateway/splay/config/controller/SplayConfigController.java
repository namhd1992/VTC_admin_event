package com.vtc.gamerid.gateway.splay.config.controller;

import com.vtc.gamerid.gateway.splay.config.facade.SplayConfigFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by phucnguyen on 11/06/2018.
 */
@RestController
public class SplayConfigController {
    @Autowired
    private SplayConfigFacade splayConfigFacade;

    @RequestMapping(method = RequestMethod.GET, value = "/admin/splayConfig")
    public ResponseEntity<?> getSplayConfig(){
        return new ResponseEntity<>(splayConfigFacade.getSplayConfig(), HttpStatus.OK);
    }
}
