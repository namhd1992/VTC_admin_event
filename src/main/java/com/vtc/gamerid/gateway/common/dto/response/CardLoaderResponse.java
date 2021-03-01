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
 * Date    : Sep 28, 2018
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CardLoaderResponse {
    
    private int  amount;

    private long transactionId;

}
