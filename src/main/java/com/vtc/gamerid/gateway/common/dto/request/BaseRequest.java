package com.vtc.gamerid.gateway.common.dto.request;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 15/12/2017.
 */
@SuppressWarnings("serial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseRequest implements Serializable {
    
    private String searchValue;

    private int    limit  = 10;

    private int    offset = 0;

}
