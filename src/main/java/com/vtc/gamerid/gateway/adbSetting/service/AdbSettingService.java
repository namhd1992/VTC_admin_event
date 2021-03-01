package com.vtc.gamerid.gateway.adbSetting.service;

import java.util.List;

import com.vtc.gamerid.gateway.adbSetting.bean.AdbSettingBean;
import com.vtc.gamerid.gateway.common.dao.entity.TblAdbSetting;

/**
 * Created by phucnguyen on 07/08/2017.
 */
public interface AdbSettingService {
    public List<TblAdbSetting> getAdbSetting();

    public List<TblAdbSetting> updateAdbSetting(List<AdbSettingBean> adbSettingBean);
}
