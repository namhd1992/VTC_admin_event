package com.vtc.gamerid.gateway.common.dao.entity;

import java.util.Date;

import javax.persistence.Column;
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
 * Created by phucnguyen on 18/04/2018.
 */
@Entity
@Table(name = "tblUserPointGame")
@Setter
@Getter
@NoArgsConstructor
public class TblUserPointGame {
    @Id
    @GeneratedValue
    private Long  id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date  createOn;

    private Long  userId;

    private int   gameId;

    @Column(name = "point")
    private float point;

}
