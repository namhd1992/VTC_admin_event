package com.vtc.gamerid.gateway.common.dao.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Created by phucnguyen on 15/05/2018.
 */
@Entity
@Table(name = "tblDistributeGiftcodeHistory")
@Setter
@Getter
@NoArgsConstructor
public class TblDistributeGiftcodeHistory {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    private String fromEmail;

    private String fromName;

    private String title;

    private String toEmail;

    private String errorMess;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createOn;

    private Long createBy;

    public TblDistributeGiftcodeHistory(String fromEmail, String fromName, String title, String toEmail, String errorMess, Date createOn, Long createBy) {
        this.fromEmail = fromEmail;
        this.fromName = fromName;
        this.title = title;
        this.toEmail = toEmail;
        this.errorMess = errorMess;
        this.createOn = createOn;
        this.createBy = createBy;
    }
    
}
