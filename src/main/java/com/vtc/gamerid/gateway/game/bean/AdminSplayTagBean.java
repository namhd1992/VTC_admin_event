package com.vtc.gamerid.gateway.game.bean;

import java.io.Serializable;
import java.util.List;

import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.dto.response.ValidateBean;
import com.vtc.gamerid.gateway.exception.RegularExpression;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 03/04/2018.
 */
@Getter
@Setter
@NoArgsConstructor
public class AdminSplayTagBean implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -2124845161893397127L;

    private String            name             = null;

    private String            typeName         = null;

    private String            backgroundColor  = null;

    private long              id               = -1;

    private List<String>      listName;

    public ValidateBean validateBean() {
        if (this.name != null && !RegularExpression.validateStripXss(this.name))
            return new ValidateBean(false, Constants.BAD_REQ);
        if (this.typeName != null && !RegularExpression.validateStripXss(this.typeName))
            return new ValidateBean(false, Constants.BAD_REQ);
        return new ValidateBean(true, Constants.SUCCESS);
    }
}
