/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dao.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Jul 16, 2019
 */
@Entity
@Table(name = "tblTopupCardHistory")
@Setter
@Getter
@NoArgsConstructor
public class TblTopupCardHistory {
  
    @Id
    @GeneratedValue
    private Long        id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date        createOn;

    private Long        scoinId;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userInfo")
    private UserInfo userInfo;

    @Temporal(TemporalType.TIMESTAMP)
    private Date     paymentTime;

    private String   paymentType;

    private long     totalPayment;

    private long     billingTransId;
    
    private long     serviceId;

    private boolean  luckyWheelUsed;

}
