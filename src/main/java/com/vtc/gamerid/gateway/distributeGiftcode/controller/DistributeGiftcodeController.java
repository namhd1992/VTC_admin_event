package com.vtc.gamerid.gateway.distributeGiftcode.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vtc.gamerid.gateway.distributeGiftcode.bean.DistributeGiftcodeBean;
import com.vtc.gamerid.gateway.distributeGiftcode.facade.DistributeGiftcodeFacade;

/**
 * Created by phucnguyen on 23/04/2018.
 */
@RestController
public class DistributeGiftcodeController {
    @Autowired
    private DistributeGiftcodeFacade distributeGiftcodeFacade;

    @RequestMapping(value="/admin/distributeGiftcode", method= RequestMethod.POST)
    public ResponseEntity<?> distributeGiftcode(
            @ModelAttribute DistributeGiftcodeBean dataRequest) throws IOException {
        return new ResponseEntity<>(distributeGiftcodeFacade.
                distributeGiftcode(dataRequest), HttpStatus.OK);
    }

//    @RequestMapping(value="/admin/reportDistributeGiftcode", method= RequestMethod.GET)
//    public ResponseEntity<?> reportDistributeGiftcode(
//            DistributeGiftcodeFilter dataRequest) {
//        return new ResponseEntity<>(distributeGiftcodeFacade.
//                reportDistributeGiftcode(dataRequest), HttpStatus.OK);
//    }
}
