package com.vtc.gamerid.gateway.splay.transaction.facade;

import com.vtc.gamerid.gateway.common.dto.response.BaseDataResponse;
import com.vtc.gamerid.gateway.splay.transaction.bean.AdminReportTransRequest;

/**
 * Created by phucnguyen on 11/06/2018.
 */
public interface AdminSplayTransFacade {
    public BaseDataResponse getTransFromSplay(AdminReportTransRequest dataRequest);
}
