/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.giftcode.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vtc.gamerid.gateway.common.controller.AbstractController;
import com.vtc.gamerid.gateway.common.dto.request.GiftcodeCreateAndUpdateRequest;
import com.vtc.gamerid.gateway.common.dto.response.GiftcodeGetRequest;
import com.vtc.gamerid.gateway.giftcode.service.GiftcodeServcie;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Apr 27, 2020
 */
@RestController
@RequestMapping("/admin/giftcode")
public class GiftcodeController extends AbstractController<GiftcodeServcie> {
    
    @GetMapping()
    public ResponseEntity<?> getGiftcodeInfo(GiftcodeGetRequest request) {
        return response(toResult(service.getGiftcodeInfo(request)));
    }
    
    @PostMapping()
    public ResponseEntity<?> createGiftcode(@ModelAttribute GiftcodeCreateAndUpdateRequest request)
            throws IOException {
        return response(toResult(service.createGiftcode(request)));
    }

}
