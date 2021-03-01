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
 * Apr 9, 2019
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateItemShopEventRequest {

    private long id;

    private long newPrice;

}
