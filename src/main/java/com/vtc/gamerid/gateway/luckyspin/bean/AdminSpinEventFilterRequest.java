package com.vtc.gamerid.gateway.luckyspin.bean;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 29/06/2017.
 */
@Setter
@Getter
@NoArgsConstructor
public class AdminSpinEventFilterRequest implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private int               limit            = 0;

    private int               offset           = 0;

    private String            searchValue      = null;

    private Date              dateActive       = null;

    private Long              publisherId;

    private String            status;

    public AdminSpinEventFilterRequest(int limit, int offset) {
        this.limit = limit;
        this.offset = offset;
    }

}
