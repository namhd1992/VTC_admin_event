/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.exception;

import com.vtc.gamerid.gateway.common.StatusCode;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Nov 20, 2018
 */
public class UnknownErrorException extends SplayBusinessException {
    
    private static final long serialVersionUID = -1354707523557446934L;

    public UnknownErrorException() {
        super(StatusCode.UNKNOWN_ERROR, StatusCode.UNKNOWN_ERROR_DESCRIPTION);
    }

    public UnknownErrorException(String message) {
        super(StatusCode.UNKNOWN_ERROR, message);
    }
    
    public UnknownErrorException(String status, String message) {
        super(status, message);
    }

//    public UnknownErrorException(String objectName, String message) {
//        super(StatusCode.UNKNOWN_ERROR, objectName + ":" + message);
//    }

}
