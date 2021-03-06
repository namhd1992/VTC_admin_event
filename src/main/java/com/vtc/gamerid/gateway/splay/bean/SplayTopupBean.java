package com.vtc.gamerid.gateway.splay.bean;

import com.vtc.gamerid.gateway.common.dto.request.SplayBaseRequest;

/**
 * Created by phucnguyen on 12/01/2018.
 */
public class SplayTopupBean extends SplayBaseRequest {
    private int amount;
    private String description;
    private long transId;
    private int serviceId;
    /*  1 - Checkin
        2 - Mission
        3 - Luckyspin
        4 - Giftcode
        5 - Auction
    * */
    private String sign;

    public SplayTopupBean(String api_key, String access_token, long accountId) {
        super(api_key, access_token, accountId);
    }

    public SplayTopupBean(String api_key, String access_token, long accountId, int amount, String description, long transId, int serviceId) {
        super(api_key, access_token, accountId);
        this.amount = amount;
        this.description = description;
        this.transId = transId;
        this.serviceId = serviceId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getTransId() {
        return transId;
    }

    public void setTransId(long transId) {
        this.transId = transId;
    }
}
