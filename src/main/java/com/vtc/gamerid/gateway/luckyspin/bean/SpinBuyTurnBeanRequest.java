package com.vtc.gamerid.gateway.luckyspin.bean;

import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.dto.response.ValidateBean;
import com.vtc.gamerid.gateway.exception.RegularExpression;

import java.io.Serializable;

/**
 * Created by phucnguyen on 26/06/2017.
 */
public class SpinBuyTurnBeanRequest implements Serializable{
    private int spinId = -1;
    private int numberTurns = -1;
    private String spinName = null;
    private String scoinToken = null;

    public ValidateBean validate(){
        if(this.numberTurns < 1 || !RegularExpression.validateStripXss(this.scoinToken))
            return new ValidateBean(false, Constants.BAD_REQ);
        return new ValidateBean(true, Constants.SUCCESS);
    }
    public SpinBuyTurnBeanRequest() {
    }

    public SpinBuyTurnBeanRequest(int spinId, int numberTurns, String spinName) {
        this.spinId = spinId;
        this.numberTurns = numberTurns;
        this.spinName = spinName;
    }

    public String getScoinToken() {
        return scoinToken;
    }

    public void setScoinToken(String scoinToken) {
        this.scoinToken = scoinToken;
    }

    public String getSpinName() {
        return spinName;
    }

    public void setSpinName(String spinName) {
        this.spinName = spinName;
    }

    public int getSpinId() {
        return spinId;
    }

    public void setSpinId(int spinId) {
        this.spinId = spinId;
    }

    public int getNumberTurns() {
        return numberTurns;
    }

    public void setNumberTurns(int numberTurns) {
        this.numberTurns = numberTurns;
    }
}
