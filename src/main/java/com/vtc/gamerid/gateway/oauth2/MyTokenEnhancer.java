package com.vtc.gamerid.gateway.oauth2;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * Created by phucnguyen on 14/03/2017.
 */
public class MyTokenEnhancer extends JwtAccessTokenConverter {
    private final Logger logger = LoggerFactory.getLogger(MyTokenEnhancer.class);
    @SuppressWarnings("unchecked")
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        final Map<String, Object> additionalInfo = new HashMap<>();
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            additionalInfo.put("statusCode", "T");
            try{
                Map<String, Object> authenDetail = (Map<String, Object>) authentication.getUserAuthentication().getPrincipal();
                additionalInfo.put("data", authenDetail);
            }catch (Exception e){
                logger.error(e.toString());
            }

        }else{
            additionalInfo.put("statusCode", "F");
        }

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return super.enhance(accessToken,authentication);
    }
}