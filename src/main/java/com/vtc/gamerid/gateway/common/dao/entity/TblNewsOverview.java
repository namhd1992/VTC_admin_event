package com.vtc.gamerid.gateway.common.dao.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by phucnguyen on 17/03/2017.
 */
@Entity
@Table(name = "tblNewsOverview")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TblNewsOverview {
    
    @Id
    @GeneratedValue
    private int              id;

    private String           title;

    private String           defaultImage;

    private String           keyName;

    @ManyToOne
    @JoinColumn(name = "publisherId")
    private TblPublisher     publisher;

    @Temporal(TemporalType.TIMESTAMP)
    private Date             createOn;

    private String           status;

    private String           urlSource;

    private String           urlView;

    private String           newsType;

    private int              numberView        = 0;

    private int              numberLike        = 0;

    private int              numberShare       = 0;

    private int              numberComment     = 0;

    private float            score             = 0;

    private String           author            = "No name";

    @Transient
    private boolean          isLike            = false;

    @Transient
    private List<Integer>    channelsId        = new ArrayList<>();

    private String           content;

    private int              numberSameArticle = 0;

    private String           itemImage;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "tblTag_News", joinColumns = @JoinColumn(name = "newsId"), inverseJoinColumns = @JoinColumn(name = "tagId"))
    private List<TblTags>    tagsList;

    @OneToOne
    @JoinColumn(name = "giftcodeEvent")
    private TblGiftcodeEvent giftcodeEvent;

    public TblNewsOverview(String title, String defaultImage, String keyName, TblPublisher publisher, Date createOn, String status, String urlSource, String urlView, String newsType, int numberView) {
        this.title = title;
        this.defaultImage = defaultImage;
        this.keyName = keyName;
        this.publisher = publisher;
        this.createOn = createOn;
        this.status = status;
        this.urlSource = urlSource;
        this.urlView = urlView;
        this.newsType = newsType;
        this.numberView = numberView;
    }

    public TblNewsOverview(String title, String defaultImage, String keyName, TblPublisher publisher, Date createOn, String status, String urlSource, String urlView, String newsType, int numberView, int numberLike, float score) {
        this.title = title;
        this.defaultImage = defaultImage;
        this.keyName = keyName;
        this.publisher = publisher;
        this.createOn = createOn;
        this.status = status;
        this.urlSource = urlSource;
        this.urlView = urlView;
        this.newsType = newsType;
        this.numberView = numberView;
        this.numberLike = numberLike;
        this.score = score;
    }

}
