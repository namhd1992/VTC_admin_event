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
 * Date    : Sep 19, 2018
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetWinnerResponse {
    
    private String userName;
    
    private String email;
    
    private String phone;
    
    private Long amount;

}
