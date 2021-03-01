/***************************************************************************
 * Product 2018 by Quang Dat * 
 **************************************************************************/
package com.vtc.gamerid.gateway.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.vtc.gamerid.gateway.common.StatusCode;
import com.vtc.gamerid.gateway.common.dto.response.SplayResponse;
import com.vtc.gamerid.gateway.common.ultils.JsonMapperUtils;

/**
 * Creator : Le Quang Dat
 * Email   : quangdat0993@gmail.com
 * Date    : Sep 19, 2018
 */
public class AbstractController<S> {
    
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    protected S service;
    
    protected <T> SplayResponse<T> toResult(T t) {
        SplayResponse<T> response = new SplayResponse<T>();
        response.setStatus(StatusCode.SUCCESS);
        response.setMessage(StatusCode.SUCCESS_DESCRIPTION);
        response.setData(t);
        response.setTotalRecords(0);
        logger.info("\n================[RESPOSE API] ================= {}", JsonMapperUtils.toJson(response));
//        logger.info("\n================[RESPOSE API] ================= {}", response.getStatus());
        return response;
    }
    
    protected <T> SplayResponse<T> toResult(T t, int totalRecords) {
        SplayResponse<T> response = new SplayResponse<T>();
        response.setStatus(StatusCode.SUCCESS);
        response.setMessage(StatusCode.SUCCESS_DESCRIPTION);
        response.setData(t);
        response.setTotalRecords(totalRecords);
        logger.info("\n================[RESPOSE API] ================= {}", JsonMapperUtils.toJson(response));
//        logger.info("\n================[RESPOSE API] ================= {}", response.getStatus());
        return response;
    }
    
    protected <T> ResponseEntity<SplayResponse<T>> response(SplayResponse<T> response){
        if(ObjectUtils.isEmpty(response)) {
            throw new IllegalArgumentException("Please set response");
        }
        
        if (StringUtils.isEmpty(response.getStatus())) response.setStatus(StatusCode.SUCCESS);
        
        return new ResponseEntity<SplayResponse<T>>(response, HttpStatus.OK);
    }

}
