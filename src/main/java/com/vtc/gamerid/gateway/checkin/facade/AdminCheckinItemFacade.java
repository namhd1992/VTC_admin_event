package com.vtc.gamerid.gateway.checkin.facade;

import com.vtc.gamerid.gateway.checkin.bean.AdminCheckinItemBean;
import com.vtc.gamerid.gateway.common.dto.response.BaseDataResponse;

/**
 * Created by phucnguyen on 05/12/2017.
 */
public interface AdminCheckinItemFacade {
    public BaseDataResponse updateCheckinItem(AdminCheckinItemBean dataRequest);

    public BaseDataResponse getListItemCheckin();
}
