package com.vtc.gamerid.gateway.common.dao.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by phucnguyen on 16/01/2018.
 */
@Entity
@Table(name = "tblSplayTransaction")
public class TblSplayTransaction {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "splayId")
    private long splayId;

    @Column(name = "amount")
    private long amount;

    @Column(name = "fee")
    private long fee;

    @Column(name = "promotion")
    private long promotion;

    @Column(name = "totalAmount")
    private long totalAmount;

    @Column(name = "createBy")
    private int createBy;

    @Column(name = "createOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createOn;

    @Column(name = "status")
    private int status;

    @Column(name = "dataRequest")
    private String dataRequest;

    @Column(name = "dataResponse")
    private String dataResponse;

    @Column(name = "description")
    private String description;

    @Column(name = "functionName")
    private String functionName;

    public TblSplayTransaction() {
    }

    public TblSplayTransaction(long splayId, long amount, long fee, long promotion, long totalAmount, int createBy, Date createOn, int status, String dataRequest, String dataResponse, String description, String functionName) {
        this.splayId = splayId;
        this.amount = amount;
        this.fee = fee;
        this.promotion = promotion;
        this.totalAmount = totalAmount;
        this.createBy = createBy;
        this.createOn = createOn;
        this.status = status;
        this.dataRequest = dataRequest;
        this.dataResponse = dataResponse;
        this.description = description;
        this.functionName = functionName;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSplayId() {
        return splayId;
    }

    public void setSplayId(long splayId) {
        this.splayId = splayId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getFee() {
        return fee;
    }

    public void setFee(long fee) {
        this.fee = fee;
    }

    public long getPromotion() {
        return promotion;
    }

    public void setPromotion(long promotion) {
        this.promotion = promotion;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDataRequest() {
        return dataRequest;
    }

    public void setDataRequest(String dataRequest) {
        this.dataRequest = dataRequest;
    }

    public String getDataResponse() {
        return dataResponse;
    }

    public void setDataResponse(String dataResponse) {
        this.dataResponse = dataResponse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
