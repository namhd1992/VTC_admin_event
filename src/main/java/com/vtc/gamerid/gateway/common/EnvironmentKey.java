/***************************************************************************
 * Product 2018 by Quang Dat * 
 **************************************************************************/
package com.vtc.gamerid.gateway.common;

/**
 * Creator : Le Quang Dat
 * Email   : quangdat0993@gmail.com
 * Date    : Aug 27, 2018
 */
public enum EnvironmentKey {
    
    
    
//  ###################### SANBOX ###############################
    SCOIN_LIVE_CLIENTID("scoin.live.clientId"),
    SANDBOX_SCOIN_API_URL("sandbox.scoin.url.api"),
    SANDBOX_SCOIN_API_KEY("sandbox.scoin.api.key"),
    SANDBOX_SCOIN_SECRET_KEY("sandbox.scoin.secret.key"),
    SANDBOX_TOPGAME_API_URL("sandbox.topgame.url.api"),
    SANDBOX_TOPGAME_SECRET_KEY("sandbox.topgame.secret.key"),
    
//    ###################### CONFIG ###############################
    
//    -------------------------- SYSTEM ----------------------
    PROJECT_BASE_URL("project.base.url"),
    HEADER_BASE_URL("header.base.url"),
    
    
    
//    -------------------------- XU ----------------------
    LIVE_SCOIN_API_XU_URL("live.scoin.url.xu.api"),
    
    
    
//    +++++++++++++++++++++++++ SPLAY +++++++++++++++++++++++
    LIVE_SCOIN_XU_API_KEY("live.scoin.xu.api.key"),
    LIVE_SCOIN_XU_SECRET_KEY("live.scoin.xu.secret.key"),
    PACKAGE_COIN_SCOIN("package.coin.scoin"),
    
    
//  +++++++++++++++++++++++++ EVENT GAME +++++++++++++++++++++++
    EVENTGAME_TRIEUHOI_CHINHDO_URL("eventgame.trieuhoi.chinhdo.url"),
    EVENTGAME_TRIEUHOI_CHINHDO_SECRET("eventgame.trieuhoi.chinhdo.secret"),
   
//  +++++++++++++++++++++++++ EVENT GAME +++++++++++++++++++++++
    MCD_SERVER_INFO_URL("mcd.server.info"),
    MCD_PLAYER_INFO_URL("mcd.player.info.url"),
    MCD_PLAYER_INFO_KEY("mcd.player.info.key"),
    
    
//  +++++++++++++++++++++++++ PROVIDERS +++++++++++++++++++++++
    MINI_GAME_CUONGPHAN_NAME("minigame.cuongphan.name"),
    MINI_GAME_CUONGPHAN_PROVIDER_ID("minigame.cuongphan.provider.id"),
    MINI_GAME_CUONGPHAN_PROVIDER_SERCRET("minigame.cuongphan.provider.secret"),
    
    
    
//  +++++++++++++++++++++++++ TOPGAME-330281 +++++++++++++++++++++++
    LIVE_SCOIN_XU_API_KEY_TOPGAME("live.scoin.xu.api.key.topgame"),
    LIVE_SCOIN_XU_SECRET_KEY_TOPGAME("live.scoin.xu.secret.key.topgame"),
    
    
    
//  -------------------------- KNB-GAME ----------------------
    LIVE_TOPGAME_API_URL("live.topgame.url.api"),
    LIVE_TOPGAME_SECRET_KEY("live.topgame.secret.key")
    
    ;
    
    private String key;

    EnvironmentKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

}
