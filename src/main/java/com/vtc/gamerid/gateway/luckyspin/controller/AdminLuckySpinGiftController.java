package com.vtc.gamerid.gateway.luckyspin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vtc.gamerid.gateway.common.controller.AbstractController;
import com.vtc.gamerid.gateway.common.dto.request.BaseRequest;
import com.vtc.gamerid.gateway.luckyspin.service._interface.AdminLuckySpinGiftService;

@RestController
public class AdminLuckySpinGiftController extends AbstractController<AdminLuckySpinGiftService> {
    
    @GetMapping("/admin/luckySpin-gift")
    public ResponseEntity<?> getListLuckySpin(BaseRequest request) {
        return response(toResult(service.getItemGift(request), service.countLuckySpinGift()));
    }

}
