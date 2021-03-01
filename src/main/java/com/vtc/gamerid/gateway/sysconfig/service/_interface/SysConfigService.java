package com.vtc.gamerid.gateway.sysconfig.service._interface;

import java.util.List;
import java.util.Map;

import com.vtc.gamerid.gateway.common.dao.entity.TblSysConfig;

/**
 * Created by phucnguyen on 15/03/2017.
 */
public interface SysConfigService {
    public List<TblSysConfig> getAllSysConfig();

    public TblSysConfig getSysConfigByName(String name);

    public TblSysConfig updateSysConfig(Map<String, Object> dataRequest);
}
