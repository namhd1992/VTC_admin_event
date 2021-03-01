package com.vtc.gamerid.gateway.splay.transaction.bean;

import java.io.Serializable;

/**
 * Created by phucnguyen on 11/06/2018.
 */
public class AdminReportTransRequest implements Serializable {
    private int serviceId = -1;
    private String serviceType = null; //scoin, telco, inapp, sms
    private int currentPage = 0; //offset
    private int pageSize = 20; //limit
    private int fromDateInt = -1;
    private int toDateInt = -1;
    private String sign = null;

    public AdminReportTransRequest() {
    }

    public AdminReportTransRequest(int serviceId, String serviceType, int currentPage, int pageSize, int fromDateInt, int toDateInt, String sign) {
        this.serviceId = serviceId;
        this.serviceType = serviceType;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.fromDateInt = fromDateInt;
        this.toDateInt = toDateInt;
        this.sign = sign;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getFromDateInt() {
        return fromDateInt;
    }

    public void setFromDateInt(int fromDateInt) {
        this.fromDateInt = fromDateInt;
    }

    public int getToDateInt() {
        return toDateInt;
    }

    public void setToDateInt(int toDateInt) {
        this.toDateInt = toDateInt;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
