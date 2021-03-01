/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.game.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vtc.gamerid.gateway.common.controller.AbstractController;
import com.vtc.gamerid.gateway.common.dto.request.BaseRequest;
import com.vtc.gamerid.gateway.common.dto.request.GameRankingItemCreateAndUpdateRequest;
import com.vtc.gamerid.gateway.game.service.AdminGameRankingItemService;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Apr 24, 2020
 */
@RestController
@RequestMapping("/admin/game-ranking-item")
public class AdminGameRankingItemController extends AbstractController<AdminGameRankingItemService> {
    
    @GetMapping
    public ResponseEntity<?> getSplayGame(BaseRequest request,
                                          @RequestParam(value = "rank_id") Long rankId) {
        return response(toResult(service.getByGameRankingRank(request, rankId),
                service.countByGameRankingRank(rankId)));
    }
    
    @PostMapping
    public ResponseEntity<?> createGameRankingItem(@RequestBody GameRankingItemCreateAndUpdateRequest request) {
        return response(toResult(service.createGameRankingItem(request)));
    }
    
    @PutMapping
    public ResponseEntity<?> UpdateGameRankingItem(@RequestBody GameRankingItemCreateAndUpdateRequest request) {
        return response(toResult(service.UpdateGameRankingItem(request)));
    }
    
    @DeleteMapping
    public ResponseEntity<?> deleteGameRankingItem(@RequestParam(value = "id") Long id) {
        return response(toResult(service.deleteGameRankingItem(id)));
    }

}
