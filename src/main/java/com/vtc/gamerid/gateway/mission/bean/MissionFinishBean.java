package com.vtc.gamerid.gateway.mission.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 28/12/2017.
 */
@Setter
@Getter
@NoArgsConstructor
public class MissionFinishBean implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private boolean           isFinish;

    private boolean           isReceiverd;

    public MissionFinishBean(boolean isFinish, boolean isReceiverd) {
        this.isFinish = isFinish;
        this.isReceiverd = isReceiverd;
    }

}
