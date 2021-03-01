package com.vtc.gamerid.gateway.luckyspin.bean;

import java.io.Serializable;

/**
 * Created by phucnguyen on 08/11/2017.
 */
public class SpinRankingBean implements Serializable {
    private String fullName;
    private long rankingValue;

    public SpinRankingBean() {
    }

    public SpinRankingBean(String fullName, long rankingValue) {
        this.fullName = fullName;
        this.rankingValue = rankingValue;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public long getRankingValue() {
        return rankingValue;
    }

    public void setRankingValue(long rankingValue) {
        this.rankingValue = rankingValue;
    }
}
