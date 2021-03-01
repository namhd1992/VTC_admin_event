/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dao.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
@Table(name = "game_event")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GameEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long                      id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date                      createOn;

    @Temporal(TemporalType.TIMESTAMP)
    private Date                      updateOn;

    private String                    name;

    private String                    eventType;

    private String                    playTutorial;

    private Long                      serviceId;

    private String                    urlBaseEvent;

    private long                      oncePoint;

    private String                    giftEvent;

    private float                     ratioGift;

    private long                      limitGift;

    private long                      gaveGift;

    private String                    packageGift;

    private Date                      fromDate;

    private Date                      toDate;

    private boolean                   status;

    @OneToMany(mappedBy = "gameEvent",  fetch = FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<GameItemOfGameEvent> itemEvents;

}
