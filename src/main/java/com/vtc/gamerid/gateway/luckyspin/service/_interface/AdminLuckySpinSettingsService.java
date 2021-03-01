package com.vtc.gamerid.gateway.luckyspin.service._interface;

import java.util.List;

import com.vtc.gamerid.gateway.common.dao.entity.TblLuckySpinSetting;
import com.vtc.gamerid.gateway.common.dto.response.ServiceResponse;

/**
 * Created by phucnguyen on 04/07/2017.
 */
public interface AdminLuckySpinSettingsService {
    public List<TblLuckySpinSetting> getSpinSettingByType(String type);

    public ServiceResponse getAllSpinSetting();

}
