/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Nov 22, 2018
 */
@Entity
@Table(name = "tblExchangeHistory")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TblExchangeHistory {

    @Id
    @GeneratedValue
    private Long        id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date        createOn;

    private Long        exchangeId;

    @ManyToOne
    @JoinColumn(name = "userInfo")
    private UserInfo userInfo;
    
    private Long        scoinId;

    private String      provider;

    private Long        serviceId;

    private Long        providerId;

    private Long        amount;

    private String      exchangeType;

    private double      exchangeRatio;

    private Long        beforeBalance;

    private Long        currentBalance;

    private String      status;

    private String      description;

}
