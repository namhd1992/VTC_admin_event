package com.vtc.gamerid.gateway.splay.transaction.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by phucnguyen on 11/06/2018.
 */
public class AdminReportTransResponse implements Serializable {
    private List<AdminTransactionGameInfo> items;
    private int pageindex;
    private int pagesize;
    private int totalrecord;
    private int code;
    private String message;

    public AdminReportTransResponse() {
    }

    public List<AdminTransactionGameInfo> getItems() {
        return items;
    }

    public void setItems(List<AdminTransactionGameInfo> items) {
        this.items = items;
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

    public int getTotalrecord() {
        return totalrecord;
    }

    public void setTotalrecord(int totalrecord) {
        this.totalrecord = totalrecord;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
