package com.vtc.gamerid.gateway.luckyspin.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by phucnguyen on 14/09/2017.
 */
public class LuckySpinSettingList implements Serializable {
    private List<LuckySpinSettingBean> dataRequest;

    public LuckySpinSettingList() {
    }

    public List<LuckySpinSettingBean> getDataRequest() {
        return dataRequest;
    }

    public void setDataRequest(List<LuckySpinSettingBean> dataRequest) {
        this.dataRequest = dataRequest;
    }
}
