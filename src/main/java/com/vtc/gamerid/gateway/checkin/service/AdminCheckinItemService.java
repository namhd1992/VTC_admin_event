package com.vtc.gamerid.gateway.checkin.service;

import com.vtc.gamerid.gateway.checkin.bean.AdminCheckinItemBean;
import com.vtc.gamerid.gateway.common.dto.response.ServiceResponse;

/**
 * Created by phucnguyen on 04/12/2017.
 */
public interface AdminCheckinItemService {
    public ServiceResponse updateCheckinItem(AdminCheckinItemBean dataRequest);

    public ServiceResponse getCheckinItem();
}
