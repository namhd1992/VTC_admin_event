package com.vtc.gamerid.gateway.splay.bean;

import java.io.Serializable;

/**
 * Created by phucnguyen on 28/02/2018.
 */
public class HelpSplayBean implements Serializable {
    private String newValue = null;

    public HelpSplayBean() {
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }
}
