package com.vtc.gamerid.gateway.splay.transaction.controller;

import com.vtc.gamerid.gateway.splay.transaction.bean.AdminReportTransRequest;
import com.vtc.gamerid.gateway.splay.transaction.facade.AdminSplayTransFacade;
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
public class AdminSplayTransController {
    @Autowired
    private AdminSplayTransFacade adminSplayTransFacade;

    @RequestMapping(method = RequestMethod.GET, value = "/admin/splayTrans")
    public ResponseEntity<?> getSplayConfig(AdminReportTransRequest dataRequest){
        return new ResponseEntity<>(adminSplayTransFacade.getTransFromSplay(dataRequest), HttpStatus.OK);
    }

}
