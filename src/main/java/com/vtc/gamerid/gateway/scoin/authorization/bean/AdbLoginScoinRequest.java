package com.vtc.gamerid.gateway.scoin.authorization.bean;

import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.dto.request.BeanRequest;
import com.vtc.gamerid.gateway.common.dto.response.ValidateBean;
import com.vtc.gamerid.gateway.exception.RegularExpression;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by phucnguyen on 23/08/2017.
 */
@Getter
@Setter
@NoArgsConstructor
public class AdbLoginScoinRequest implements Serializable, BeanRequest {
    /**
     * 
     */
    private static final long serialVersionUID = 8478608688114339618L;

    private String            code;

    private String            redirect_uri;

    @Override
    public ValidateBean validate() {
        if(!RegularExpression.validateStripXss(this.code))
            return new ValidateBean(false, "Code invalid");
        if(!RegularExpression.validateStripXss(this.redirect_uri))
            return new ValidateBean(false, "Redirect uri invalid");
        return new ValidateBean(true, Constants.SUCCESS);
    }

}
