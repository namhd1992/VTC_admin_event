package com.vtc.gamerid.gateway.scoin.authorization.service;

import java.util.Map;

import com.vtc.gamerid.gateway.common.dao.entity.TblUserVTC;
import com.vtc.gamerid.gateway.common.dto.response.SplayBaseResponse;

/**
 * Created by phucnguyen on 22/05/2017.
 */
public interface LoginScoinService {
    public Map<String, Object> getAccessTokenFromScoin(String code, String redirect_uri);

    public TblUserVTC loginScoin( String userName, Map<String, Object> detailAuthen);

    public TblUserVTC checkUserScoin(SplayBaseResponse splayBaseResponse);

    public void updateRef(TblUserVTC userVTC);
}
