package com.vtc.gamerid.gateway.splay.config.bean;

import java.io.Serializable;

/**
 * Created by phucnguyen on 11/06/2018.
 */
public class SplayBankConfigBean implements Serializable {
    private double rate;
    private int scoinbonus;
    private int telcobonus;
    private int iapbonus;
    private int smsbonus;

    public SplayBankConfigBean() {
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getScoinbonus() {
        return scoinbonus;
    }

    public void setScoinbonus(int scoinbonus) {
        this.scoinbonus = scoinbonus;
    }

    public int getTelcobonus() {
        return telcobonus;
    }

    public void setTelcobonus(int telcobonus) {
        this.telcobonus = telcobonus;
    }

    public int getIapbonus() {
        return iapbonus;
    }

    public void setIapbonus(int iapbonus) {
        this.iapbonus = iapbonus;
    }

    public int getSmsbonus() {
        return smsbonus;
    }

    public void setSmsbonus(int smsbonus) {
        this.smsbonus = smsbonus;
    }
}
