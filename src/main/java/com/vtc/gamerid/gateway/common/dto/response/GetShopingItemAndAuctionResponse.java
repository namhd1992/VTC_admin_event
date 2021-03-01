/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dto.response;

import com.vtc.gamerid.gateway.common.dao.entity.TblShopItemPro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Mar 15, 2019
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetShopingItemAndAuctionResponse {

//    private ClientShopAndAuctionBean shopingItemAndAuction;

    private TblShopItemPro           promotion;

}
