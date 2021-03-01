package com.vtc.gamerid.gateway.game.bean;

import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.dto.response.ValidateBean;
import com.vtc.gamerid.gateway.exception.RegularExpression;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by phucnguyen on 05/12/2017.
 */
@Getter
@Setter
@NoArgsConstructor
public class AdminSplayGameFilter implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private int               limit            = 10;

    private int               offset           = 0;

    private String            searchValue;

    private boolean           onlyScoinGame;

    private Long              createBy;

    public ValidateBean validate() {
        if (searchValue != null && !RegularExpression.validateStripXss(this.searchValue))
            return new ValidateBean(false, Constants.BAD_REQ);
        return new ValidateBean(true, Constants.SUCCESS);
    }
}
