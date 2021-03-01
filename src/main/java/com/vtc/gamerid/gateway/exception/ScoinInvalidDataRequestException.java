/***************************************************************************
 * Product 2018 by Quang Dat * 
 **************************************************************************/
package com.vtc.gamerid.gateway.exception;

import com.vtc.gamerid.gateway.common.StatusCode;

/**
 * Creator : Le Quang Dat
 * Email   : quangdat0993@gmail.com
 * Date    : Oct 11, 2018
 */
public class ScoinInvalidDataRequestException extends SplayBusinessException {
    
    private static final long serialVersionUID = -6502596312985405760L;

    public ScoinInvalidDataRequestException() {
        super(StatusCode.INVALID_DATA_REQUEST, StatusCode.INVALID_DATA_REQUEST_DESCRIPTION);
    }
    
    public ScoinInvalidDataRequestException(String message) {
        super(StatusCode.INVALID_DATA_REQUEST, message);
    }
    
    public ScoinInvalidDataRequestException(String objectName, String message) {
        super(StatusCode.INVALID_DATA_REQUEST, objectName + " : " + message);
    }

}
