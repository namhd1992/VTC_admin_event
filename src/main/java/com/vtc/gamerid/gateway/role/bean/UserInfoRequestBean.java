package com.vtc.gamerid.gateway.role.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 25/04/2017.
 */
@Setter
@Getter
@NoArgsConstructor
public class UserInfoRequestBean implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -8746930277217509224L;

    private Long   id;

    private String firstName;

    private String lastName;

    private String status;

    private String phoneNumber;

    private String address;

    private Long   groupRoleId;

}
