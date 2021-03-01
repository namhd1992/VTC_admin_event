package com.vtc.gamerid.gateway.mission.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by phucnguyen on 29/01/2018.
 */
@SuppressWarnings("serial")
@Setter
@Getter
public class SplayMissionReportBean implements Serializable {

    private long income;

    private long outcome;

    public SplayMissionReportBean(long income, long outcome) {
        this.income = income;
        this.outcome = outcome;
    }

}
