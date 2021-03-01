package com.vtc.gamerid.gateway.splay.bean;

import java.io.Serializable;

/**
 * Created by phucnguyen on 30/01/2018.
 */
public class SplayEventRequest implements Serializable {
    private int eventtype;
    private int pageindex;
    private int pagesize;
    private String sign;

    public SplayEventRequest() {
    }

    public SplayEventRequest(int eventtype, int pageindex, int pagesize) {
        this.eventtype = eventtype;
        this.pageindex = pageindex;
        this.pagesize = pagesize;
    }

    public int getEventtype() {
        return eventtype;
    }

    public void setEventtype(int eventtype) {
        this.eventtype = eventtype;
    }

    public int getPageindex() {
        return pageindex;
    }

    public void setPageindex(int pageindex) {
        this.pageindex = pageindex;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
