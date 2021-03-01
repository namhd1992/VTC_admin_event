package com.vtc.gamerid.gateway.scoin.authorization.bean;

import java.io.Serializable;

import com.vtc.gamerid.gateway.common.dao.entity.TblGroupRole;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 23/08/2017.
 */
@Setter
@Getter
@NoArgsConstructor
public class AdbLoginResponseData implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 6823933566551700138L;

    private String            fullName;

    private String            urlAvatar;

    private int               userInfoId;

    private TblGroupRole      groupRole;

    private int               id;

    private String            email;

    private String            username;

    public AdbLoginResponseData(String fullName, String urlAvatar, int userInfoId, TblGroupRole groupRole, int id, String email, String username) {
        this.fullName = fullName;
        this.urlAvatar = urlAvatar;
        this.userInfoId = userInfoId;
        this.groupRole = groupRole;
        this.id = id;
        this.email = email;
        this.username = username;
    }

}
