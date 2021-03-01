package com.vtc.gamerid.gateway.luckyspin.bean;

import java.io.Serializable;

/**
 * Created by phucnguyen on 26/06/2017.
 */
public class SpinBuyTurnBeanResponse implements Serializable {
    private int transactionId;
    private int numberTurns;
    private int spinId;

    public SpinBuyTurnBeanResponse() {
    }

    public SpinBuyTurnBeanResponse(int transactionId, int numberTurns, int spinId) {
        this.transactionId = transactionId;
        this.numberTurns = numberTurns;
        this.spinId = spinId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getNumberTurns() {
        return numberTurns;
    }

    public void setNumberTurns(int numberTurns) {
        this.numberTurns = numberTurns;
    }

    public int getSpinId() {
        return spinId;
    }

    public void setSpinId(int spinId) {
        this.spinId = spinId;
    }
}
