package com.vtc.gamerid.gateway.luckyspin.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
by phucnguyen on 26/06/2017.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSpinInfoBeanResponse implements Serializable{
    private static final long serialVersionUID = 1L;
    private int userId;
    private long spinId;
    private int turnsBuy;
    private int turnsFree;
    private int balanceLP;
    private String currName;
    private long rewardPoint;

    public UserSpinInfoBeanResponse(int userId, long spinId, int turnsBuy, int turnsFree,int balanceLP) {
        this.userId = userId;
        this.spinId = spinId;
        this.turnsBuy = turnsBuy;
        this.turnsFree = turnsFree;
        this.balanceLP = balanceLP;
    }

    public UserSpinInfoBeanResponse(int userId, long spinId, int turnsBuy, int turnsFree, int balanceLP, String currName) {
        this.userId = userId;
        this.spinId = spinId;
        this.turnsBuy = turnsBuy;
        this.turnsFree = turnsFree;
        this.balanceLP = balanceLP;
        this.currName = currName;
    }

}
