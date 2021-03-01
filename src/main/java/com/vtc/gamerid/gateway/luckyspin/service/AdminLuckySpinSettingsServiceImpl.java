/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.luckyspin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.dao.entity.TblLuckySpinSetting;
import com.vtc.gamerid.gateway.common.dao.repository.LuckySpinSettingsRepository;
import com.vtc.gamerid.gateway.common.dto.response.ServiceResponse;
import com.vtc.gamerid.gateway.luckyspin.service._interface.AdminLuckySpinSettingsService;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Jul 29, 2019
 */
@Service("spinSettingService")
public class AdminLuckySpinSettingsServiceImpl implements AdminLuckySpinSettingsService {
    
    @Autowired
    LuckySpinSettingsRepository spinSettingsDao;

    @Override
    public List<TblLuckySpinSetting> getSpinSettingByType(String type) {
        return spinSettingsDao.findByStatusAndType(Constants.STATUS_ACTIVE, type);
    }

    @Override
    public ServiceResponse getAllSpinSetting() {
        return new ServiceResponse(true, spinSettingsDao.findAll());
    }

}
