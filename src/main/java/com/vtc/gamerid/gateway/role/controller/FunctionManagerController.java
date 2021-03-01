package com.vtc.gamerid.gateway.role.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vtc.gamerid.gateway.role.facade._interface.FunctionManagerFacade;

/**
 * Created by phucnguyen on 03/05/2017.
 */
@RestController
public class FunctionManagerController {
    @Autowired
    private FunctionManagerFacade functionManagerFacade;

    @RequestMapping(method = RequestMethod.GET, value = "/admin/function")
    public ResponseEntity<?> getAllFunction(@RequestParam Map<String, Object> dataRequest) {
        return new ResponseEntity<>(functionManagerFacade.
                getListFunction(dataRequest), HttpStatus.OK);
    }
}
