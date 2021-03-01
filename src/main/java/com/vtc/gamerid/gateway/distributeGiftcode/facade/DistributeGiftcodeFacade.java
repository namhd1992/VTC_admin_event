package com.vtc.gamerid.gateway.distributeGiftcode.facade;

import java.io.IOException;

import com.vtc.gamerid.gateway.common.dto.response.BaseDataResponse;
import com.vtc.gamerid.gateway.distributeGiftcode.bean.DistributeGiftcodeBean;

/**
 * Created by phucnguyen on 23/04/2018.
 */
public interface DistributeGiftcodeFacade{
    public BaseDataResponse distributeGiftcode(DistributeGiftcodeBean dataRequest) throws IOException;
//    public BaseDataResponse reportDistributeGiftcode(DistributeGiftcodeFilter dataRequest);
}
