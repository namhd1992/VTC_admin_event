package com.vtc.gamerid.gateway.profile.bean;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 19/06/2017.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileBeanResponse implements Serializable {
    private static final long serialVersionUID = -5671358087328434598L;

    private int               userId;

    private String            fullName;

    private String            urlAvatar;

    private int               balance;

    private long              accountNumber;

    private Date              birthday;

    private String            phoneNumber;

    private int               scoinBalance;

    private long              balanceXU;

    private long              splayPoint;

    private int               vipLevel;

    private long              numberInboxUnread;

    private long              numberMissionUnFinish;

    private boolean           checkinToday;

    private String            email;

    private int               numberInboxShop  = 0;

}
