package com.vtc.gamerid.gateway.splay.bean;

import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.dto.response.ValidateBean;

/**
 * Created by phucnguyen on 31/01/2018.
 */
public class SplayDbReportRequest {
    private long fromDate = -1;
    private long toDate = -1;
    private int timeSwitch = -1;
    private String sign = null;

    public ValidateBean validate(){
        if(this.fromDate < 1 || this.toDate < 1 || this.timeSwitch < 1)
            return new ValidateBean(false, "Datetime invalid");
        if(this.sign == null)
            return new ValidateBean(false, Constants.BAD_REQ);
        return new ValidateBean(true, Constants.SUCCESS);
    }
    public SplayDbReportRequest() {
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public long getFromDate() {
        return fromDate;
    }

    public void setFromDate(long fromDate) {
        this.fromDate = fromDate;
    }

    public long getToDate() {
        return toDate;
    }

    public void setToDate(long toDate) {
        this.toDate = toDate;
    }

    public int getTimeSwitch() {
        return timeSwitch;
    }

    public void setTimeSwitch(int timeSwitch) {
        this.timeSwitch = timeSwitch;
    }
}
