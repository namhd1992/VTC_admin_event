package com.vtc.gamerid.gateway.mission.bean;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 21/11/2017.
 */

@SuppressWarnings("serial")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MissionResponse implements Serializable {
    private int     missionId;

    private String  missionName;

    private String  description;

    private String  actionName;

    private long    valueAward;

    private Date    fromDate;

    private Date    toDate;

    private boolean isCyclic;

    private boolean isFinish;

    private boolean isReceived;

    private long    objectId;

    private String  objectValue;

    private String  androidScheme;

    private String  iosScheme;

    private int  scoinGameId;

    private String  award;

    private Long    awardAvailable;
    
    private Boolean condition;
    
    private String missionStatus;
    
    private Boolean highLights;
    
    private Integer missionProgress;
  
    private Integer missionSatisfying;

    public MissionResponse(int missionId, String missionName, String description, String actionName, long valueAward, Date fromDate, Date toDate, boolean isCyclic, boolean isFinish, boolean isReceived) {
        this.missionId = missionId;
        this.missionName = missionName;
        this.description = description;
        this.actionName = actionName;
        this.valueAward = valueAward;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.isCyclic = isCyclic;
        this.isFinish = isFinish;
        this.isReceived = isReceived;
    }

}
