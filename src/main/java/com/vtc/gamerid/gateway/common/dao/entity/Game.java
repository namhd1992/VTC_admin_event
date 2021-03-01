package com.vtc.gamerid.gateway.common.dao.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 05/12/2017.
 */
@Entity
@Table(name = "game")
@Setter
@Getter
@NoArgsConstructor
public class Game {

    @Id
    @GeneratedValue
    private Long              id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date              createOn;

    @Temporal(TemporalType.TIMESTAMP)
    private Date              updateOn;

    private String            name;

    private String            keyName;

    private String            defaultImage;

    private String            bigImage;

    private String            fanpageFB;
    
    private String            groupFB;

    private String            publisher;

    private String            status;

    private String            description;

    private String            gameType;
    
    private String            webgameUrl;

    private long              scoinGameId;

    private String            subTitle;

    private long              downloadTurns;

    private String            urlDownloadAndroid;

    private String            urlDownloadIos;
    
    private String            urlDownloadPC;

    private String            screenShot;

    private float             pointReview;

    private int               position;

    private Long              createBy;

    private long              priorityTag;

    private String            youtubeChannelId;

    private String            youtubeDefaultSearch;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
                })
    @JoinTable(name = "tblSplayGame_Tag", joinColumns = @JoinColumn(name = "gameId"), inverseJoinColumns = @JoinColumn(name = "tagId"))
    private List<TblSplayTag> tagsList;

    @Transient
    private long              numberGiftcodeOfGame = 0;

    public Game(String name, String keyName, String defaultImage, String groupFB,
                        String fanpageFB, String publisher, String status, Date createOn) {
        this.name = name;
        this.keyName = keyName;
        this.defaultImage = defaultImage;
        this.groupFB = groupFB;
        this.fanpageFB = fanpageFB;
        this.publisher = publisher;
        this.status = status;
        this.createOn = createOn;
    }

}
