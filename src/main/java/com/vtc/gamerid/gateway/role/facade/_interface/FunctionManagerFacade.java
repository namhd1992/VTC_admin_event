package com.vtc.gamerid.gateway.role.facade._interface;

import com.vtc.gamerid.gateway.common.dao.entity.TblFunction;
import com.vtc.gamerid.gateway.common.dto.response.BaseDataResponse;

import java.util.List;
import java.util.Map;

/**
 * Created by phucnguyen on 03/05/2017.
 */
public interface FunctionManagerFacade {
    public BaseDataResponse getListFunction(Map<String, Object> dataRequest);
}
