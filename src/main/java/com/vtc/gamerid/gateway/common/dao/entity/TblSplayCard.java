package com.vtc.gamerid.gateway.common.dao.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Created by phucnguyen on 17/07/2018.
 */
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tblSplayCard")
public class TblSplayCard {
    @Id
    @GeneratedValue
    private long        id;

    private Long        accountScoinId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date        lastUpdateOn;

    private String      cardSerial;

    private String      cardCode;

    private Integer     price;

    private Long        missionId;

    private Integer     scoinGameId;

    private String      expirationDate;

    @ManyToOne
    @JoinColumn(name = "createBy")
    private UserInfo createBy;

    @Column(name = "useFlag", nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean     useFlag;

}
