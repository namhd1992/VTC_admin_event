/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.turnover.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vtc.gamerid.gateway.common.controller.AbstractController;
import com.vtc.gamerid.gateway.common.dto.request.CreateUpdateTurnoverLandmarkRequest;
import com.vtc.gamerid.gateway.common.dto.request.GetTurnonverLandmarkRequest;
import com.vtc.gamerid.gateway.common.ultils.JsonMapperUtils;
import com.vtc.gamerid.gateway.turnover.service.TurnoverLandmarkService;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Sep 9, 2019
 */
@RestController
@RequestMapping("/admin/turnover-landmark")
public class TurnoverLandmarkController extends AbstractController<TurnoverLandmarkService> {
    
    @GetMapping("/get")
    public ResponseEntity<?> getTurnoverLandmark(GetTurnonverLandmarkRequest request) {
        logger.info("REQUEST GET TurnoverLandmark GAME ======== {}", JsonMapperUtils.toJson(request));
        return response(toResult(service.getTurnoverLandmark(request), service.countAllRecordEvent()));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTurnoverLandmark(@RequestBody List<CreateUpdateTurnoverLandmarkRequest> requests) {
        logger.info("REQUEST CREATE TurnoverLandmark GAME ======== {}", JsonMapperUtils.toJson(requests));
        return response(toResult(service.createTurnoverLandmark(requests)));
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateTurnoverLandmark(@RequestBody List<CreateUpdateTurnoverLandmarkRequest> requests) {
        logger.info("REQUEST UPDATE TurnoverLandmark GAME ======== {}", JsonMapperUtils.toJson(requests));
        return response(toResult(service.updateTurnoverLandmark(requests)));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteTurnoverLandmark(@RequestParam(value = "ids", required = false) List<Long> ids) {
        logger.info("REQUEST DELETE TurnoverLandmark GAME ======== {}", JsonMapperUtils.toJson(ids));
        return response(toResult(service.deleteTurnoverLandmark(ids)));
    }

}
