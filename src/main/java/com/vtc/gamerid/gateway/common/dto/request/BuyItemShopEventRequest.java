/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dto.request;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Apr 10, 2019
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BuyItemShopEventRequest {

    private long      accountid;

    private int       packid;

    private int       serverid;

    private Timestamp time;

    private String    sign;

    private String    title;

    private String    content;

}
