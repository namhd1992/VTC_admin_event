package com.vtc.gamerid.gateway.common.dao.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Created by phucnguyen on 23/04/2018.
 */
@Entity
@Table(name = "tblShopingItem")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TblShopingItem {
    @Id
    @GeneratedValue
    private long    id;

    private String  name;

    private String  hotTitle;

    @Temporal(TemporalType.TIMESTAMP)
    private Date    createOn;
    
    private Integer packageId;

    private long    price;

    private String  coinType;

    private long    pricePoint;

    private int     quantity;

    private long    shopId;

    private String  defaultImage;

    private String  bigImage;

    private Long    createBy;

    private String  status;

    private String  firstType;

    private String  secondType;

    private long    numberLike;

    private String  description;

    private String  screenShot;

    @Transient
    private long    salePrice    = -1;

    @Transient
    private long    saleQuantity = -1;

    private int     rare;

    private int     itemType;

    private boolean hasPromotion;

    public TblShopingItem(String name, String hotTitle, Date createOn, long price, int quantity,
                          long shopId, String defaultImage, String bigImage, Long createBy, String status,
                          String firstType, String secondType, long numberLike, int rare) {
        this.name = name;
        this.hotTitle = hotTitle;
        this.createOn = createOn;
        this.price = price;
        this.quantity = quantity;
        this.shopId = shopId;
        this.defaultImage = defaultImage;
        this.bigImage = bigImage;
        this.createBy = createBy;
        this.status = status;
        this.firstType = firstType;
        this.secondType = secondType;
        this.numberLike = numberLike;
        this.rare = rare;
    }

}
