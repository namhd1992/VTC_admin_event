package com.vtc.gamerid.gateway.checkin.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 12/01/2018.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CheckActionRequest implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private String            scoinToken;

}
