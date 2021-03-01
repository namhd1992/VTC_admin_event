package com.vtc.gamerid.gateway.common.dao.entity;

import javax.persistence.*;

/**
 * Created by phucnguyen on 04/12/2017.
 */
@Entity
@Table(name = "tblCheckinItem")
public class TblCheckinItem {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "day")
    private int day;

    @Column(name = "awardPoint")
    private int awardPoint;

    @Column(name = "pointBonus")
    private int pointBonus;

    @Column(name = "awardName")
    private String awardName;

    public TblCheckinItem() {
    }

    public TblCheckinItem(int day, int awardPoint, int pointBonus, String awardName) {
        this.day = day;
        this.awardPoint = awardPoint;
        this.pointBonus = pointBonus;
        this.awardName = awardName;
    }

    public int getPointBonus() {
        return pointBonus;
    }

    public void setPointBonus(int pointBonus) {
        this.pointBonus = pointBonus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getAwardPoint() {
        return awardPoint;
    }

    public void setAwardPoint(int awardPoint) {
        this.awardPoint = awardPoint;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }
}
