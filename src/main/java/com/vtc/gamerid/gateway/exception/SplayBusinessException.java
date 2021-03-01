/***************************************************************************
 * Product 2018 by Quang Dat * 
 **************************************************************************/
package com.vtc.gamerid.gateway.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Creator : Le Quang Dat
 * Email   : quangdat0993@gmail.com
 * Date    : Oct 11, 2018
 */
@Setter
@Getter
@NoArgsConstructor
public class SplayBusinessException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    protected String status;
    
    protected Object data;

    public SplayBusinessException(String status) {
        super();
        this.status = status;
    }
    
    public SplayBusinessException(String status,String message) {
        super(message);
        this.status = status;
    }
    
    public SplayBusinessException(String status, Object data, String message) {
        super(data + ": " + message);
        this.status = status;
    }

}
