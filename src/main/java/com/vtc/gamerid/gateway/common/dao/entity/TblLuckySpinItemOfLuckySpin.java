package com.vtc.gamerid.gateway.common.dao.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 23/06/2017.
 */
@Entity
@Table(name = "tblLuckySpinItemOfLuckySpin")
@Setter
@Getter
@NoArgsConstructor
public class TblLuckySpinItemOfLuckySpin {
    
    @Id
    @GeneratedValue
    private Long             id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "luckySpin")
    private LuckySpin  luckySpin;

    @ManyToOne
    @JoinColumn(name = "item")
    private TblLuckySpinItem item;

    private int           totalQuantity;
    
    private int           receivedQuantity;

    private int           ratioIndex;

    private double        ratio;

    public TblLuckySpinItemOfLuckySpin(LuckySpin luckySpin, TblLuckySpinItem item, int totalQuantity, int ratioIndex) {
        this.luckySpin = luckySpin;
        this.item = item;
        this.totalQuantity = totalQuantity;
        this.ratioIndex = ratioIndex;
    }

}
