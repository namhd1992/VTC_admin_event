package com.vtc.gamerid.gateway.luckyspin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vtc.gamerid.gateway.luckyspin.facade._interface.AdminLuckyspinSettingFacade;

/**
 * Created by phucnguyen on 14/09/2017.
 */
@RestController
public class AdminLuckyspinSettingController {
    @Autowired
    private AdminLuckyspinSettingFacade adminLuckyspinSettingFacade;

    @GetMapping("/admin/luckyspinSetting")
    public ResponseEntity<?> adminGetLuckyspinSetting(){
        return new ResponseEntity<>(adminLuckyspinSettingFacade.
                getLuckyspinSetting(), HttpStatus.OK);
    }

//    @PutMapping("/admin/luckyspinSetting")
//    public ResponseEntity<?> adminUpdateLuckyspinSetting(
//            @RequestBody LuckySpinSettingList dataRequest){
//        return new ResponseEntity<>(adminLuckyspinSettingFacade.
//                updateLuckyspinSetting(dataRequest.getDataRequest()), HttpStatus.OK);
//    }
}
