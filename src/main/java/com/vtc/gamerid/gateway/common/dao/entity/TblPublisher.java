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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 17/03/2017.
 */
@Entity
@Table(name = "tblPublisher")
@Setter
@Getter
@NoArgsConstructor
public class TblPublisher implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = -9152687770545691463L;

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String baseUrl;

    private String tags;

    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createOn;

    private String keyName;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "tblPublisher_Tag", joinColumns = @JoinColumn(name = "publisherId"), inverseJoinColumns = @JoinColumn(name = "tagsId"))
    private List<TblTags> tagsList;

    public TblPublisher(String name, String baseUrl, String tags, String status, Date createOn) {
        this.name = name;
        this.baseUrl = baseUrl;
        this.tags = tags;
        this.status = status;
        this.createOn = createOn;
    }

}
