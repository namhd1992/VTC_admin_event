package com.vtc.gamerid.gateway.splay.bean;

import java.io.Serializable;

/**
 * Created by phucnguyen on 31/01/2018.
 */
public class ReportPieChart implements Serializable {
    private long gameId;
    private long value;

    public ReportPieChart() {
    }

    public ReportPieChart(long gameId, long value) {
        this.gameId = gameId;
        this.value = value;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
