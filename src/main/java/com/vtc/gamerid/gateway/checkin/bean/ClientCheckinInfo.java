package com.vtc.gamerid.gateway.checkin.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by phucnguyen on 13/12/2017.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClientCheckinInfo {
    
    public int     toDay     = 1;

    public boolean checkined = false;

}
