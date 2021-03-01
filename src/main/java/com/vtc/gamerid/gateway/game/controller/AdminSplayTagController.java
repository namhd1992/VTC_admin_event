package com.vtc.gamerid.gateway.game.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vtc.gamerid.gateway.common.controller.AbstractController;
import com.vtc.gamerid.gateway.common.ultils.JsonMapperUtils;
import com.vtc.gamerid.gateway.game.bean.AdminSplayTagBean;
import com.vtc.gamerid.gateway.game.bean.ClientSplayTagFilter;
import com.vtc.gamerid.gateway.game.facade.AdminSplayTagFacade;

/**
 * Created by phucnguyen on 03/04/2018.
 */
@RestController
public class AdminSplayTagController extends AbstractController<AdminSplayTagFacade> {
  
    @GetMapping("/anonymous/splayTag")
    public ResponseEntity<?> anonymoust_getSplayTag(ClientSplayTagFilter dataRequest){
        return response(toResult(service.getSplayTag(dataRequest), service.countSplayTag(dataRequest)));
    }
    
    @GetMapping("/admin/splayTag")
    public ResponseEntity<?> getSplayTag(ClientSplayTagFilter dataRequest){
        logger.info("REQUEST ============================ {}", JsonMapperUtils.toJson(dataRequest));
        return response(toResult(service.getSplayTag(dataRequest), service.countSplayTag(dataRequest)));
    }

    @PostMapping("/admin/splayTag")
    public ResponseEntity<?> createSplayTag(@RequestBody AdminSplayTagBean dataRequest){
        return response(toResult(service.createSplayTag(dataRequest)));
    }

    @PutMapping("/admin/splayTag")
    public ResponseEntity<?> updateSplayTag(@RequestBody AdminSplayTagBean dataRequest){
        return new ResponseEntity<>(service.updateSplayTag(dataRequest),
                HttpStatus.OK);
    }

    @DeleteMapping("/admin/splayTag")
    public ResponseEntity<?> deleteSplayTag(@RequestParam(defaultValue = "0") long id){
        return new ResponseEntity<>(service.deleteSplayTag(id),
                HttpStatus.OK);
    }
}
