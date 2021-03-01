package com.vtc.gamerid.gateway.common.dao.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by phucnguyen on 13/12/2017.
 */
@Entity
@Table(name = "tblAuctionHistory")
public class TblAuctionHistory {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "userInfo")
    private UserInfo userInfo;

    @Column(name = "createOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createOn;

    @Column(name = "lastUpdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    @ManyToOne
    @JoinColumn(name = "auctionEvent")
    private TblAuctionEvent auctionEvent;

    @Column(name = "transactionLog")
    private String transactionLog;

    @Column(name = "currentPrice")
    private long currentPrice;

    @Transient
    private String userPaid;

    public TblAuctionHistory(UserInfo userInfo, Date createOn, Date lastUpdate, TblAuctionEvent auctionEvent, String transactionLog, long currentPrice) {
        this.userInfo = userInfo;
        this.createOn = createOn;
        this.lastUpdate = lastUpdate;
        this.auctionEvent = auctionEvent;
        this.transactionLog = transactionLog;
        this.currentPrice = currentPrice;
    }

    public TblAuctionHistory() {
    }

    public String getUserPaid() {
        return userPaid;
    }

    public void setUserPaid(String userPaid) {
        this.userPaid = userPaid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public TblAuctionEvent getAuctionEvent() {
        return auctionEvent;
    }

    public void setAuctionEvent(TblAuctionEvent auctionEvent) {
        this.auctionEvent = auctionEvent;
    }

    public String getTransactionLog() {
        return transactionLog;
    }

    public void setTransactionLog(String transactionLog) {
        this.transactionLog = transactionLog;
    }

    public long getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(long currentPrice) {
        this.currentPrice = currentPrice;
    }
}
