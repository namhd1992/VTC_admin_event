package com.vtc.gamerid.gateway.adbSetting.bean;

import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.dto.request.BeanRequest;
import com.vtc.gamerid.gateway.common.dto.response.ValidateBean;
import com.vtc.gamerid.gateway.exception.RegularExpression;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by phucnguyen on 07/08/2017.
 */
@SuppressWarnings("serial")
@Setter
@Getter
@NoArgsConstructor
public class AdbSettingBean implements Serializable, BeanRequest {
    private int    limit  = 10;

    private int    offset = 0;

    private Long   id;

    private String name   = null;

    private String value  = null;

    @Override
    public ValidateBean validate() {
        if(!RegularExpression.validateStripXss(this.name))
            return new ValidateBean(false, "Name invalid");
        if(!RegularExpression.validateStripXss(this.value))
            return new ValidateBean(false, "Value invalid");
        return new ValidateBean(true, Constants.SUCCESS);
    }
}
