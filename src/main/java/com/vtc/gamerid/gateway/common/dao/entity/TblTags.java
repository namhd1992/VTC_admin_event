package com.vtc.gamerid.gateway.common.dao.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 20/03/2017.
 */
@Entity
@Table(name = "tblTags")
@Setter
@Getter
@NoArgsConstructor
public class TblTags {
    @Id
    @GeneratedValue
    private int    id;

    private String keyName;

    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private Date   createOn;

    private String status;

    private String description;

    private String packageAndroid;

    private String packageIOS;

    private String urlDownloadAndroid;

    private String urlDownloadiOS;

    private String tagType;

    public TblTags(String keyName, String name, Date createOn, String status, String description) {
        this.keyName = keyName;
        this.name = name;
        this.createOn = createOn;
        this.status = status;
        this.description = description;
    }

    public TblTags(String keyName, String name, Date createOn, String status, String description, String packageAndroid, String packageIOS, String tagType) {
        this.keyName = keyName;
        this.name = name;
        this.createOn = createOn;
        this.status = status;
        this.description = description;
        this.packageAndroid = packageAndroid;
        this.packageIOS = packageIOS;
        this.tagType = tagType;
    }

}
