/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Dec 20, 2018
 */
@Setter
@Getter
@AllArgsConstructor
public class ReportSourceUserGameResponse {
    
    private List<ReportSourceUserGameMainResponse> ReportSourceUserGameMainResponse;
    
    private String linkDownLoad;

}
