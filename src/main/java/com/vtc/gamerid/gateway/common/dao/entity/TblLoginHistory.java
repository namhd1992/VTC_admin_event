package com.vtc.gamerid.gateway.common.dao.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by phucnguyen on 29/01/2018.
 */
@Entity
@Table(name = "tblLoginHistory")
public class TblLoginHistory {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "createOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createOn;

    @Column(name = "simbaId")
    private int simbaId;

    @Column(name = "scoinId")
    private long scoinId;

    @Column(name = "statusCode")
    private int statusCode;

    public TblLoginHistory() {
    }

    public TblLoginHistory(Date createOn, int simbaId, long scoinId, int statusCode) {
        this.createOn = createOn;
        this.simbaId = simbaId;
        this.scoinId = scoinId;
        this.statusCode = statusCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    public int getSimbaId() {
        return simbaId;
    }

    public void setSimbaId(int simbaId) {
        this.simbaId = simbaId;
    }

    public long getScoinId() {
        return scoinId;
    }

    public void setScoinId(long scoinId) {
        this.scoinId = scoinId;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
