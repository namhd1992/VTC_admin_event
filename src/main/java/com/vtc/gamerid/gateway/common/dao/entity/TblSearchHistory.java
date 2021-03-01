package com.vtc.gamerid.gateway.common.dao.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by phucnguyen on 28/04/2017.
 */
@Entity
@Table(name = "tblSearchHistory")
public class TblSearchHistory {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "searchKey")
    private String searchKey;

    @Column(name = "keyCompare")
    private String keyCompare;

    @Column(name = "numberSearch")
    private int numberSearch = 1;

    @Column(name = "createOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createOn;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "tblUser_Search", joinColumns = @JoinColumn(name = "searchId"), inverseJoinColumns = @JoinColumn(name = "userId"))
    private List<UserInfo> userInfos = new ArrayList<>();

    public TblSearchHistory() {
    }

    public TblSearchHistory(String searchKey, String keyCompare, int numberSearch) {
        this.searchKey = searchKey;
        this.keyCompare = keyCompare;
        this.numberSearch = numberSearch;
        this.userInfos = new ArrayList<>();
    }

    public List<UserInfo> getUserInfos() {
        return userInfos;
    }

    public void setUserInfos(List<UserInfo> userInfos) {
        this.userInfos = userInfos;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getKeyCompare() {
        return keyCompare;
    }

    public void setKeyCompare(String keyCompare) {
        this.keyCompare = keyCompare;
    }

    public int getNumberSearch() {
        return numberSearch;
    }

    public void setNumberSearch(int numberSearch) {
        this.numberSearch = numberSearch;
    }
}
