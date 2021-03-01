package com.vtc.gamerid.gateway.splay.transaction.bean;

import org.joda.time.DateTime;

import java.io.Serializable;

/**
 * Created by phucnguyen on 11/06/2018.
 */
public class AdminTransactionGameInfo implements Serializable {
    private long rownumber;
    private long id;
    private int serviceId;
    private long accountId;
    private String accountName;
    private int amount;
    private String serviceType;
    private String refId;
    private String description;
    private String clientIp;
    private DateTime createTime;
    private int createDate;
    private int amountScoin;
    private int status;

    public AdminTransactionGameInfo() {
    }

    public long getRownumber() {
        return rownumber;
    }

    public void setRownumber(long rownumber) {
        this.rownumber = rownumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public DateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(DateTime createTime) {
        this.createTime = createTime;
    }

    public int getCreateDate() {
        return createDate;
    }

    public void setCreateDate(int createDate) {
        this.createDate = createDate;
    }

    public int getAmountScoin() {
        return amountScoin;
    }

    public void setAmountScoin(int amountScoin) {
        this.amountScoin = amountScoin;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
