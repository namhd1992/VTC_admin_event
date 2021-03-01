package com.vtc.gamerid.gateway.luckyspin.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.vtc.gamerid.gateway.common.Constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 05/07/2017.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AdSEventManagerBean implements Serializable {
    
    private static final long         serialVersionUID = 1L;

    private Long                      spinEventId;

    private String                    name             = null;

    private String                    type;

    private String                    image            = null;

    private Date                      startDate;

    private Date                      endDate;

    private String                    description      = null;
    
    private String                    linkLiveStream;

    private int                       freeSpinPerDay   = 0;

    private int                       freeSpinOnStart  = 0;

    private int                       maxSpinPerUser   = -1;

    private String                    buyTurnType;

    private int                       pricePerTurn;

    private String                    status           = Constants.STATUS_ACTIVE;
    
    private Date                      goldTimeStart;

    private Date                      goldTimeEnd;
    
    private int                       goldTimeItemIndex;

    private boolean                   goldTimeStatus;

    private List<ItemOfSpinEventBean> itemOfSpin;

}
