/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dto.response;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * May 11, 2020
 */
@Getter
@Setter
@NoArgsConstructor
public class AdminGameRankingItemReportResponse {

    private String  userName;

    private String  rankName;

    private String  itemName;

    private boolean isReceived;

    private Date    receivedDate;

}
