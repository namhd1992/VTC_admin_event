package com.vtc.gamerid.gateway.game.bean;

import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.dto.response.ValidateBean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by phucnguyen on 18/04/2018.
 */
@Getter
@Setter
@NoArgsConstructor
public class ClientReviewGameBean implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 3303683981771113063L;

    private int               gameId           = -1;

    private int               userId           = -1;

    private float             point            = -1;

    public ValidateBean validate() {
        if (this.gameId < 1 || this.point < 1)
            return new ValidateBean(false, Constants.BAD_REQ);
        return new ValidateBean(true, Constants.SUCCESS);
    }

}
