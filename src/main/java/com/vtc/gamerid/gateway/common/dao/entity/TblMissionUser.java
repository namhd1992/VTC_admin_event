package com.vtc.gamerid.gateway.common.dao.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by phucnguyen on 20/11/2017.
 */
@Entity
@Table(name = "tblMissionUser")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TblMissionUser implements Serializable {
    private static final long serialVersionUID = 1081322660951162886L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "mission")
    private TblMission mission;

    @ManyToOne
    @JoinColumn(name = "userInfo")
    private UserInfo userInfo;

    @Column(name = "createOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createOn;

    @Column(name = "action")
    private String action;

    @Column(name = "description")
    private String description;

    @Column(name = "isReceived")
    private boolean isReceived;

    public TblMissionUser(TblMission mission, UserInfo userInfo, Date createOn, String action, String description) {
        this.mission = mission;
        this.userInfo = userInfo;
        this.createOn = createOn;
        this.action = action;
        this.description = description;
    }

}
