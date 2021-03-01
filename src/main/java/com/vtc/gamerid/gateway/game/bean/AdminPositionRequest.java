package com.vtc.gamerid.gateway.game.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by phucnguyen on 21/05/2018.
 */

public class AdminPositionRequest implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -6799568039129352313L;
    private List<AdminSplayGamePositionBean> dataRequest;

    public AdminPositionRequest() {
    }

    public AdminPositionRequest(List<AdminSplayGamePositionBean> dataRequest) {
        this.dataRequest = dataRequest;
    }

    public List<AdminSplayGamePositionBean> getDataRequest() {
        return dataRequest;
    }

    public void setDataRequest(List<AdminSplayGamePositionBean> dataRequest) {
        this.dataRequest = dataRequest;
    }
}
