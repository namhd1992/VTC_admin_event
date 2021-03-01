package com.vtc.gamerid.gateway.checkin.bean;

import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.dto.response.ValidateBean;
import com.vtc.gamerid.gateway.exception.RegularExpression;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by phucnguyen on 04/12/2017.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdminCheckinItemBean implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private int               id;

    private int               day;

    private int               awardPoint       = -1;

    private int               pointBonus       = -1;

    private String            awardName        = null;

    public ValidateBean validate() {
        if (this.awardName != null &&
                !RegularExpression.validateStripXss(this.awardName))
            return new ValidateBean(false, Constants.BAD_REQ);
        return new ValidateBean(true, Constants.SUCCESS);
    }

}
