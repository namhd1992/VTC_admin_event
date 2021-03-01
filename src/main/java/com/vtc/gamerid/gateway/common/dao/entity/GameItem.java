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
 * The persistent class for the game_item database table.
 * 
 */
@Entity
@Table(name="game_item")
@NamedQuery(name="GameItem.findAll", query="SELECT g FROM GameItem g")
@Getter
@Setter
@NoArgsConstructor
public class GameItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long              id;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date              createOn;

    @Temporal(TemporalType.TIMESTAMP)
    private Date              updateOn;

    private String            itemName;

    private String            itemType;
    
    private Long              objectId;

    private String            winningMessage;

    private String            itemImageURL;

}