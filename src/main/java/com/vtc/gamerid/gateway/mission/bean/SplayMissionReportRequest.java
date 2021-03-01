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
public class SplayMissionReportRequest implements Serializable {
    private long fromDate   = -1;

    private long toDate     = -1;

    private int  action     = -1;

    private int  type       = -1;

    private long timeSwitch = -1;

}
