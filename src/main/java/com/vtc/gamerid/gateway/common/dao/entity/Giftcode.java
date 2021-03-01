package com.vtc.gamerid.gateway.common.dao.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


/**
 * The persistent class for the giftcode database table.
 * 
 */
@Entity
@Table(name="giftcode")
@NamedQuery(name="Giftcode.findAll", query="SELECT g FROM Giftcode g")
@Setter
@Getter
@NoArgsConstructor
public class Giftcode implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long              id;

    @CreationTimestamp
    private Date              createOn;

    private String            mainCode;

    private String            subCode;

    private String            eventType;

    private Long              mainEventId;

    private Long              subEventId;

    @OneToOne()
    @JsonBackReference
    @JoinColumn(name = "userInfo")
    private UserInfo          userInfo;

}