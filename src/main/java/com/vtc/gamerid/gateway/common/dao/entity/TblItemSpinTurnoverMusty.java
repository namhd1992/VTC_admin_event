/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dao.entity;

import java.util.Date;

import javax.persistence.Entity;
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
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Aug 1, 2019
 */
@Entity
@Table(name="tblItemSpinTurnoverMusty")
@Setter
@Getter
@NoArgsConstructor
public class TblItemSpinTurnoverMusty {

    @Id
    private Long          id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date          createOn;

    @Temporal(TemporalType.TIMESTAMP)
    private Date          updateOn;

    @ManyToOne
    @JoinColumn(name = "item")
    private TblLuckySpinItem item;

    private int           itemQuantiry;

    @ManyToOne
    @JoinColumn(name = "luckySpin")
    private LuckySpin  luckySpin;

    private long          turnoverMusty;

}
