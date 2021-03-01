/***************************************************************************
 * Product 2018 by Quang Dat * 
 **************************************************************************/
package com.vtc.gamerid.gateway.common.dto.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Creator : Le Quang Dat
 * Email   : quangdat0993@gmail.com
 * Date    : Oct 11, 2018
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SplayResponse<T> implements Serializable{
    
    private static final long serialVersionUID = -6669686067446636607L;

    protected String          status;

    protected String          message;

    protected T               data;

    protected int             totalRecords = 0;

}
