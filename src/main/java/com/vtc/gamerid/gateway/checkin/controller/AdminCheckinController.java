package com.vtc.gamerid.gateway.checkin.controller;

import com.vtc.gamerid.gateway.checkin.bean.AdminCheckinItemBean;
import com.vtc.gamerid.gateway.checkin.facade.AdminCheckinItemFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by phucnguyen on 05/12/2017.
 */
@RestController
public class AdminCheckinController {
    @Autowired
    private AdminCheckinItemFacade checkinItemFacade;

    @RequestMapping(method = RequestMethod.GET, value = "/admin/checkinItem")
    public ResponseEntity<?> getCheckinItem(){
        return new ResponseEntity<>(checkinItemFacade.getListItemCheckin(),
                HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/admin/checkinItem")
    public ResponseEntity<?> updateCheckinItem(@RequestBody AdminCheckinItemBean dataRequest){
        return new ResponseEntity<>(checkinItemFacade.updateCheckinItem(dataRequest),
                HttpStatus.OK);
    }
}
