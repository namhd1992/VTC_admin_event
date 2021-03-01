package com.vtc.gamerid.gateway.adbSetting.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vtc.gamerid.gateway.adbSetting.bean.AdbSettingRequest;
import com.vtc.gamerid.gateway.adbSetting.facade.AdbSettingFacade;
import com.vtc.gamerid.gateway.common.controller.AbstractController;

/**
 * Created by phucnguyen on 07/08/2017.
 */
@RestController
public class AdbSettingController extends AbstractController<AdbSettingFacade> {

    @RequestMapping(method = RequestMethod.GET, value = "/admin/adbSetting")
    public ResponseEntity<?> getAdbSetting() {
        return response(toResult(service.getAdbSettings()));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/admin/adbSetting")
    public ResponseEntity<?> updateAdbSetting(
            @RequestBody AdbSettingRequest adbSettingRequest) {
        return response(toResult(service.updateAdbSettings(adbSettingRequest)));
    }
}
