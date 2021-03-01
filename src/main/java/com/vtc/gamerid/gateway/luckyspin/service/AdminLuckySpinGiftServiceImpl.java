package com.vtc.gamerid.gateway.luckyspin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vtc.gamerid.gateway.common.dao.entity.LuckySpinGift;
import com.vtc.gamerid.gateway.common.dao.entity.UserGameRID;
import com.vtc.gamerid.gateway.common.dao.repository.LuckySpinGiftRepository;
import com.vtc.gamerid.gateway.common.dto.request.BaseRequest;
import com.vtc.gamerid.gateway.common.dto.request.LuckySpinGiftBeanRequest;
import com.vtc.gamerid.gateway.common.service.AbstractService;
import com.vtc.gamerid.gateway.luckyspin.service._interface.AdminLuckySpinGiftService;
import com.vtc.gamerid.gateway.profile.service.AdminProfileService;

@Service("adminLuckySpinGiftService")
public class AdminLuckySpinGiftServiceImpl
        extends AbstractService<LuckySpinGift, Long, LuckySpinGiftRepository>
        implements AdminLuckySpinGiftService {
    
    @Autowired
    AdminProfileService adminProfileService;
    
    @Override
    public List<LuckySpinGift> getItemGift(BaseRequest request) {
        UserGameRID userInfo = adminProfileService.getSessionUserInfo();
        Long userId = (long) 0;
        if(!userInfo.getGroupRole().getName().equals("admin"))
            userId = userInfo.getId();
            
        return repo.findAll();
    }
    
    @Override
    public int countLuckySpinGift() {
        return repo.countAllLuckySpinGift();
        
    }

    @Override
    public LuckySpinGift createItemGift(LuckySpinGiftBeanRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LuckySpinGift updateItemGift(LuckySpinGiftBeanRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LuckySpinGift deleteItemGift(Long spinGiftId) {
        // TODO Auto-generated method stub
        return null;
    }

}
