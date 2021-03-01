package com.vtc.gamerid.gateway.luckyspin.service._interface;

import java.util.List;

import com.vtc.gamerid.gateway.common.dao.entity.LuckySpin;
import com.vtc.gamerid.gateway.luckyspin.bean.AdSEventManagerBean;
import com.vtc.gamerid.gateway.luckyspin.bean.AdminSpinEventFilterRequest;

/**
 * Created by phucnguyen on 29/06/2017.
 */
public interface AdminLuckySpinService {
    public List<LuckySpin> getListLuckySpin(AdminSpinEventFilterRequest request);

    public int adminCountGetLuckySpin(AdminSpinEventFilterRequest request);

    public LuckySpin createLuckySpin(AdSEventManagerBean request);

    public LuckySpin updateLuckySpin(AdSEventManagerBean request);

    public String deleteLuckySpin(Long luckySpinId);

}
