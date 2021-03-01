package com.vtc.gamerid.gateway.luckyspin.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by phucnguyen on 25/07/2017.
 */
public class LuckySpinHistoryClient implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Timestamp dateOn;
    private int totalRecord = 0;
    private int addAmount = 0;
    private int subAmount = 0;

    public LuckySpinHistoryClient(Timestamp dateOn, int totalRecord, int addAmount, int subAmount) {
        this.dateOn = dateOn;
        this.totalRecord = totalRecord;
        this.addAmount = addAmount;
        this.subAmount = subAmount;
    }

    public LuckySpinHistoryClient() {
    }

    public Timestamp getDateOn() {
        return dateOn;
    }

    public void setDateOn(Timestamp dateOn) {
        this.dateOn = dateOn;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getAddAmount() {
        return addAmount;
    }

    public void setAddAmount(int addAmount) {
        this.addAmount = addAmount;
    }

    public int getSubAmount() {
        return subAmount;
    }

    public void setSubAmount(int subAmount) {
        this.subAmount = subAmount;
    }
}
