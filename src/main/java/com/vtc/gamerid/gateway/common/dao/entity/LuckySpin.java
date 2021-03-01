package com.vtc.gamerid.gateway.common.dao.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 23/06/2017.
 */
@Entity
@Table(name = "tblLuckySpin")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LuckySpin {

    @Id
    @GeneratedValue
    private Long                           id;

    @CreationTimestamp
    private Date                           createOn;

    @UpdateTimestamp
    private Date                           updateOn;

    private String                         type;

    private String                         name;

    private String                         image;

    @Temporal(TemporalType.TIMESTAMP)
    private Date                           startDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date                           endDate;

    private String                         description;

    private String                         linkLiveStream;

    private int                            freeSpinPerDay;

    private int                            freeSpinOnStart;

    private String                         buyTurnType;

    private int                            pricePerTurn;

    private String                         status;

    private int                            spinTimes;

    @Temporal(TemporalType.TIMESTAMP)
    private Date                           goldTimeStart;

    @Temporal(TemporalType.TIMESTAMP)
    private Date                           goldTimeEnd;
    
    private int                            goldTimeItemIndex;

    private boolean                        goldTimeStatus;

    private Long                           createBy;

    @OneToMany(mappedBy = "luckySpin", fetch = FetchType.EAGER)
    private List<TblLuckySpinItemOfLuckySpin> spinItems   = new ArrayList<>();

}
