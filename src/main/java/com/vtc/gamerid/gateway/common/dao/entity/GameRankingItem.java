package com.vtc.gamerid.gateway.common.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * The persistent class for the game_ranking_item database table.
 * 
 */
@Entity
@Table(name="game_ranking_item")
@NamedQuery(name="GameRankingItem.findAll", query="SELECT g FROM GameRankingItem g")
@Getter
@Setter
@NoArgsConstructor
public class GameRankingItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long               id;

    @Column(name = "create_on")
    @CreationTimestamp
    private Date              createOn;

    private String            description;

    @Column(name = "item_icon_url")
    private String            itemIconUrl;

    @Column(name = "item_name")
    private String            itemName;

    @Column(name = "item_type")
    private String            itemType;

    @Column(name = "item_quantity")
    private int               itemQuantity;
    
    @Column(name = "object_id")
    private Long              objectId;

    @Column(name = "item_value")
    private int               itemValue;

    @Column(name = "update_on")
    @UpdateTimestamp
    private Date              updateOn;

    @Column(name = "winning_tittle")
    private String            winningTittle;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "game_ranking")
    private GameRanking       gameRanking;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "rank")
    private GameRankingRank   gameRankingRank;

}