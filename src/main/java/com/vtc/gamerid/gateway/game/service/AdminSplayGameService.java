package com.vtc.gamerid.gateway.game.service;

import java.util.List;

import com.vtc.gamerid.gateway.common.dao.entity.Game;
import com.vtc.gamerid.gateway.game.bean.AdminSplayGameBeanRequest;
import com.vtc.gamerid.gateway.game.bean.AdminSplayGameFilter;

/**
 * Created by phucnguyen on 05/12/2017.
 */
public interface AdminSplayGameService {
    
    public Game createSplayGame(AdminSplayGameBeanRequest dataRequest);

    public Game updateSplayGame(AdminSplayGameBeanRequest dataRequest);

    public String deleteSplayGame(Long splayGameId);

    public List<Game> getListSplayGame(AdminSplayGameFilter dataRequest);
}
