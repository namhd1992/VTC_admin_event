/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dto.response;

import com.vtc.gamerid.gateway.common.dao.entity.TblShopItemPro;
import com.vtc.gamerid.gateway.common.dao.entity.TblShopingItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Mar 14, 2019
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetShopingItemResponse {
    
    private TblShopingItem shopingItem;
    
    private TblShopItemPro promotion;
    
}
