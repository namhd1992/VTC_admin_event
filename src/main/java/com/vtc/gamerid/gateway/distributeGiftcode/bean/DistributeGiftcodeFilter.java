package com.vtc.gamerid.gateway.distributeGiftcode.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 15/05/2018.
 */
@Setter
@Getter
@NoArgsConstructor
public class DistributeGiftcodeFilter implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -6762395541391064008L;

    private String fromEmail = null;

    private long   fromDate  = -1;

    private long   toDate    = -1;

    private Long   createBy;

}
