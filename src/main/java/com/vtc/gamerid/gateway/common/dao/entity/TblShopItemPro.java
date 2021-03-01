package com.vtc.gamerid.gateway.common.dao.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 26/04/2018.
 */
@Entity
@Table(name = "tblShopItemPro")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TblShopItemPro {
    @Id
    @GeneratedValue
    private long           id;

    private long           shopItem;

    private long           shopPromotion;

    private String         tagView;

    private long           newPrice;

    private int            quantity;

    @Transient
    private TblShopingItem shopingItem;

    public TblShopItemPro(long shopItem, long shopPromotion, long newPrice, int quantity) {
        this.shopItem = shopItem;
        this.shopPromotion = shopPromotion;
        this.newPrice = newPrice;
        this.quantity = quantity;
    }

}
