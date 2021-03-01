package com.vtc.gamerid.gateway;

import static com.vtc.gamerid.gateway.common.Constants.sysConfig;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.vtc.gamerid.gateway.common.dao.entity.TblSysConfig;
import com.vtc.gamerid.gateway.sysconfig.service._interface.SysConfigService;

/**
 * Created by phucnguyen on 12/04/2017.
 */
@Component
public class ApplicationStartup
        implements ApplicationListener<ApplicationReadyEvent> {
    private Logger logger = LoggerFactory.getLogger(ApplicationStartup.class);

    @Autowired
    private SysConfigService sysConfigService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        //get caches sys_config
        logger.info("******Loading System Config******");
        List<TblSysConfig> listSysConfig = sysConfigService.getAllSysConfig();
        for (TblSysConfig instance : listSysConfig) {
            sysConfig.put(instance.getKeyName(), instance.getValue());
        }
        logger.info("******End Load System Config******");
    }

}
