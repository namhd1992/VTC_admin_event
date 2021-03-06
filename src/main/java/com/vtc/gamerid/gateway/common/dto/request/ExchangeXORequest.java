/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Nov 19, 2018
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeXORequest {

    private long   AccountId;

    private long   XU;

    private long   XO;

    private String Descript;

    private long   TimeStamp;

    private long   TransId;

    private int    Action;

    private String Sign;

}
