/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * May 7, 2020
 */
@Getter
@Setter
@NoArgsConstructor
public class AdminRankReportResponse {

    private String rankName;

    private int    potsition;

    private String userName;

    private String server;

    private float  scoin;

}
