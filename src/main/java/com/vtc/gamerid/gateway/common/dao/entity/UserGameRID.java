package com.vtc.gamerid.gateway.common.dao.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tblUserGameRID")
@Getter
@Setter
@NoArgsConstructor
public class UserGameRID {
    
    @Id
    @GeneratedValue
    private Long     id;

    @CreationTimestamp
    private Date     createOn;

    @UpdateTimestamp
    private Date     updateOn;

    private String   username;

    private String   password;

    private String   status;
    
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "groupRole")
    private TblGroupRole   groupRole;

//    @OneToOne
//    @JsonBackReference
//    @JoinColumn(name = "userInfo")
//    private UserInfo userInfo;

    public UserGameRID(String username, String password, Date createOn, String status) {
        this.username = username;
        this.password = password;
        this.createOn = createOn;
        this.status = status;
    }

}
