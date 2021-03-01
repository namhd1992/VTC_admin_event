package com.vtc.gamerid.gateway.common.dao.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Created by phucnguyen on 30/11/2017.
 */
@Entity
@Table(name = "tblAuctionEvent")
@Setter
@Getter
@NoArgsConstructor
public class TblAuctionEvent {
    
    @Id
    @GeneratedValue
    private long        id;

    private String      name;

    private String      description;

    private String      defaultImage;

    @Temporal(TemporalType.TIMESTAMP)
    private Date        createOn;
    
    @ManyToOne
    @JoinColumn(name = "createBy")
    private UserInfo createBy;

    private String      itemName;

    private int         type;

    private String      status;

    private long        amountStart;

    private long        priceStep;

    private long        topPrice;

    private String      coinType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date        fromDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date        toDate;

    @Transient
    private long        amountUserPaid;

    @Transient
    private long        countUserAuction;

    private String      winningMessage;

    private String      listImage;

    private long        scoinGameId;

    private boolean     highLights;

    public TblAuctionEvent(String name, String description, String defaultImage, Date createOn, UserInfo createBy, String itemName, int type, String status, long amountStart, long priceStep, Date fromDate, Date toDate) {
        this.name = name;
        this.description = description;
        this.defaultImage = defaultImage;
        this.createOn = createOn;
        this.createBy = createBy;
        this.itemName = itemName;
        this.type = type;
        this.status = status;
        this.amountStart = amountStart;
        this.priceStep = priceStep;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

}
