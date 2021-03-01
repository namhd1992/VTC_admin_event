package com.vtc.gamerid.gateway.common.dao.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.vtc.gamerid.gateway.common.Constants;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 26/06/2017.
 */
@Entity
@Table(name = "tblLuckySpinHistory")
@Setter
@Getter
@NoArgsConstructor
public class LuckySpinHistory {
    @Id
    @GeneratedValue
    private int           id;

    @ManyToOne
    @JoinColumn(name = "userInfo")
    private UserInfo   userInfo;

    @ManyToOne
    @JoinColumn(name = "luckySpin")
    private LuckySpin  luckySpin;

    @ManyToOne
    @JoinColumn(name = "item")
    private TblLuckySpinItem item;

    @Temporal(TemporalType.TIMESTAMP)
    private Date          createOn;

    private String        description;

    private String        status;

    private long          value;

    private String        message;

    private String        turnType;

    @Transient
    private int           itemId;

    @Transient
    private String        itemName;

    @Transient
    private String        defaultImage;

    @Transient
    private String        luckyspinName;

    public LuckySpinHistory(UserInfo userInfo, LuckySpin luckySpin, TblLuckySpinItem item, Date createOn) {
        this.userInfo = userInfo;
        this.luckySpin = luckySpin;
        this.item = item;
        this.createOn = createOn;
        this.status = Constants.STATUS_RECIEVED;
    }

}
