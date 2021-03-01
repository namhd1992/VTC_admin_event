package com.vtc.gamerid.gateway.game.service;

import com.vtc.gamerid.gateway.common.dto.response.ServiceResponse;
import com.vtc.gamerid.gateway.game.bean.AdminSplayTagBean;

/**
 * Created by phucnguyen on 03/04/2018.
 */
public interface AdminSplayTagService {
    
    public ServiceResponse createSplayTag(AdminSplayTagBean dataRequest);

    public ServiceResponse updateSplayTag(AdminSplayTagBean dataRequest);

    public ServiceResponse deleteSplayTag(long id);
}
