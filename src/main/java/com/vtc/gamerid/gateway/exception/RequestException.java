package com.vtc.gamerid.gateway.exception;

import org.apache.log4j.Logger;

/**
 * Created by phucnguyen on 08/03/2017.
 */
public class RequestException extends Exception {
    private String statusCode;
    private String message;
    private boolean status;
    private int _code;
    private Object data;
    private static final Logger logger = Logger.getLogger(RequestException.class);

    public RequestException(String messageException, String statusCode, String messageResponse) {
        super(messageException);
        this.statusCode = statusCode;
        this.message = messageResponse;
        logger.info("*** Request exception *** : [" + statusCode + "] - "+ messageResponse);
    }

    public RequestException(boolean status, String messageException) {
        super(messageException);
        this.status = status;
        this.message = messageException;
        logger.info("*** Request exception *** : [" + status + "] - "+ messageException);
    }

    public RequestException(String messageException, String statusCode) {
        super(messageException);
        this.statusCode = statusCode;
        this.message = messageException;
        logger.info("*** Request exception *** : [" + statusCode + "] - "+ messageException);
    }

    public RequestException(String messageException, String statusCode, int _code) {
        super(messageException);
        this.statusCode = statusCode;
        this.message = messageException;
        this._code = _code;
        logger.info("*** Request exception *** : [" + _code + "] - "+ messageException);
    }

    public RequestException(String messageException, String statusCode, String messageResponse, Object data) {
        super(messageException);
        this.statusCode = statusCode;
        this.message = messageResponse;
        this.data = data;
    }

    public int get_code() {
        return _code;
    }

    public void set_code(int _code) {
        this._code = _code;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
