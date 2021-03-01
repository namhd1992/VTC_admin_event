package com.vtc.gamerid.gateway.luckyspin.service._interface;

import java.util.List;

import com.vtc.gamerid.gateway.common.dao.entity.LuckySpinGift;
import com.vtc.gamerid.gateway.common.dto.request.BaseRequest;
import com.vtc.gamerid.gateway.common.dto.request.LuckySpinGiftBeanRequest;

public interface AdminLuckySpinGiftService {
    
    List<LuckySpinGift> getItemGift(BaseRequest request);
    
    int countLuckySpinGift();
    
    LuckySpinGift createItemGift(LuckySpinGiftBeanRequest request);

    LuckySpinGift updateItemGift(LuckySpinGiftBeanRequest request);

    LuckySpinGift deleteItemGift(Long spinGiftId);

}
