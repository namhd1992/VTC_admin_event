package com.vtc.gamerid.gateway.common.dto.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 03/07/2017.
 */
@SuppressWarnings("serial")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ValidateBean implements Serializable {
    private boolean isSuccess;

    private int     _code;

    private String  message;

    public ValidateBean(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }
    
    public ValidateBean(int _code, String message) {
        this._code = _code;
        this.message = message;
    }

}
