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
import com.vtc.gamerid.gateway.common.dto.request.BaseRequest;
import com.vtc.gamerid.gateway.common.dto.request.UpdateAndCreateGameRankingRankRequest;
import com.vtc.gamerid.gateway.game.service.AdminGameRankingRankService;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Apr 16, 2020
 */
@RestController
@RequestMapping("/admin/game-ranking-rank")
public class AdminGameRankingRankController extends AbstractController<AdminGameRankingRankService> {
    
    @GetMapping("/{rank_id}")
    public ResponseEntity<?> getSplayGameById(@PathVariable(value = "rank_id") Long id) {
        return response(toResult(service.get(id)));
    }
    
    @GetMapping
    public ResponseEntity<?> getSplayGame(BaseRequest request,
                                          @RequestParam(value = "game_ranking_id") Long gameRankingId) {
        return response(toResult(service.getByGameRanking(request, gameRankingId),
                service.countByGameRanking(gameRankingId)));
    }
    
    @PostMapping
    public ResponseEntity<?> createRankingRank(@RequestBody UpdateAndCreateGameRankingRankRequest request) {
        return response(toResult(service.createRankingRank(request)));
    }
    
    @PutMapping
    public ResponseEntity<?> updateRankingRank(@RequestBody UpdateAndCreateGameRankingRankRequest request) {
        return response(toResult(service.updateRankingRank(request)));
    }
    
    @DeleteMapping
    public ResponseEntity<?> deleteGameRankingRank(@RequestParam(value = "id") Long gameRankingRankId) {
        return response(toResult(service.deleteGameRankingRank(gameRankingRankId)));
    }

}
