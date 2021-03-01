package com.vtc.gamerid.gateway.splay.bean;

import com.vtc.gamerid.gateway.common.dto.request.SplayBaseRequest;

/**
 * Created by phucnguyen on 11/01/2018.
 */
public class SplayProfileRequest extends SplayBaseRequest {
    
    private static final long serialVersionUID = -1013506526113510440L;

    public SplayProfileRequest(String api_key, String access_token, int userInfoId) {
        super(api_key, access_token, userInfoId);
    }
    
    public SplayProfileRequest(String access_token, int userInfoId) {
        super(access_token, userInfoId);
    }
}
