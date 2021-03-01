package com.vtc.gamerid.gateway.common.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 20/11/2017.
 */
@Entity
@Table(name = "tblMission")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TblMission implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long              id;

    private String            name;

    private String            description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date              createOn;

    @ManyToOne
    @JoinColumn(name = "createBy")
    private UserInfo       createBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date              updateOn;

    @ManyToOne
    @JoinColumn(name = "updateBy")
    private UserGameRID       updateBy;

    private String            status;

    private int               action;

    private Long              objectId;

    private String            linkToShare;

    private String            award;

    private long              valueAward;

    private long              spinAwardId;

    private Long              valueLimit;

    private String            typeLimit;

    @Temporal(TemporalType.TIMESTAMP)
    private Date              fromDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date              toDate;

    private boolean           isCyclic;

    private String            defaultImage;

    private boolean           highLights;

    private String            androidScheme;

    private String            iosScheme;

    private String            objectValue;

    public TblMission(String name, String description, Date createOn, UserInfo createBy, Date updateOn, UserGameRID updateBy, String status, int action, String award, long valueAward, Date fromDate, Date toDate, boolean isCyclic, String defaultImage, Long objectId) {
        this.name = name;
        this.description = description;
        this.createOn = createOn;
        this.createBy = createBy;
        this.updateOn = updateOn;
        this.updateBy = updateBy;
        this.status = status;
        this.action = action;
        this.award = award;
        this.valueAward = valueAward;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.isCyclic = isCyclic;
        this.defaultImage = defaultImage;
        this.objectId = objectId;
    }

}
