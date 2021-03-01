package com.vtc.gamerid.gateway.common.dao.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vtc.gamerid.gateway.common.Constants;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 07/03/2017.
 */
@Entity
@Table(name = "tblUserInfo")
@Setter
@Getter
@NoArgsConstructor
public class UserInfo {

    @Id
    @GeneratedValue
    private Long           id;

    @CreationTimestamp
    private Date           createOn;

    @UpdateTimestamp
    private Date           updateOn;

    private String         firstName;

    private String         lastName;

    private String         fullName;

    private String         email;

    private String         phoneNumber;

    private String         address;

    @Temporal(TemporalType.TIMESTAMP)
    private Date           dateOfBirth;

    private String         urlAvatar;

    private String         gender;

    private String         status = Constants.STATUS_ACTIVE;

    private String         otpKey;

    private String         notifiKey;

    private String         deviceType;

    private String         deviceId;

    @Transient
    private String         username;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "groupRole")
    private TblGroupRole   groupRole;
    
//    @OneToOne(mappedBy = "userInfo", fetch = FetchType.LAZY)
//    @JsonBackReference
//    private UserGameRID userGameRID;
    
    @OneToOne(mappedBy = "userInfo")
    @JsonBackReference
    private TblUserSSO     userSSO;
    
    @OneToOne(mappedBy = "userInfo")
    @JsonBackReference
    private TblUserVTC     userVTC;

    private int            vipLevel;

    private String         ref;

    private String         facebookIdMerge;

    public UserInfo(String firstName, String lastName, String email, String phoneNumber, String address, Date dateOfBirth, String urlAvatar, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.urlAvatar = urlAvatar;
        this.gender = gender;
    }

    public void cloneObject(UserInfo tblUserInfo){
        this.firstName = tblUserInfo.getFirstName();
        this.lastName = tblUserInfo.getLastName();
        this.email = tblUserInfo.getEmail();
        this.phoneNumber = tblUserInfo.getPhoneNumber();
        this.address = tblUserInfo.getAddress();
        this.dateOfBirth = tblUserInfo.getDateOfBirth();
        this.urlAvatar = tblUserInfo.getUrlAvatar();
        this.gender = tblUserInfo.getGender();
        this.status = tblUserInfo.getStatus()==null? Constants.STATUS_ACTIVE:tblUserInfo.getStatus();
        this.userSSO = tblUserInfo.getUserSSO();
        this.userVTC = tblUserInfo.getUserVTC();
    }
    
}
