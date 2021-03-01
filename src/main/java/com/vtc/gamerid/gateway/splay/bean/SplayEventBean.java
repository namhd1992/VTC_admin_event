package com.vtc.gamerid.gateway.splay.bean;

import java.io.Serializable;

/**
 * Created by phucnguyen on 30/01/2018.
 */
public class SplayEventBean implements Serializable {
    private int id;
    private String title;
    private int eventtype;
    private int itemtype;
    private int itemvalue;
    private String startdate;
    private String enddate;

    public SplayEventBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEventtype() {
        return eventtype;
    }

    public void setEventtype(int eventtype) {
        this.eventtype = eventtype;
    }

    public int getItemtype() {
        return itemtype;
    }

    public void setItemtype(int itemtype) {
        this.itemtype = itemtype;
    }

    public int getItemvalue() {
        return itemvalue;
    }

    public void setItemvalue(int itemvalue) {
        this.itemvalue = itemvalue;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }
}
