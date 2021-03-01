package com.vtc.gamerid.gateway.adbSetting.facade;

import java.util.List;

import com.vtc.gamerid.gateway.adbSetting.bean.AdbSettingRequest;
import com.vtc.gamerid.gateway.common.dao.entity.TblAdbSetting;

/**
 * Created by phucnguyen on 07/08/2017.
 */
public interface AdbSettingFacade {
    public List<TblAdbSetting> getAdbSettings();

    public List<TblAdbSetting> updateAdbSettings(AdbSettingRequest adbSettingRequest);
}
