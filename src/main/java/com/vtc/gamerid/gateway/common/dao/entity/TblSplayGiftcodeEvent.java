package com.vtc.gamerid.gateway.common.dao.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 13/11/2017.
 */
@Entity
@Table(name = "tblSplayGiftcodeEvent")
@Setter
@Getter
@NoArgsConstructor
public class TblSplayGiftcodeEvent implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 4836557090501657264L;

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String giftcodeFile;

    private String defaultImage;

    private String urlDownloadAndroid;

    private String urlDownloadiOS;

    private String androidPackage;

    private String iosSchemes;

    private String status;

    private String description;

    private String urlShareFB;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate = new Date();

    @ManyToOne
    @JoinColumn(name="createBy")
    private UserInfo createBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createOn = new Date();

    @ManyToOne
    @JoinColumn(name="lastUpdateBy")
    private UserInfo lastUpdateBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateOn = new Date();

    private int numberGiftcode;

    private int numberGiftcodeLost;

    private int price;

    private int vipLevel;

    private int numberLike;

    private int numberShare;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "tblSplayGECondition",
            joinColumns = @JoinColumn(name = "sGiftcodeEvent"),
            inverseJoinColumns = @JoinColumn(name = "giftcodeCondition"))
    private List<TblGiftcodeCondition> giftcodeCondition;

    public TblSplayGiftcodeEvent(String name, String giftcodeFile, String defaultImage, String urlDownloadAndroid, String urlDownloadiOS, String androidPackage, String iosSchemes, String status, String description, String urlShareFB, Date startDate, Date endDate, UserInfo createBy, Date createOn, UserInfo lastUpdateBy, Date lastUpdateOn, int numberGiftcode, int numberGiftcodeLost, int price, int vipLevel, List<TblGiftcodeCondition> giftcodeCondition) {
        this.name = name;
        this.giftcodeFile = giftcodeFile;
        this.defaultImage = defaultImage;
        this.urlDownloadAndroid = urlDownloadAndroid;
        this.urlDownloadiOS = urlDownloadiOS;
        this.androidPackage = androidPackage;
        this.iosSchemes = iosSchemes;
        this.status = status;
        this.description = description;
        this.urlShareFB = urlShareFB;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createBy = createBy;
        this.createOn = createOn;
        this.lastUpdateBy = lastUpdateBy;
        this.lastUpdateOn = lastUpdateOn;
        this.numberGiftcode = numberGiftcode;
        this.numberGiftcodeLost = numberGiftcodeLost;
        this.price = price;
        this.vipLevel = vipLevel;
        this.giftcodeCondition = giftcodeCondition;
        this.numberLike = 0;
        this.numberShare = 0;
    }

}
