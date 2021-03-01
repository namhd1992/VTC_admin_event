package com.vtc.gamerid.gateway.luckyspin.service._interface;

import java.util.Date;
import java.util.List;

import com.vtc.gamerid.gateway.common.dto.response.ResultReportSpinGetResponseData;

/**
 * Created by phucnguyen on 14/07/2017.
 */
public interface AdminReportSpinService {
    public List<ResultReportSpinGetResponseData> getResultReportSpin(long luckySpinId, Date startDate, Date endDate);
    
//    public Map<String, Long> countTotalSpinReport(SpinReportBean spinReportBean);
    
    public int countAllUserJoinEventBetweenDate(long luckySpinId, Date startDate, Date endDate);
}
