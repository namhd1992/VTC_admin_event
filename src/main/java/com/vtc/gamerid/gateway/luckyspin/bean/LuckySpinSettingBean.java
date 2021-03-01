package com.vtc.gamerid.gateway.luckyspin.bean;

import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.dto.request.BeanRequest;
import com.vtc.gamerid.gateway.common.dto.response.ValidateBean;
import com.vtc.gamerid.gateway.exception.RegularExpression;

import java.io.Serializable;

/**
 * Created by phucnguyen on 14/09/2017.
 */
public class LuckySpinSettingBean implements Serializable, BeanRequest {
    private int id = -1;
    private int intValue = -1;
    private double doubleValue = -1;
    private String stringValue = null;
    private String status = null;

    public LuckySpinSettingBean() {
    }

    @Override
    public ValidateBean validate() {
        if(this.status != null && !RegularExpression.validateStripXss(this.status))
            return new ValidateBean(false, "Status invalid");
        if(this.stringValue != null &&
                !RegularExpression.validateStripXss(this.stringValue))
            return new ValidateBean(false, "String value invalid");
        return new ValidateBean(true, Constants.SUCCESS);
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
