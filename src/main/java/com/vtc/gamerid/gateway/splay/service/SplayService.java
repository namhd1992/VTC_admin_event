package com.vtc.gamerid.gateway.splay.service;

import java.util.Date;
import java.util.List;

import com.vtc.gamerid.gateway.common.dao.entity.TblUserVTC;
import com.vtc.gamerid.gateway.common.dto.response.HistoryLoginGameResponse;
import com.vtc.gamerid.gateway.common.dto.response.SplayBaseResponse;
import com.vtc.gamerid.gateway.splay.bean.SplayBuyItemBean;
import com.vtc.gamerid.gateway.splay.bean.SplayEventRequest;
import com.vtc.gamerid.gateway.splay.bean.SplayEventResponse;
import com.vtc.gamerid.gateway.splay.bean.SplayProfileRequest;
import com.vtc.gamerid.gateway.splay.bean.SplayTopupBean;

/**
 * Created by phucnguyen on 12/01/2018.
 */
public interface SplayService {
    public SplayBaseResponse splayTopup(SplayTopupBean dataRequest);

    public SplayBaseResponse splayBuyItem(SplayBuyItemBean dataRequest);

    public SplayBaseResponse getProfileFromSplay(SplayProfileRequest dataRequest);

    public SplayEventResponse splayGetEvent(SplayEventRequest dataRequest);

    public boolean checkLoginScoinInService(long serviceId, long scoinId, Date fromDate,
                                            Date toDate);

    public List<HistoryLoginGameResponse> getLoginGameFromScoin(TblUserVTC userVTC);

}
