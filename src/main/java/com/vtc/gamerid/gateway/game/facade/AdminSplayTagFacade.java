package com.vtc.gamerid.gateway.game.facade;

import java.util.List;

import com.vtc.gamerid.gateway.common.dao.entity.TblSplayTag;
import com.vtc.gamerid.gateway.common.dto.response.BaseDataResponse;
import com.vtc.gamerid.gateway.game.bean.AdminSplayTagBean;
import com.vtc.gamerid.gateway.game.bean.ClientSplayTagFilter;

/**
 * Created by phucnguyen on 03/04/2018.
 */
public interface AdminSplayTagFacade {
    
    public List<TblSplayTag> getSplayTag(ClientSplayTagFilter dataRequest);

    public BaseDataResponse createSplayTag(AdminSplayTagBean dataRequest);

    public BaseDataResponse updateSplayTag(AdminSplayTagBean dataRequest);

    public BaseDataResponse deleteSplayTag(long id);
    
    public int countSplayTag(ClientSplayTagFilter dataRequest);
}
