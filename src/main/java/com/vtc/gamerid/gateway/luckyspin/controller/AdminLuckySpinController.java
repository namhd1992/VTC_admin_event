package com.vtc.gamerid.gateway.luckyspin.controller;

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
import com.vtc.gamerid.gateway.luckyspin.bean.AdSEventManagerBean;
import com.vtc.gamerid.gateway.luckyspin.bean.AdminSpinEventFilterRequest;
import com.vtc.gamerid.gateway.luckyspin.service._interface.AdminLuckySpinService;

/**
 * Created by phucnguyen on 23/06/2017.
 */
@RestController
public class AdminLuckySpinController extends AbstractController<AdminLuckySpinService> {
    
    @GetMapping("/admin/luckySpin")
    public ResponseEntity<?> getListLuckySpin(AdminSpinEventFilterRequest request) {
        return response(toResult(service.getListLuckySpin(request), service.adminCountGetLuckySpin(request)));
    }

    @PostMapping("/admin/luckySpin")
    public ResponseEntity<?> createLuckySpin(@RequestBody AdSEventManagerBean request) {
        return response(toResult(service.createLuckySpin(request)));
    }

    @PutMapping("/admin/luckySpin")
    public ResponseEntity<?> updateLuckySpin(@RequestBody AdSEventManagerBean request) {
        logger.info("REQUEST UPDATE SPIN =========== {}", JsonMapperUtils.toJson(request));
        return response(toResult(service.updateLuckySpin(request)));
    }

    @DeleteMapping("/admin/luckySpin")
    public ResponseEntity<?> deleteLuckySpin(@RequestParam Long spinEventId) {
        return response(toResult(service.deleteLuckySpin(spinEventId)));
    }
}
