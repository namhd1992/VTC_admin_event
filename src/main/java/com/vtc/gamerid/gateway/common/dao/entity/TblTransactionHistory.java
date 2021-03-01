package com.vtc.gamerid.gateway.common.dao.entity;

import javax.persistence.*;

import com.vtc.gamerid.gateway.common.Constants;

import java.util.Date;

/**
 * Created by phucnguyen on 12/06/2017.
 */
@Entity
@Table(name = "tblTransactionHistory")
public class TblTransactionHistory {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "transactionId")
    private long transactionId;

    @Column(name = "serviceType")
    private String serviceType;

    @Column(name = "sourceType")
    private String sourceType;

    @Column(name = "status")
    private String status;

    @Column(name = "createOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createOn;

    @Column(name = "inputAmount")
    private long inputAmount;

    @Column(name = "amount")
    private long amount;

    @Column(name = "fee")
    private long fee;

    @Column(name = "promotion")
    private long promotion;

    @Column(name = "totalAmount")
    private long totalAmount;

    @Column(name = "currency")
    private String currency = Constants.LEAGUE_CURRENCY;

    @Column(name = "dataRequest")
    private String dataRequest;

    @Column(name = "dataResponse")
    private String dataResponse;

    @Column(name = "balanceBefore")
    private long balanceBefore;

    @Column(name = "balanceAfter")
    private long balanceAfter;

    @Column(name = "description")
    private String description;

    @Transient
    private int userId;

    @Transient
    private long scoinUserId;

    @ManyToOne
    @JoinColumn(name = "senderId")
    private UserInfo sender;

    @ManyToOne
    @JoinColumn(name = "receiverId")
    private UserInfo receiver;

    public TblTransactionHistory() {
        this.inputAmount = 0;
        this.amount = 0;
        this.fee = 0;
        this.promotion = 0;
        this.totalAmount = 0;
        this.balanceBefore = 0;
        this.balanceAfter = 0;
        this.transactionId = 0;
    }

    public TblTransactionHistory(String serviceType, String sourceType, Date createOn, long inputAmount, long amount, long fee, long promotion, long totalAmount, String currency, String dataRequest, String dataResponse, String description, UserInfo sender, UserInfo receiver) {
        this.serviceType = serviceType;
        this.sourceType = sourceType;
        this.createOn = createOn;
        this.inputAmount = inputAmount;
        this.amount = amount;
        this.fee = fee;
        this.promotion = promotion;
        this.totalAmount = totalAmount;
        this.currency = currency;
        this.dataRequest = dataRequest;
        this.dataResponse = dataResponse;
        this.description = description;
        this.sender = sender;
        this.receiver = receiver;
        this.status = "failed";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    public long getInputAmount() {
        return inputAmount;
    }

    public void setInputAmount(long inputAmount) {
        this.inputAmount = inputAmount;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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

    public long getBalanceBefore() {
        return balanceBefore;
    }

    public void setBalanceBefore(long balanceBefore) {
        this.balanceBefore = balanceBefore;
    }

    public long getBalanceAfter() {
        return balanceAfter;
    }

    public void setBalanceAfter(long balanceAfter) {
        this.balanceAfter = balanceAfter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getScoinUserId() {
        return scoinUserId;
    }

    public void setScoinUserId(long scoinUserId) {
        this.scoinUserId = scoinUserId;
    }

    public UserInfo getSender() {
        return sender;
    }

    public void setSender(UserInfo sender) {
        this.sender = sender;
    }

    public UserInfo getReceiver() {
        return receiver;
    }

    public void setReceiver(UserInfo receiver) {
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return "TblTransactionHistory{" +
                "id=" + id +
                ", transactionId=" + transactionId +
                ", serviceType='" + serviceType + '\'' +
                ", sourceType='" + sourceType + '\'' +
                ", status='" + status + '\'' +
                ", createOn=" + createOn +
                ", inputAmount=" + inputAmount +
                ", amount=" + amount +
                ", fee=" + fee +
                ", promotion=" + promotion +
                ", totalAmount=" + totalAmount +
                ", currency='" + currency + '\'' +
                ", dataRequest='" + dataRequest + '\'' +
                ", dataResponse='" + dataResponse + '\'' +
                ", balanceBefore=" + balanceBefore +
                ", balanceAfter=" + balanceAfter +
                ", description='" + description + '\'' +
                ", userId=" + userId +
                '}';
    }
}
