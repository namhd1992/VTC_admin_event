package com.vtc.gamerid.gateway.luckyspin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vtc.gamerid.gateway.common.controller.AbstractController;
import com.vtc.gamerid.gateway.luckyspin.bean.SpinItemBeanRequest;
import com.vtc.gamerid.gateway.luckyspin.bean.SpinItemSqlRequest;
import com.vtc.gamerid.gateway.luckyspin.facade._interface.AdminSpinItemFacade;

/**
 * Created by phucnguyen on 30/06/2017.
 */
@RestController
public class SpinItemAdminController extends AbstractController<AdminSpinItemFacade> {

    @GetMapping("/admin/adminItemOfSpin")
    public ResponseEntity<?> adminItemOfSpin(SpinItemSqlRequest spinItemSqlRequest){
        return new ResponseEntity<>(service.getItemByPublisher(spinItemSqlRequest), HttpStatus.OK);
    }

    @PostMapping("/admin/adminItemOfSpin")
    public ResponseEntity<?> createItemOfSpin(@ModelAttribute SpinItemBeanRequest itemBeanRequest){
        return new ResponseEntity<>(service.createItem(itemBeanRequest), HttpStatus.OK);
    }

    @PutMapping("/admin/adminItemOfSpin")
    public ResponseEntity<?> updateItemOfSpin(@RequestBody SpinItemBeanRequest itemBeanRequest){
        return new ResponseEntity<>(service.updateItem(itemBeanRequest), HttpStatus.OK);
    }

    @DeleteMapping("/admin/adminItemOfSpin")
    public ResponseEntity<?> deleteItemOfSpin(@RequestParam Long itemId){
        return new ResponseEntity<>(service.deleteItem(itemId), HttpStatus.OK);
    }
}
