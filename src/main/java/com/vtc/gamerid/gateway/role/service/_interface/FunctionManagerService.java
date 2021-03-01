package com.vtc.gamerid.gateway.role.service._interface;

import java.util.List;

import com.vtc.gamerid.gateway.common.dao.entity.TblFunction;

/**
 * Created by phucnguyen on 03/05/2017.
 */
public interface FunctionManagerService {
    public List<TblFunction> getListFunction(String status, int limit, int offset);

    public int countUserInfo(String status, String searchValue);

    public int countFunction(String status);
}
