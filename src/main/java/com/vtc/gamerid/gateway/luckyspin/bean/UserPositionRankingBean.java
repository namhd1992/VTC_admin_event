package com.vtc.gamerid.gateway.luckyspin.bean;

import java.io.Serializable;

/**
 * Created by phucnguyen on 09/11/2017.
 */
public class UserPositionRankingBean implements Serializable {
    private long rankingValue;
    private int rankingPosition;

    public UserPositionRankingBean() {
    }

    public UserPositionRankingBean(long rankingValue, int rankingPosition) {
        this.rankingValue = rankingValue;
        this.rankingPosition = rankingPosition;
    }

    public long getRankingValue() {
        return rankingValue;
    }

    public void setRankingValue(long rankingValue) {
        this.rankingValue = rankingValue;
    }

    public int getRankingPosition() {
        return rankingPosition;
    }

    public void setRankingPosition(int rankingPosition) {
        this.rankingPosition = rankingPosition;
    }
}
