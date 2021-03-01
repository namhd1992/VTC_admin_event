package com.vtc.gamerid.gateway.common.dto.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 04/07/2017.
 */
@SuppressWarnings("serial")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResponse implements Serializable{
    private boolean isSuccess;

    private int     _code;

    private String  message;

    private Object  dataResponse;

    public ServiceResponse(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public ServiceResponse(boolean isSuccess, Object dataResponse) {
        this.isSuccess = isSuccess;
        this.dataResponse = dataResponse;
    }

}
