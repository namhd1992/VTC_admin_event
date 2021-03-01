package com.vtc.gamerid.gateway.game.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vtc.gamerid.gateway.common.controller.AbstractController;
import com.vtc.gamerid.gateway.game.bean.AdminSplayGameBeanRequest;
import com.vtc.gamerid.gateway.game.bean.AdminSplayGameFilter;
import com.vtc.gamerid.gateway.game.facade.AdminSplayGameFacade;

/**
 * Created by phucnguyen on 06/12/2017.
 */
@RestController
public class AdminGameController extends AbstractController<AdminSplayGameFacade> {

    @GetMapping("/admin/splayGame")
    public ResponseEntity<?> getSplayGame(AdminSplayGameFilter dataRequest) {
        return response(toResult(service.getSplayGameList(dataRequest)));
    }

    @PostMapping("/admin/splayGame")
    public ResponseEntity<?> createSplayGame(@RequestBody AdminSplayGameBeanRequest dataRequest) {
        return response(toResult(service.createSplayGame(dataRequest)));
    }

    @PutMapping("/admin/splayGame")
    public ResponseEntity<?> updateSplayGame(@RequestBody AdminSplayGameBeanRequest dataRequest) {
        return response(toResult(service.updateSplayGame(dataRequest)));
    }

    @DeleteMapping("/admin/splayGame")
    public ResponseEntity<?> deleteSplayGame(@RequestParam Long splayGameId) {
        return response(toResult(service.deleteSplayGame(splayGameId)));
    }

}
