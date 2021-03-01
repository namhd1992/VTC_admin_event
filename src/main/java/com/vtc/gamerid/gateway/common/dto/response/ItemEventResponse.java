/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dto.response;

import com.vtc.gamerid.gateway.common.dao.entity.TblShopingItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Apr 3, 2019
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemEventResponse {

    private TblShopingItem item;

    private Long           newPrice;

}
