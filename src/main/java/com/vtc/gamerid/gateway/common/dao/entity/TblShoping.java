package com.vtc.gamerid.gateway.common.dao.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Created by phucnguyen on 19/04/2018.
 */
@Entity
@Table(name = "tblShoping")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TblShoping {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long   id;

    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private Date   createOn;

    private String defaultImage;

    private String bigImage;

    private long   gameId;

    private Long   createBy;

    private String status;

    private int    shopType;

    private String redirectUrl;

    public TblShoping(String name, Date createOn, String defaultImage, String bigImage, int gameId,
                      Long createBy, String status, int shopType, String redirectUrl) {
        this.name = name;
        this.createOn = createOn;
        this.defaultImage = defaultImage;
        this.bigImage = bigImage;
        this.gameId = gameId;
        this.createBy = createBy;
        this.status = status;
        this.shopType = shopType;
        this.redirectUrl = redirectUrl;
    }

}
