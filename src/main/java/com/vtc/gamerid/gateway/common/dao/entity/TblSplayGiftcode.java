package com.vtc.gamerid.gateway.common.dao.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by phucnguyen on 13/11/2017.
 */
@Entity
@Table(name = "tblSplayGiftcode")
@Setter
@Getter
@NoArgsConstructor
public class TblSplayGiftcode implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "splayGiftcodeEvent")
    private TblSplayGiftcodeEvent splayGiftcodeEvent;

    @Column(name = "giftcode")
    private String giftcode;

    @ManyToOne
    @JoinColumn(name="userLost")
    private UserInfo userLost;

    @Column(name = "deviceID")
    private String deviceID;

    @Column(name = "createOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createOn = new Date();

    public TblSplayGiftcode(TblSplayGiftcodeEvent splayGiftcodeEvent, String giftcode) {
        this.splayGiftcodeEvent = splayGiftcodeEvent;
        this.giftcode = giftcode;
    }

    public TblSplayGiftcode(TblSplayGiftcodeEvent splayGiftcodeEvent, String giftcode, UserInfo userLost, String deviceID, Date createOn) {
        this.splayGiftcodeEvent = splayGiftcodeEvent;
        this.giftcode = giftcode;
        this.userLost = userLost;
        this.deviceID = deviceID;
        this.createOn = createOn;
    }

}
