package com.vtc.gamerid.gateway.splay.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by phucnguyen on 30/01/2018.
 */
public class SplayEventResponse implements Serializable {
    private int code;
    private String message;
    private List<SplayEventBean> items;

    public SplayEventResponse() {
    }

    public SplayEventResponse(int code, String message) {
        this.code = code;
        this.message = message;
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

    public List<SplayEventBean> getItems() {
        return items;
    }

    public void setItems(List<SplayEventBean> items) {
        this.items = items;
    }
}
