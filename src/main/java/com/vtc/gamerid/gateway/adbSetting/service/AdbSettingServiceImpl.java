package com.vtc.gamerid.gateway.adbSetting.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vtc.gamerid.gateway.adbSetting.bean.AdbSettingBean;
import com.vtc.gamerid.gateway.common.dao.entity.TblAdbSetting;
import com.vtc.gamerid.gateway.common.dao.repository.AdbSettingRepository;
import com.vtc.gamerid.gateway.common.service.AbstractService;
import com.vtc.gamerid.gateway.exception.FailedToExecuteException;
import com.vtc.gamerid.gateway.exception.ScoinNotFoundEntityException;

/**
 * Created by phucnguyen on 07/08/2017.
 */
@Service
public class AdbSettingServiceImpl extends AbstractService<TblAdbSetting, Long, AdbSettingRepository> implements AdbSettingService {

    @Override
    public List<TblAdbSetting> getAdbSetting() {
        return repo.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<TblAdbSetting> updateAdbSetting(List<AdbSettingBean> adbSettingBean) {
        List<TblAdbSetting> responses = new ArrayList<TblAdbSetting>();
        for (AdbSettingBean instance : adbSettingBean) {
            //Get adb setting
            TblAdbSetting adbSetting = repo.findOne(instance.getId());
            if (adbSetting == null)
                throw new ScoinNotFoundEntityException();

            //Clone data
            adbSetting.setName(instance.getName());
            adbSetting.setValue(instance.getValue());

            //Update adb setting
            adbSetting = repo.save(adbSetting);
            if (adbSetting == null) 
                throw new FailedToExecuteException();
            responses.add(adbSetting);
        }
        return responses;
    }
}
