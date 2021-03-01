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
public class ScoinUnAuthorizationException extends SplayBusinessException{
    
    public ScoinUnAuthorizationException() {
        super(StatusCode.UNAUTHORIZED, StatusCode.UNAUTHORIZED_DESCRIPTION);
    }
    
    public ScoinUnAuthorizationException(String message) {
        super(StatusCode.UNAUTHORIZED, message);
    }
    
    public ScoinUnAuthorizationException(String objectName, String message) {
        super(StatusCode.UNAUTHORIZED, objectName + " : " + message);
    }

}
