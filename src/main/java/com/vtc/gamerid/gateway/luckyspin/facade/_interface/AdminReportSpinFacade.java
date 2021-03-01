package com.vtc.gamerid.gateway.luckyspin.facade._interface;

import java.util.List;

import com.vtc.gamerid.gateway.common.dto.response.ResultReportSpinGetResponse;

/**
 * Created by phucnguyen on 14/07/2017.
 */
public interface AdminReportSpinFacade {
    public List<ResultReportSpinGetResponse> resultReportSpin(long luckySpinId, long startDate, long endDate);
}
