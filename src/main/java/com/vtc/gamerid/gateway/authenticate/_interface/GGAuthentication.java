package com.vtc.gamerid.gateway.authenticate._interface;

/**
 * Created by phucnguyen on 10/05/2017.
 */
public interface GGAuthentication {
    public String gennerateSecretKey();
    public boolean checkOTP(String secretKey, String opt);
}
