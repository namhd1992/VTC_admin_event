package com.vtc.gamerid.gateway.profile.bean;

import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.dto.request.BeanRequest;
import com.vtc.gamerid.gateway.common.dto.response.ValidateBean;
import com.vtc.gamerid.gateway.exception.RegularExpression;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * Created by phucnguyen on 26/10/2017.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileBeanRequest implements Serializable, BeanRequest {
    /**
     * 
     */
    private static final long serialVersionUID = -6703162348129806625L;

    private String        fullName    = null;

    private String        phoneNumber = null;

    private String        email       = null;

    private MultipartFile avatar      = null;

    @Override
    public ValidateBean validate() {
        if(this.fullName != null && !RegularExpression.validateStripXss(this.fullName))
            return new ValidateBean(false, "FullName invalid");
        if((this.phoneNumber != null && !RegularExpression.validateStripXss(this.phoneNumber))
                || this.phoneNumber.length() > 12)
            return new ValidateBean(false, "PhoneNumber invalid");
        if(this.email != null && !RegularExpression.validateStripXss(this.email))
            return new ValidateBean(false, "Email invalid");
        return new ValidateBean(true, Constants.SUCCESS);
    }

}
