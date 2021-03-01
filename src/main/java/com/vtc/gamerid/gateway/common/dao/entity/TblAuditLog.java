package com.vtc.gamerid.gateway.common.dao.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 03/03/2017.
 */
@Entity
@Table(name = "tblAuditLog")
@Setter
@Getter
@NoArgsConstructor
public class TblAuditLog {
    @Id
    @GeneratedValue
    private long        id;

    private String      functionName;

    private String      status;

    private String      dataRequest;

    @Temporal(TemporalType.TIMESTAMP)
    private Date        createOn;

    private Long        timeHandle;

    private String      dataResponse;

    private String      ipRequest;

    private String      deviceId;

    private String      language;

    private String      deviceType;

    private String      appVersion;

    private String      onlyMessage;

    @ManyToOne
    @JoinColumn(name = "userInfo")
    private UserInfo userInfo;

    public TblAuditLog(String functionName, String status, String dataRequest, Date createOn, Long timeHandle, String dataResponse, String ipRequest, String deviceId, String language, String deviceType, String appVersion, UserInfo userInfo) {
        this.functionName = functionName;
        this.status = status;
        this.dataRequest = dataRequest;
        this.createOn = createOn;
        this.timeHandle = timeHandle;
        this.dataResponse = dataResponse;
        this.ipRequest = ipRequest;
        this.deviceId = deviceId;
        this.language = language;
        this.deviceType = deviceType;
        this.appVersion = appVersion;
        this.userInfo = userInfo;
    }

    public TblAuditLog(String functionName, String status, String dataRequest, Date createOn, Long timeHandle, String dataResponse, String ipRequest) {
        this.functionName = functionName;
        this.status = status;
        this.dataRequest = dataRequest;
        this.createOn = createOn;
        this.timeHandle = timeHandle;
        this.dataResponse = dataResponse;
        this.ipRequest = ipRequest;
    }

    public TblAuditLog(String functionName, String status, String dataRequest, Date createOn, Long timeHandle, String dataResponse, String ipRequest, String deviceId, String language, String deviceType, String appVersion) {
        this.functionName = functionName;
        this.status = status;
        this.dataRequest = dataRequest;
        this.createOn = createOn;
        this.timeHandle = timeHandle;
        this.dataResponse = dataResponse;
        this.ipRequest = ipRequest;
        this.deviceId = deviceId;
        this.language = language;
        this.deviceType = deviceType;
        this.appVersion = appVersion;
    }

}
