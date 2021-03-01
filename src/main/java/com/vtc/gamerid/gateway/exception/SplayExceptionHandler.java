/***************************************************************************
 * Product 2018 by Quang Dat * 
 **************************************************************************/
package com.vtc.gamerid.gateway.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.vtc.gamerid.gateway.common.dto.response.SplayResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * Creator : Le Quang Dat
 * Email   : quangdat0993@gmail.com
 * Date    : Oct 11, 2018
 */
@ControllerAdvice
@Slf4j
public class SplayExceptionHandler extends ResponseEntityExceptionHandler {
  
    /**
     * 
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(SplayBusinessException.class)
    public ResponseEntity<SplayResponse<String>> handleSplayBusinessException(final HttpServletRequest request,
                                                                     final Exception e) {
        log.info(e.getMessage(),e);
        SplayResponse<String> response = new SplayResponse<String>();
        response.setStatus(((SplayBusinessException)e).getStatus());
        response.setMessage(e.getMessage());
        return new ResponseEntity<SplayResponse<String>>(response, null, HttpStatus.OK);
    }
    
    /**
     * 
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(ScoinInvalidDataRequestException.class)
    public ResponseEntity<SplayResponse<String>> handleScoinInvalidDataRequestException(final HttpServletRequest request,
                                                                             final Exception e) {
        log.info(e.getMessage(),e);
        SplayResponse<String> response = new SplayResponse<String>();
        response.setStatus(((ScoinInvalidDataRequestException)e).getStatus());
        response.setMessage(e.getMessage());
        return new ResponseEntity<SplayResponse<String>>(response, null, HttpStatus.OK);
    }
    
    /**
     * 
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(ScoinNotFoundEntityException.class)
    public ResponseEntity<SplayResponse<String>> handleScoinNotFoundEntityException(final HttpServletRequest request,
                                                                     final Exception e) {
        log.info(e.getMessage(),e);
        SplayResponse<String> response = new SplayResponse<String>();
        response.setStatus(((ScoinNotFoundEntityException)e).getStatus());
        response.setMessage(e.getMessage());
        return new ResponseEntity<SplayResponse<String>>(response, null, HttpStatus.OK);
    }
    
    /**
     * 
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(ScoinUnAuthorizationException.class)
    public ResponseEntity<SplayResponse<String>> handleScoinUnAuthorizationException(final HttpServletRequest request,
                                                                     final Exception e) {
        log.info(e.getMessage(),e);
        SplayResponse<String> response = new SplayResponse<String>();
        response.setStatus(((ScoinUnAuthorizationException)e).getStatus());
        response.setMessage(e.getMessage());
        return new ResponseEntity<SplayResponse<String>>(response, null, HttpStatus.OK);
    }
    
    /**
     * 
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(FailedToExecuteException.class)
    public ResponseEntity<SplayResponse<String>> handleBluuFailedToExecuteException(final HttpServletRequest request,
                                                                     final Exception e) {
        log.info(e.getMessage(),e);
        SplayResponse<String> response = new SplayResponse<String>();
        response.setStatus(((FailedToExecuteException)e).getStatus());
        response.setMessage(e.getMessage());
        return new ResponseEntity<SplayResponse<String>>(response, null, HttpStatus.OK);
    }
    
    /**
     * 
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(ScoinDuplicateEntityException.class)
    public ResponseEntity<SplayResponse<String>> handleScoinDuplicateEntityException(final HttpServletRequest request,
                                                                     final Exception e) {
        log.info(e.getMessage(),e);
        SplayResponse<String> response = new SplayResponse<String>();
        response.setStatus(((ScoinDuplicateEntityException)e).getStatus());
        response.setMessage(e.getMessage());
        return new ResponseEntity<SplayResponse<String>>(response, null, HttpStatus.OK);
    }
    
    /**
     * 
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(UnknownErrorException.class)
    public ResponseEntity<SplayResponse<String>> handleUnknownErrorException(final HttpServletRequest request,
                                                                     final Exception e) {
        log.info(e.getMessage(),e);
        SplayResponse<String> response = new SplayResponse<String>();
        response.setStatus(((UnknownErrorException)e).getStatus());
        response.setMessage(e.getMessage());
        return new ResponseEntity<SplayResponse<String>>(response, null, HttpStatus.OK);
    }

}
