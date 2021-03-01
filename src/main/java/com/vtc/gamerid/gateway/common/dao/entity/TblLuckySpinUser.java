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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 23/06/2017.
 */
@Entity
@Table(name = "tblLuckySpinUser")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TblLuckySpinUser {
    @Id
    @GeneratedValue
    private int          id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date         createOn;

    @Temporal(TemporalType.TIMESTAMP)
    private Date         updateOn;

    @ManyToOne
    @JoinColumn(name = "luckySpin")
    private LuckySpin luckySpin;

    @ManyToOne
    @JoinColumn(name = "userInfo")
    private UserInfo  userInfo;

    private int       turnsBuy;

    private long      turnsFree;

    private long      personalTopup;
    
    private long      balance;
    
    private long      accumulationPoint;

    public TblLuckySpinUser(LuckySpin luckySpin, UserInfo userInfo, int turnsBuy, int turnsFree) {
        this.luckySpin = luckySpin;
        this.userInfo = userInfo;
        this.turnsBuy = turnsBuy;
        this.turnsFree = turnsFree;
    }

}
