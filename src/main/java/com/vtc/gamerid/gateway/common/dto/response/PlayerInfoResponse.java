/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Feb 18, 2019
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerInfoResponse {
    
    private Long   accountid;

    private Long   serverid;

    private String servername;

    private Long   roleid;

    private String rolename;

    private int    level;
    
}
