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
 * Date    : Oct 30, 2018
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScoinBaseResponse<T> {

    private T       data;

    private Boolean status;

    private Integer error_code;

}
