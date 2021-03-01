package com.vtc.gamerid.gateway.common.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * The persistent class for the game_item_of_game_event database table.
 * 
 */
@Entity
@Table(name="game_item_of_game_event")
@NamedQuery(name="GameItemOfGameEvent.findAll", query="SELECT g FROM GameItemOfGameEvent g")
@Getter
@Setter
@NoArgsConstructor
public class GameItemOfGameEvent implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private int               id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date         createOn;

    @Temporal(TemporalType.TIMESTAMP)
    private Date         updateOn;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)  
    @JoinColumn(name = "gameEvent")
    private GameEvent         gameEvent;

    private String            iconURL;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gameItem")
    private GameItem          gameItem;

    private String            itemName;

    private int               position;

}