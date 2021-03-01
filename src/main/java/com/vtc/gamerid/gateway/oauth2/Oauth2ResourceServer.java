package com.vtc.gamerid.gateway.oauth2;

import static com.vtc.gamerid.gateway.common.Constants.RESOURCE_ID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import com.vtc.gamerid.gateway.common.Constants;

/**
 * Created by phucnguyen on 02/03/2017.
 */
@Configuration
@EnableResourceServer
public class Oauth2ResourceServer extends ResourceServerConfigurerAdapter {

    /*@Autowired
    private CorsFilter corsFilter;*/
    @Autowired
    private TokenStore tokenStore;

    @Override // [3]
    public void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/ping").permitAll()
                .antMatchers("/webapp/**").permitAll()
                .antMatchers("/lgi").permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/allGame").permitAll()
                .antMatchers("/testGiftcode").permitAll()
                .antMatchers("/redirectArticle").permitAll()
                .antMatchers("/redirectSourcePage").permitAll()
                .antMatchers("/getArticles").permitAll()
                .antMatchers("/channelNoPermission").permitAll()
                .antMatchers("/anonymous/**").permitAll()
                .antMatchers("/scoin/**").permitAll()
                .antMatchers("/splay/**").permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/.well-known/**").permitAll()
//                .antMatchers("/bettings/games").permitAll()

                .antMatchers(Constants.ERROR_PATH).permitAll()
                .anyRequest()
                .authenticated();
        // @formatter:on
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        System.out.println("Configuring ResourceServerSecurityConfigurer ");
        resources.resourceId(RESOURCE_ID)
        .tokenStore(tokenStore);
    }

}
