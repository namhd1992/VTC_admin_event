/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.game.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vtc.gamerid.gateway.common.controller.AbstractController;
import com.vtc.gamerid.gateway.common.dto.request.GameRankingUpdateAndCreateRequest;
import com.vtc.gamerid.gateway.game.service.AdminGameRankingService;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Apr 14, 2020
 */
@RestController
@RequestMapping("/admin/game-ranking")
public class AdminGameRankingController extends AbstractController<AdminGameRankingService> {
    
    @GetMapping("/{game_ranking_id}")
    public ResponseEntity<?> getSplayGameById(@PathVariable(value = "game_ranking_id") Long id) {
        return response(toResult(service.getGameRankingById(id)));
    }

    @GetMapping
    public ResponseEntity<?> getSplayGame() {
        return response(toResult(service.getGameRanking()));
    }

    @PostMapping
    public ResponseEntity<?> createGameRanking(@RequestBody GameRankingUpdateAndCreateRequest request) {
        return response(toResult(service.createGameRanking(request)));
    }
    
    @PutMapping
    public ResponseEntity<?> updateGameRanking(@RequestBody GameRankingUpdateAndCreateRequest request) {
        return response(toResult(service.updateGameRanking(request)));
    }
    
    @DeleteMapping
    public ResponseEntity<?> deleteGameRanking(@RequestParam(value = "id") Long id) {
        return response(toResult(service.deleteGameRanking(id)));
    }
}
