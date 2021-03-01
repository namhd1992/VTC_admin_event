package com.vtc.gamerid.gateway.common.dao.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Created by phucnguyen on 07/03/2017.
 */
@Entity
@Table(name = "tblUserSSO")
@Getter
@Setter
@NoArgsConstructor
public class TblUserSSO {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "sourceType")
    private String sourceType;

    @Column(name = "status")
    private String status;

    @Column(name = "createOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createOn;

    @Column(name = "ssoToken")
    private String ssoToken;

    @OneToOne
    @JoinColumn(name = "userInfo")
    private UserInfo userInfo;

    public TblUserSSO(String username, String sourceType, String status, Date createOn,
                      String ssoToken, UserInfo userInfo) {
        this.username = username;
        this.sourceType = sourceType;
        this.status = status;
        this.createOn = createOn;
        this.ssoToken = ssoToken;
        this.userInfo = userInfo;
    }

}
