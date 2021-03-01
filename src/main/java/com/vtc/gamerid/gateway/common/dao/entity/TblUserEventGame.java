/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
 * Feb 14, 2019
 */
@Entity
@Table(name = "tblUserEventGame")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TblUserEventGame implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long              id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date              createOn;

    @OneToOne
    @JoinColumn(name = "userInfo")
    private UserInfo       userInfo;

    private String            facebookId;

    @ManyToOne
    @JoinColumn(name = "eventGame")
    private GameEvent      eventGame;

    private long              eventPoint;

    private Long              userParent;

    private boolean           finishMission;
    
    private boolean           usedLink;

}
