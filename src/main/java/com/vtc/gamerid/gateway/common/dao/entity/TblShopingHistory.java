package com.vtc.gamerid.gateway.common.dao.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Created by phucnguyen on 03/05/2018.
 */
@Entity
@Table(name = "tblShopingHistory")
@Getter
@Setter
@NoArgsConstructor
public class TblShopingHistory {
    @Id
    @GeneratedValue
    private long   id;

    private Long   buyerSplayId;

    private long   receiverScoinId;

    private long   itemId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date   createOn;

    private long   price;

    private String status;

    private String note;

    @Temporal(TemporalType.TIMESTAMP)
    private Date   lastUpdateOn;

    private Long   lastUpdateBy;

    @Transient
    private String userType = null;

    public TblShopingHistory(Long buyerSplayId, long receiverScoinId, long itemId, Date createOn,
                             long price, String status, String note, Date lastUpdateOn,
                             Long lastUpdateBy) {
        this.buyerSplayId = buyerSplayId;
        this.receiverScoinId = receiverScoinId;
        this.itemId = itemId;
        this.createOn = createOn;
        this.price = price;
        this.status = status;
        this.note = note;
        this.lastUpdateOn = lastUpdateOn;
        this.lastUpdateBy = lastUpdateBy;
    }

}
