package com.vtc.gamerid.gateway.luckyspin.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 05/07/2017.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemOfSpinEventBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long              itemOfSpinId;

    private Long              spinItemId;

    private int               quantity         = 0;

    private int               position         = -1;

}