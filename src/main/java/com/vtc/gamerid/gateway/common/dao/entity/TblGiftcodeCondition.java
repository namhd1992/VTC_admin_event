package com.vtc.gamerid.gateway.common.dao.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 21/04/2017.
 */
@Entity
@Table(name = "tblGiftcodeCondition")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TblGiftcodeCondition {
    @Id
    @GeneratedValue
    private int     id;

    private String  name;

    private String  status;

    private String  description;

    @Transient
    private boolean isLoginGame     = false;

    @Transient
    private boolean isShareFacebook = false;
    
    @Transient
    private boolean isValidatedPhone = false;

    public TblGiftcodeCondition(String name, String status, String description) {
        this.name = name;
        this.status = status;
        this.description = description;
    }

}
