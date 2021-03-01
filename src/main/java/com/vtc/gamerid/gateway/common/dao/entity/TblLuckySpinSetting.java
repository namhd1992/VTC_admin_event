package com.vtc.gamerid.gateway.common.dao.entity;

import javax.persistence.*;

import com.vtc.gamerid.gateway.common.Constants;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by phucnguyen on 04/07/2017.
 */
@Entity
@Table(name = "tblLuckySpinSetting")
@Setter
@Getter
@NoArgsConstructor
public class TblLuckySpinSetting {
    
    @Id
    @GeneratedValue
    private Long         id;

    private String       name;

    private String       keyName;

    private String       type;

    private int          intValue;

    private double       doubleValue;

    private String       stringValue;

    @ManyToOne
    @JoinColumn(name = "luckySpin")
    private LuckySpin luckySpin;

    private String       status = Constants.STATUS_ACTIVE;

    @Temporal(TemporalType.TIMESTAMP)
    private Date         lastUpdateOn;

    @ManyToOne
    @JoinColumn(name = "lastUpdateBy")
    private UserInfo  lastUpdateBy;

}
