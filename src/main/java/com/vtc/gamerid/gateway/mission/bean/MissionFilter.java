package com.vtc.gamerid.gateway.mission.bean;

import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.dto.response.ValidateBean;
import com.vtc.gamerid.gateway.exception.RegularExpression;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by phucnguyen on 21/11/2017.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MissionFilter implements Serializable {
  
    private static final long serialVersionUID = -1231603077709468152L;

    private int               limit            = 10;

    private int               offset           = 0;

    private String            searchValue      = null;

    public ValidateBean validate(){
        if(this.searchValue != null && !RegularExpression.validateStripXss(this.searchValue))
            return new ValidateBean(false, Constants.BAD_REQ);
        return new ValidateBean(true, Constants.SUCCESS);
    }

}
