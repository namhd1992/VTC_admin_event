/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.giftcode.service;

import java.io.IOException;

import com.vtc.gamerid.gateway.common.dto.request.GiftcodeCreateAndUpdateRequest;
import com.vtc.gamerid.gateway.common.dto.response.GiftcodeGetRequest;
import com.vtc.gamerid.gateway.common.dto.response.GiftcodeGetResponse;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Apr 27, 2020
 */
public interface GiftcodeServcie {
    
    GiftcodeGetResponse getGiftcodeInfo(GiftcodeGetRequest request);
    
    String createGiftcode(GiftcodeCreateAndUpdateRequest request) throws IOException;

}
