package com.vtc.gamerid.gateway.common.dao.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 07/03/2017.
 */
@Entity
@Table(name = "tblUserVTC")
@Setter
@Getter
@NoArgsConstructor
public class TblUserVTC {
    @Id
    @GeneratedValue
    private Long        id;

    private String      username;

    private String      status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date        createOn;

    @Temporal(TemporalType.TIMESTAMP)
    private Date        updateOn;

    private Long        scoinId;

    private int         scoin      = 0;

    
    @OneToOne()
    @JsonBackReference
    @JoinColumn(name = "userInfo")
    private UserInfo userInfo;

    @Temporal(TemporalType.TIMESTAMP)
    private Date        lastCheckInbox;

    @Transient
    private boolean     firstLogin = false;

    public TblUserVTC(long scoinId, String username, String status, Date createOn, UserInfo userInfo) {
        this.username = username;
        this.status = status;
        this.createOn = createOn;
        this.userInfo = userInfo;
        this.scoinId = scoinId;
        this.scoin = 0;
    }

}
