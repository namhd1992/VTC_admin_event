package com.vtc.gamerid.gateway.common.dto.request;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 11/01/2018.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SplayBaseRequest implements Serializable {
    
    private static final long serialVersionUID = -8504656635246911622L;

    private String api_key;

    private String access_token;

    private long   accountId;

    public SplayBaseRequest(String access_token, long accountId) {
        this.access_token = access_token;
        this.accountId = accountId;
    }

}
