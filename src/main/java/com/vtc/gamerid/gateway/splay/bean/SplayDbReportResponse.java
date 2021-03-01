package com.vtc.gamerid.gateway.splay.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by phucnguyen on 31/01/2018.
 */
public class SplayDbReportResponse implements Serializable {
    private long totalNRU;
    private long totalDAU;
    private List<ReportPieChart> pieChartNRU;
    private List<ReportTimelineBean> timelineNRU;
    private List<ReportTimelineBean> timelineDAU;

    public SplayDbReportResponse() {
    }

    public SplayDbReportResponse(long totalNRU, long totalDAU, List<ReportPieChart> pieChartNRU, List<ReportTimelineBean> timelineNRU, List<ReportTimelineBean> timelineDAU) {
        this.totalNRU = totalNRU;
        this.totalDAU = totalDAU;
        this.pieChartNRU = pieChartNRU;
        this.timelineNRU = timelineNRU;
        this.timelineDAU = timelineDAU;
    }

    public List<ReportPieChart> getPieChartNRU() {
        return pieChartNRU;
    }

    public void setPieChartNRU(List<ReportPieChart> pieChartNRU) {
        this.pieChartNRU = pieChartNRU;
    }

    public long getTotalNRU() {
        return totalNRU;
    }

    public void setTotalNRU(long totalNRU) {
        this.totalNRU = totalNRU;
    }

    public long getTotalDAU() {
        return totalDAU;
    }

    public void setTotalDAU(long totalDAU) {
        this.totalDAU = totalDAU;
    }

    public List<ReportTimelineBean> getTimelineNRU() {
        return timelineNRU;
    }

    public void setTimelineNRU(List<ReportTimelineBean> timelineNRU) {
        this.timelineNRU = timelineNRU;
    }

    public List<ReportTimelineBean> getTimelineDAU() {
        return timelineDAU;
    }

    public void setTimelineDAU(List<ReportTimelineBean> timelineDAU) {
        this.timelineDAU = timelineDAU;
    }
}
