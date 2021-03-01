/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.exception;

import com.vtc.gamerid.gateway.common.StatusCode;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Oct 14, 2018
 */
public class FailedToExecuteException extends SplayBusinessException {
    
    private static final long serialVersionUID = 7641702942202000228L;

    public FailedToExecuteException() {
        super(StatusCode.FAILED_TO_EXECUTE, StatusCode.FAILED_TO_EXECUTE_DESCRIPTION);
    }
    
    public FailedToExecuteException(String message) {
        super(StatusCode.FAILED_TO_EXECUTE, message);
    }

    public FailedToExecuteException(String objectName, String message) {
        super(StatusCode.FAILED_TO_EXECUTE, objectName + ":" + message);
    }

}
