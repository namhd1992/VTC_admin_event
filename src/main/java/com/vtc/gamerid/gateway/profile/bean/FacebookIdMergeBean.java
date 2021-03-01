package com.vtc.gamerid.gateway.profile.bean;

import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.dto.response.ValidateBean;
import com.vtc.gamerid.gateway.exception.RegularExpression;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by phucnguyen on 09/03/2018.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FacebookIdMergeBean implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -1201526423695832421L;

    private String facebookId  = null;

    private String urlAvatar   = null;

    private String email       = null;

    private String fullName    = null;

    private String phoneNumber = null;

    public ValidateBean validate(){
        if(!RegularExpression.validateStripXss(this.facebookId))
            return new ValidateBean(false, Constants.BAD_REQ);
        return new ValidateBean(true, Constants.SUCCESS);
    }
    
}
