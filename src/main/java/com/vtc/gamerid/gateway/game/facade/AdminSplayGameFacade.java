package com.vtc.gamerid.gateway.game.facade;

import java.util.List;

import com.vtc.gamerid.gateway.common.dao.entity.Game;
import com.vtc.gamerid.gateway.game.bean.AdminSplayGameBeanRequest;
import com.vtc.gamerid.gateway.game.bean.AdminSplayGameFilter;

/**
 * Created by phucnguyen on 06/12/2017.
 */
public interface AdminSplayGameFacade {
    public List<Game> getSplayGameList(AdminSplayGameFilter dataRequest);

    public Game createSplayGame(AdminSplayGameBeanRequest dataRequest);

    public Game updateSplayGame(AdminSplayGameBeanRequest dataRequest);

    public String deleteSplayGame(Long splayGameId);
}
