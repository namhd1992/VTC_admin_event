/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.ultils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.vtc.gamerid.gateway.exception.UnknownErrorException;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Apr 10, 2019
 */
public class CallToOtherServerUtils {
    private static Logger logger = LoggerFactory.getLogger(CallToOtherServerUtils.class);
    
    public static <R> Object get(String url, R response) {
        Class<R> type = null;
        RestTemplate restTemplate = new RestTemplate();
        try {
            response = restTemplate.getForObject(url, type);
        } catch (HttpClientErrorException e) {
            logger.info("" + e.getStatusCode());
            logger.info("" + e.getResponseBodyAsString());
            throw new UnknownErrorException("99","CallToOtherServerUtils Falue");
        }
        return response;
    }

}
