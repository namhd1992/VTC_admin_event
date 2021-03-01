/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.eventGame.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vtc.gamerid.gateway.common.controller.AbstractController;
import com.vtc.gamerid.gateway.common.dto.request.CreateUpdateEventGameRequest;
import com.vtc.gamerid.gateway.common.dto.request.GetEventGameRequest;
import com.vtc.gamerid.gateway.common.ultils.JsonMapperUtils;
import com.vtc.gamerid.gateway.eventGame.service.AdminEventGameService;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Jan 3, 2019
 */
@RestController
@RequestMapping("/admin/event-game")
public class AdminEventGameController extends AbstractController<AdminEventGameService> {

    @GetMapping("/get")
    public ResponseEntity<?> getEvent(GetEventGameRequest request) {
        return response(toResult(service.getEvent(request)));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createEvent(@RequestBody CreateUpdateEventGameRequest request) {
        logger.info("REQUEST CREATE EVENT GAME ======== {}", JsonMapperUtils.toJson(request));
        return response(toResult(service.createEvent(request)));
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateEvent(@RequestBody CreateUpdateEventGameRequest request) {
        logger.info("REQUEST UPDATE EVENT GAME ======== {}", JsonMapperUtils.toJson(request));
        return response(toResult(service.updateEvent(request)));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteEvent(@RequestParam Long eventId) {
        logger.info("REQUEST DELETE EVENT GAME ======== {}", JsonMapperUtils.toJson(eventId));
        return response(toResult(service.deleteEvent(eventId)));
    }

}
