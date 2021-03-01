package com.vtc.gamerid.gateway.luckyspin.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vtc.gamerid.gateway.common.dao.repository.AdminReportSpinRepository;
import com.vtc.gamerid.gateway.common.dto.response.ResultReportSpinGetResponseData;
import com.vtc.gamerid.gateway.luckyspin.service._interface.AdminReportSpinService;

/**
 * Created by phucnguyen on 14/07/2017.
 */
@Service
public class AdminReportSpinServiceImpl implements AdminReportSpinService {
//    private Logger logger = LoggerFactory.getLogger(AdminReportSpinServiceImpl.class);
    @Autowired
    private AdminReportSpinRepository adminReportSpinDao;

    @Override
    public List<ResultReportSpinGetResponseData> getResultReportSpin(long luckySpinId, Date startDate, Date endDate) {
        return adminReportSpinDao.getResultReportSpin(luckySpinId, startDate, endDate);
    }

//    @Override
//    public Map<String, Long> countTotalSpinReport(SpinReportBean spinReportBean) {
//        return adminReportSpinDao.countTotalSpinReport(spinReportBean);
//    }

    @Override
    public int countAllUserJoinEventBetweenDate(long luckySpinId, Date startDate, Date endDate) {
        return adminReportSpinDao.countAllUserJoinEventBetweenDate(luckySpinId, startDate, endDate);
    }
}
