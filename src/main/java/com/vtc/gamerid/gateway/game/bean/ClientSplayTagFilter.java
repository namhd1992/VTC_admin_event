package com.vtc.gamerid.gateway.game.bean;

import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.dto.response.ValidateBean;
import com.vtc.gamerid.gateway.exception.RegularExpression;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by phucnguyen on 03/04/2018.
 */
@Getter
@Setter
@NoArgsConstructor
public class ClientSplayTagFilter implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1796198001290187058L;

    private String            searchValue;

    private String            typeName;

    private int               limit            = 10;

    private int               offset           = 0;

    public ValidateBean validate() {
        if (this.searchValue != null && !RegularExpression.validateStripXss(this.searchValue))
            return new ValidateBean(false, Constants.BAD_REQ);
        if (this.typeName != null && !RegularExpression.validateStripXss(this.typeName))
            return new ValidateBean(false, Constants.BAD_REQ);
        return new ValidateBean(true, Constants.SUCCESS);
    }

}
