package com.vtc.gamerid.gateway.authenticate;

import com.vtc.gamerid.gateway.authenticate._interface.GGAuthentication;
import org.jboss.aerogear.security.otp.Totp;
import org.jboss.aerogear.security.otp.api.Base32;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by phucnguyen on 10/05/2017.
 */
@Component
public class GGAuthenticationImpl implements GGAuthentication {
    private Logger logger = LoggerFactory.getLogger(GGAuthenticationImpl.class);

    @Override
    public String gennerateSecretKey() {
        return Base32.random();
    }

    @Override
    public boolean checkOTP(String secretKey, String opt) {
        try{
            Totp totp = new Totp(secretKey);
            return totp.verify(opt);
        }catch (Exception e){
            logger.error(e.toString());
        }
        return false;
    }
}
