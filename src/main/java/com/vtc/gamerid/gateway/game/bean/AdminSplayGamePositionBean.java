package com.vtc.gamerid.gateway.game.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 16/05/2018.
 */
@Getter
@Setter
@NoArgsConstructor
public class AdminSplayGamePositionBean implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 7640317879264389011L;

    private int id;

    private int position;

}
