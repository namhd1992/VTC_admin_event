package com.vtc.gamerid.gateway.errors;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vtc.gamerid.gateway.common.Constants;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by phucnguyen on 09/03/2017.
 */
@RestController
public class ErrorController {
    public static final Logger logger = Logger.getLogger(ErrorController.class);

    @RequestMapping(value = Constants.ERROR_PATH)
    public ResponseEntity<?> renderErrorPage(final HttpServletRequest request) {
        final int error_code = getHttpStatusCode(request);
        logger.error("***" + error_code + "*** " + "from ip : " + request.getRemoteAddr());
        return returnByStatus(error_code);
    }

    private int getHttpStatusCode(final HttpServletRequest request) {
        return (int) request.getAttribute("javax.servlet.error.status_code");
    }

    private ResponseEntity<?> returnByStatus(int statusCode) {
        switch (statusCode) {
            case 400:
                return new ResponseEntity<>("BAD_REQUEST", HttpStatus.BAD_REQUEST);
            case 401:
                return new ResponseEntity<>("UNAUTHORIZED", HttpStatus.UNAUTHORIZED);
            case 403:
                return new ResponseEntity<>("FORBIDDEN", HttpStatus.FORBIDDEN);
            case 404:
                return new ResponseEntity<>("NOT_FOUND", HttpStatus.NOT_FOUND);
            case 405:
                return new ResponseEntity<>("METHOD_NOT_ALLOWED", HttpStatus.METHOD_NOT_ALLOWED);
            case 500:
                return new ResponseEntity<>("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
            case 502:
                return new ResponseEntity<>("BAD_GATEWAY", HttpStatus.BAD_GATEWAY);
            case 504:
                return new ResponseEntity<>("GATEWAY_TIMEOUT", HttpStatus.GATEWAY_TIMEOUT);
            case 505:
                return new ResponseEntity<>("HTTP_VERSION_NOT_SUPPORTED", HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
            default:
                return new ResponseEntity<>("SERVICE_UNAVAILABLE", HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
