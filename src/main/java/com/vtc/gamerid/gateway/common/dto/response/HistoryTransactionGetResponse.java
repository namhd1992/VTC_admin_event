/***************************************************************************
 * Product 2018 by Quang Dat * 
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Creator : Le Quang Dat
 * Email   : quangdat0993@gmail.com
 * Date    : Sep 26, 2018
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HistoryTransactionGetResponse {
    
    private Long   TransID;

    private int   Amount;

    private String Channel;

    private String CreateTime;
    
}
