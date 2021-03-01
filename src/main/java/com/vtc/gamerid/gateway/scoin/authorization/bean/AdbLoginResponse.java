package com.vtc.gamerid.gateway.scoin.authorization.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 23/08/2017.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdbLoginResponse implements Serializable {
    /**
     * 
     */
    private static final long    serialVersionUID = -2694106953129715279L;

    private String               access_token;

    private String               tokenScoin;

    private String               token_type       = "bearer";

    private AdbLoginResponseData data;

    private String               statusCode;

}
