package com.vtc.gamerid.gateway.common.dto.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 11/01/2018.
 */
@SuppressWarnings("serial")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SplayBaseResponse implements Serializable {
    private boolean status;

    private int     error_code;

    private String  message;

    private Object  data;

}
