package com.vtc.gamerid.gateway.upload.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vtc.gamerid.gateway.common.controller.AbstractController;
import com.vtc.gamerid.gateway.common.dto.request.DeleteImageRequest;
import com.vtc.gamerid.gateway.common.dto.request.UploadImageRequest;
import com.vtc.gamerid.gateway.common.ultils.JsonMapperUtils;
import com.vtc.gamerid.gateway.upload.bean.UploadFileBean;
import com.vtc.gamerid.gateway.upload.facade._interface.UploadFileFacade;

/**
 * Created by phucnguyen on 17/05/2017.
 */
@RestController
@RequestMapping("/admin")
public class UploadFileController extends AbstractController<UploadFileFacade> {

    @PostMapping("/uploadFile")
    public ResponseEntity<?> uploadFile(@ModelAttribute UploadFileBean dataRequest) {
        return new ResponseEntity<>(service.uploadFile(dataRequest), HttpStatus.OK);
    }
    
    @PostMapping("/uploadImage")
    public ResponseEntity<?> uploadImage(@ModelAttribute UploadImageRequest request) {
        logger.info("REQUEST UPLOAD IMAGE ======== {}", JsonMapperUtils.toJson(request));
        return response(toResult(service.uploadImage(request)));
    }
    
    @PostMapping("/deleteImage")
    public ResponseEntity<?> deleteImage(@RequestBody DeleteImageRequest request) {
        logger.info("REQUEST DELETE IMAGE ======== {}", JsonMapperUtils.toJson(request));
        return response(toResult(service.deleteImage(request)));
    }
}
