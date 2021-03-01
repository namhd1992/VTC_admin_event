package com.vtc.gamerid.gateway.splay.bean;

/**
 * Created by phucnguyen on 31/01/2018.
 */
public class ReportTimelineBean {
    private long dateOn;
    private long value;

    public ReportTimelineBean() {
    }

    public ReportTimelineBean(long dateOn, long value) {
        this.dateOn = dateOn;
        this.value = value;
    }

    public long getDateOn() {
        return dateOn;
    }

    public void setDateOn(long dateOn) {
        this.dateOn = dateOn;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
