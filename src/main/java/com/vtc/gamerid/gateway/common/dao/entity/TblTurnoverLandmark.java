package com.vtc.gamerid.gateway.common.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Jul 15, 2019
 */
@Entity
@Table(name = "tblTurnoverLandmark")
@NamedQuery(name = "TblTurnoverLandmark.findAll", query = "SELECT t FROM TblTurnoverLandmark t")
@Setter
@Getter
@NoArgsConstructor
public class TblTurnoverLandmark implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long              id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date              createOn;

    @Temporal(TemporalType.TIMESTAMP)
    private Date              UpdateOn;

    private String            game;

    private Long              gameId;

    private Long              itemId;

    private int               limitQuantity;

    private String            limitType;

    private long              turnoverLandmark;

}