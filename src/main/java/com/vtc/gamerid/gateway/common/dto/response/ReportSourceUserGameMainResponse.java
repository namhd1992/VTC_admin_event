/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Dec 17, 2018
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReportSourceUserGameMainResponse {
    
    private String Date;

    private String game;

    private long   totalUser;

    private String sourceUser;

}
