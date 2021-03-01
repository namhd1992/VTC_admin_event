package com.vtc.gamerid.gateway.scoin.authorization.facade;

import com.vtc.gamerid.gateway.scoin.authorization.bean.AdbLoginResponse;
import com.vtc.gamerid.gateway.scoin.authorization.bean.AdbLoginScoinRequest;

/**
 * Created by phucnguyen on 22/05/2017.
 */
public interface LoginScoinFacade {
//    public BaseDataResponse loginScoin(Map<String, Object> dataRequest);

    public AdbLoginResponse loginScoinFromAdb(AdbLoginScoinRequest adbLoginScoinRequest);
}
