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
 * Jan 3, 2019
 */

@Entity
@Table(name = "tblEventGiftHistory")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TblEventGiftHistory {

    @Id
    @GeneratedValue
    private Long         id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date         createOn;
    
    @ManyToOne
    @JoinColumn(name = "userinfo")
    private UserInfo userinfo;

    @ManyToOne
    @JoinColumn(name = "eventGame")
    private GameEvent eventGame;
    
    private long         scoinGameId;

    private String       game;

    private String       serverGame;

    private Long         scoinId;

    private long         itemGameId;

    private String       gift;

    private Long         giftValue;

    private Long         beforePoint;

    private Long         currentPoint;

    private String       status;

    private String       statusError;

    private String       messError;

}
