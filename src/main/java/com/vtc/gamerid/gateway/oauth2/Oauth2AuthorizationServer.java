package com.vtc.gamerid.gateway.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import com.vtc.gamerid.gateway.common.Constants;

/**
 * Created by phucnguyen on 02/03/2017.
 */
@Configuration
@EnableAuthorizationServer
public class Oauth2AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(
            AuthorizationServerSecurityConfigurer oauthServer)
            throws Exception {
        oauthServer
//                .allowFormAuthenticationForClients()
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients)
            throws Exception {
        clients.inMemory()
                .withClient(Constants.CLIENT_ID)
                .secret(Constants.CLIENT_SECRET)
                .authorizedGrantTypes(
                        "password")
                .scopes("read")
                .accessTokenValiditySeconds(60*60*48);
    }

    @Override
    public void configure(
            AuthorizationServerEndpointsConfigurer endpoints)
            throws Exception {

        endpoints
        .tokenStore(tokenStore()).tokenEnhancer(tokenEnhancer())
                .pathMapping("/oauth/token", "/lgi")
                .authenticationManager(authenticationManager);
    }

    /**
     * Add a field in token
     * */
    @Autowired
    private ClientDetailsService clientDetailsService;

    @Bean
    public JwtTokenStore tokenStore() {
        JwtTokenStore store = new JwtTokenStore(tokenEnhancer());
        return store;
    }


    @Bean
    public JwtAccessTokenConverter tokenEnhancer(){
        /*KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("mytest.jks"), "vtcmobile".toCharArray());
        JwtAccessTokenConverter converter = new MyTokenEnhancer();
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("mytest"));
        return converter;*/
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("my-key.jks"), "vtcmobile".toCharArray());
        JwtAccessTokenConverter converter = new MyTokenEnhancer();
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("keyAlias"));
        return converter;
    }

    /*@Bean
    public DefaultTokenServices defaultTokenServices(){
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setClientDetailsService(clientDetailsService);
        defaultTokenServices.setTokenEnhancer(tokenEnhancerChain());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

    @Bean
    public TokenEnhancerChain tokenEnhancerChain(){
        final TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> tokenEnhancers = new ArrayList<>();
        tokenEnhancers.add(new MyTokenEnhancer());
        tokenEnhancers.add(tokenEnhancer());
        tokenEnhancerChain.setTokenEnhancers(tokenEnhancers);
        return tokenEnhancerChain;
    }*/
}