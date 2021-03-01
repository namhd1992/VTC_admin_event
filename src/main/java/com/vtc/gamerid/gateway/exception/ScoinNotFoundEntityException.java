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
public class ScoinNotFoundEntityException extends SplayBusinessException{
    
    public ScoinNotFoundEntityException() {
        super(StatusCode.NOT_FOUND_ENTITY, StatusCode.NOT_FOUND_ENTITY_DESCRIPTION);
    }
    
    public ScoinNotFoundEntityException(String message) {
        super(StatusCode.NOT_FOUND_ENTITY, message);
    }
    
    public ScoinNotFoundEntityException(String objectName, String message) {
        super(StatusCode.NOT_FOUND_ENTITY, objectName + " : " + message);
    }

}
