/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.game.service;

import java.util.List;

import com.vtc.gamerid.gateway.common.dto.response.AdminGameRankingItemReportResponse;
import com.vtc.gamerid.gateway.common.dto.response.AdminRankReportResponse;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * May 7, 2020
 */
public interface AdminGameRankingReportService {
    
    List<AdminRankReportResponse> RankReport(Long gameRakingId, int weekOfYear);
    
    List<AdminGameRankingItemReportResponse> itemReport(Long gameRakingId, int weekOfYear);

}
