package com.vtc.gamerid.gateway.sysconfig.service;

import com.vtc.gamerid.gateway.common.dao.entity.TblSysConfig;
import com.vtc.gamerid.gateway.common.dao.repository.SysConfigRepository;
import com.vtc.gamerid.gateway.sysconfig.service._interface.SysConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by phucnguyen on 15/03/2017.
 */
@Service
public class SysConfigServiceImpl implements SysConfigService {
    private final Logger logger = LoggerFactory.getLogger(SysConfigServiceImpl.class);
    @Autowired
    private SysConfigRepository sysConfigDao;

    @Override
    @Transactional
    public List<TblSysConfig> getAllSysConfig() {
        return sysConfigDao.findAll();
    }

    @Override
    @Transactional
    public TblSysConfig getSysConfigByName(String keyName) {
        return sysConfigDao.findByKeyName(keyName);
    }

    @Override
    @Transactional
    public TblSysConfig updateSysConfig(Map<String, Object> dataRequest) {
        try{
            String keyName = (String) dataRequest.get("keyName");
            TblSysConfig sysConfig = getSysConfigByName(keyName);
            if(sysConfig == null) return null;
            String value = (String) dataRequest.get("value");
            if(value == null) return null;
            sysConfig.setValue(value);

            return sysConfigDao.save(sysConfig);
        }catch (Exception e){
            logger.error(e.toString());
        }
        return null;
    }
}
