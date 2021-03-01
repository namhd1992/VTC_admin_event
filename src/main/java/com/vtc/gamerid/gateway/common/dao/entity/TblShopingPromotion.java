package com.vtc.gamerid.gateway.common.dao.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by phucnguyen on 26/04/2018.
 */
@Entity
@Table(name = "tblShopingPromotion")
@Setter
@Getter
@NoArgsConstructor
public class TblShopingPromotion {
    @Id
    @GeneratedValue
    private long                 id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date                 createOn;

    private String               name;

    private String               defaultImage;

    @Temporal(TemporalType.TIMESTAMP)
    private Date                 fromDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date                 toDate;

    private long                 shopId;

    private Long                 createBy;

    @Transient
    private List<TblShopItemPro> itemOfPromotion = new ArrayList<>();

    public TblShopingPromotion(String name, String defaultImage, Date fromDate, Date toDate,
                               long shopId, Long createBy) {
        this.name = name;
        this.defaultImage = defaultImage;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.shopId = shopId;
        this.createBy = createBy;
    }

}
