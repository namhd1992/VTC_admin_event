package com.vtc.gamerid.gateway.checkin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vtc.gamerid.gateway.checkin.bean.AdminCheckinItemBean;
import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.dao.entity.TblCheckinItem;
import com.vtc.gamerid.gateway.common.dao.repository.CheckinItemRepository;
import com.vtc.gamerid.gateway.common.dto.response.ServiceResponse;
import com.vtc.gamerid.gateway.exception.RollBackTransaction;

/**
 * Created by phucnguyen on 05/12/2017.
 */
@Service
public class AdminCheckinItemServiceImpl implements AdminCheckinItemService {
    @Autowired
    private CheckinItemRepository adminCheckinItemDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ServiceResponse updateCheckinItem(AdminCheckinItemBean dataRequest) {
        //Get item
        TblCheckinItem checkinItem = adminCheckinItemDao.findByDay(dataRequest.getDay());
        if(checkinItem == null){
            RollBackTransaction.callRollback();
            return new ServiceResponse(false, Constants.OBJECT_NOT_EXIST);
        }
        if(dataRequest.getAwardPoint() > -1)
            checkinItem.setAwardPoint(dataRequest.getAwardPoint());
        if(dataRequest.getAwardName() != null)
            checkinItem.setAwardName(dataRequest.getAwardName());
        if(dataRequest.getPointBonus() > -1)
            checkinItem.setPointBonus(dataRequest.getPointBonus());
        adminCheckinItemDao.save(checkinItem);
        return new ServiceResponse(true, Constants.SUCCESS);
    }

    @Override
    public ServiceResponse getCheckinItem() {
        return new ServiceResponse(true, adminCheckinItemDao.findAll());
    }
}
