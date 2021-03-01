/***************************************************************************
 * Product 2018 by Quang Dat * 
 **************************************************************************/
package com.vtc.gamerid.gateway.exception;

import com.vtc.gamerid.gateway.common.StatusCode;

/**
 * Creator : Le Quang Dat
 * Email   : quangdat0993@gmail.com
 * Date    : Oct 17, 2018
 */
public class ScoinDuplicateEntityException extends SplayBusinessException {

    private static final long serialVersionUID = 7641702942202000229L;

    public ScoinDuplicateEntityException() {
        super(StatusCode.FAILED_TO_EXECUTE, StatusCode.FAILED_TO_EXECUTE_DESCRIPTION);
    }

    public ScoinDuplicateEntityException(String message) {
        super(StatusCode.FAILED_TO_EXECUTE, message);
    }

    public ScoinDuplicateEntityException(String objectName, String message) {
        super(StatusCode.FAILED_TO_EXECUTE, objectName + ":" + message);
    }

}
