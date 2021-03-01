package com.vtc.gamerid.gateway.luckyspin.service._interface;

import com.vtc.gamerid.gateway.common.dto.response.ValidateBean;
import com.vtc.gamerid.gateway.exception.RequestException;
import com.vtc.gamerid.gateway.luckyspin.bean.AdSEventManagerBean;
import com.vtc.gamerid.gateway.luckyspin.bean.SpinBuyTurnBeanRequest;
import com.vtc.gamerid.gateway.luckyspin.bean.SpinItemBeanRequest;

/**
 * Created by phucnguyen on 26/06/2017.
 */
public interface SpinValidate {
    public void validateSpinBuyTurn(SpinBuyTurnBeanRequest spinBuyTurnBeanRequest)
            throws RequestException;

    public ValidateBean validateItemSpinRequest(SpinItemBeanRequest spinItemBeanRequest);

    public ValidateBean validateSpinEventRequest(AdSEventManagerBean spinEventManagerBean);
}
