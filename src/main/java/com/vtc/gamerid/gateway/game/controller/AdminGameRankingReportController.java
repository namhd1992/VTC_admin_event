/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.game.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vtc.gamerid.gateway.common.controller.AbstractController;
import com.vtc.gamerid.gateway.game.service.AdminGameRankingReportService;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * May 11, 2020
 */
@RestController
@RequestMapping("/admin/game-ranking/report")
public class AdminGameRankingReportController extends AbstractController<AdminGameRankingReportService> {
    
    @GetMapping("/rank")
    public ResponseEntity<?> RankReport(@RequestParam(value = "game_raking_id") Long gameRakingId,
                                          @RequestParam(value = "week") int weekOfYear) {
        return response(toResult(service.RankReport(gameRakingId, weekOfYear)));
    }
    
    @GetMapping("/item")
    public ResponseEntity<?> itemReport(@RequestParam(value = "game_raking_id") Long gameRakingId,
                                          @RequestParam(value = "week") int weekOfYear) {
        return response(toResult(service.itemReport(gameRakingId, weekOfYear)));
    }

}
