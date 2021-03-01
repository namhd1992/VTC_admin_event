package com.vtc.gamerid.gateway.common.dao.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 24/04/2017.
 */
@Entity
@Table(name = "tblGiftcodeEvent")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TblGiftcodeEvent {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "giftCodeFile")
    private String giftCodeFile;

    @Column(name = "urlDownloadAndroid")
    private String urlDownloadAndroid;

    @Column(name = "androidPackage")
    private String androidPackage;

    @Column(name = "urlDownloadiOS")
    private String urlDownloadiOS;

    @Column(name = "iosSchemes")
    private String iosSchemes;

    @Column(name = "urlShareFB")
    private String urlShareFB;

    @Column(name = "numberGiftcode")
    private int numberGiftcode;

    @Column(name = "numberGiftcodeLost")
    private int numberGiftcodeLost = 0;

    @Column(name = "dateStartEvent")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateStartEvent;

    @Column(name = "dateEndEvent")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEndEvent;

    @Transient
    private String defaultImage;

    @Transient
    private String title;

    @Column(name = "pushNotification")
    private String pushNotification;

    @Column(name = "gameDownloadName")
    private String gameDownloadName;

    @Column(name = "messageNotifi")
    private String messageNotifi;

    @Column(name = "numberInstall")
    private int numberInstall;

    @Column(name = "scoinGameId")
    private long scoinGameId;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "tblGiftcodeEvent_Condition",
            joinColumns = @JoinColumn(name = "giftcodeEvent"),
            inverseJoinColumns = @JoinColumn(name = "giftcodeCondition"))
    private List<TblGiftcodeCondition> giftcodeCondition;

    @ManyToOne
    @JoinColumn(name = "createBy")
    private UserInfo createBy;

    @ManyToOne
    @JoinColumn(name = "inGame")
    private Game inGame;

    @Column(name = "price")
    private int price;

    @Column(name = "vipLevel")
    private int vipLevel;

    @Column(name = "showing")
    private int showing;

    @Transient
    private String giftcodeLost;

    @Transient
    private Game scoinGameObject;

    public TblGiftcodeEvent(String giftCodeFile, String urlDownloadAndroid, String urlDownloadiOS,
                            Date dateStartEvent, Date dateEndEvent,
                            List<TblGiftcodeCondition> giftcodeCondition, UserInfo createBy) {
        this.giftCodeFile = giftCodeFile;
        this.urlDownloadAndroid = urlDownloadAndroid;
        this.urlDownloadiOS = urlDownloadiOS;
        this.dateStartEvent = dateStartEvent;
        this.dateEndEvent = dateEndEvent;
        this.giftcodeCondition = giftcodeCondition;
        this.createBy = createBy;
    }

}
