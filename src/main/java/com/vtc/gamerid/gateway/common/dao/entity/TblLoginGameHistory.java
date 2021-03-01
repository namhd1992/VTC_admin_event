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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Nov 6, 2018
 */
@Entity
@Table(name = "tblLoginGameHistory")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TblLoginGameHistory {
    
    @Id
    @GeneratedValue
    private Long        id;

//    @Temporal(TemporalType.TIMESTAMP)
//    private Date        createOn;

    @ManyToOne
    @JoinColumn(name = "userInfo")
    private UserInfo userInfo;

    private Long        scoinGameId;

    private Date        firstLogin;

    private Date        lastLogin;

}
