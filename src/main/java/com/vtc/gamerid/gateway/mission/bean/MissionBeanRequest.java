package com.vtc.gamerid.gateway.mission.bean;

import java.io.Serializable;

import com.vtc.gamerid.gateway.common.Constants;
import com.vtc.gamerid.gateway.common.dto.request.BeanRequest;
import com.vtc.gamerid.gateway.common.dto.response.ValidateBean;
import com.vtc.gamerid.gateway.common.ultils.StringUtils;
import com.vtc.gamerid.gateway.exception.RegularExpression;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 21/11/2017.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MissionBeanRequest implements Serializable, BeanRequest {

    private static final long serialVersionUID = 1L;

    private Long              missionId;

    private String            missionName;

    private String            description;

    private int               action           = 0;

    private Long              objectId         = (long) 0;

    private String            objectValue      = null;

    private String            linkToShare;

    private String            award;

    private long              spinAwardId;

    private long              valueAward       = 0;

    private Long              valueLimit;

    private String            typeLimit;

    private long              fromDate;

    private long              toDate;

    private boolean           isCyclic         = true;

    private String            defaultImage;

    private String            status           = Constants.STATUS_ACTIVE;

    private String            androidScheme    = null;

    private String            iosScheme        = null;

    private boolean           highLights;

    @Override
    public ValidateBean validate() {
        if (!RegularExpression.validateStripXss(this.missionName))
            return new ValidateBean(false, "Mission name invalid");
        if (!RegularExpression.validateStripXss(this.description))
            return new ValidateBean(false, "Description invalid");
        if (this.action < 0)
            return new ValidateBean(false, "Action invalid");
        if (this.action == 6 && StringUtils.isEmpty(this.linkToShare))
            return new ValidateBean(false, "Invalid link to share");
        if (this.award != null && !RegularExpression.validateStripXss(this.award))
            return new ValidateBean(false, "Award invalid");
        if (this.valueAward < 0)
            return new ValidateBean(false, "Value award invalid");
        if (this.defaultImage != null && !RegularExpression.validateStripXss(this.defaultImage))
            return new ValidateBean(false, "Default image invalid");
        if (this.status != null && !RegularExpression.validateStripXss(this.status))
            return new ValidateBean(false, "Status invalid");
        if (this.androidScheme != null && !RegularExpression.validateStripXss(this.androidScheme))
            return new ValidateBean(false, "Android scheme invalid");
        if (this.iosScheme != null && !RegularExpression.validateStripXss(this.iosScheme))
            return new ValidateBean(false, "IOS scheme invalid");
        /*
         * if(this.objectId < 0) return
         * new ValidateBean(false,
         * "Invalid id of award");
         */
        if (this.objectValue != null && !RegularExpression.validateStripXss(this.objectValue))
            return new ValidateBean(false, "Object value invalid");
        return new ValidateBean(true, Constants.SUCCESS);
    }

    public MissionBeanRequest(String missionName, String description, int action, String award,
                              long valueAward, long fromDate, long toDate, boolean isCyclic) {
        this.missionName = missionName;
        this.description = description;
        this.action = action;
        this.award = award;
        this.valueAward = valueAward;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.isCyclic = isCyclic;
    }

}
