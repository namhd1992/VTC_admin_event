package com.vtc.gamerid.gateway.common.dao.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by phucnguyen on 05/12/2017.
 */
@Entity
@Table(name = "tblCheckinHistory")
public class TblCheckinHistory {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name="userInfo")
    private UserInfo userInfo;

    @Column(name = "createOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createOn;

    @ManyToOne
    @JoinColumn(name="checkinItem")
    private TblCheckinItem checkinItem;

    public TblCheckinHistory() {
    }

    public TblCheckinHistory(UserInfo userInfo, Date createOn, TblCheckinItem checkinItem) {
        this.userInfo = userInfo;
        this.createOn = createOn;
        this.checkinItem = checkinItem;
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

    public TblCheckinItem getCheckinItem() {
        return checkinItem;
    }

    public void setCheckinItem(TblCheckinItem checkinItem) {
        this.checkinItem = checkinItem;
    }
}
