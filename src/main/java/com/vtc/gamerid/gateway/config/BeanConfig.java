package com.vtc.gamerid.gateway.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import com.vtc.gamerid.gateway.common.Constants;

import java.util.Properties;

/**
 * Created by phucnguyen on 09/03/2017.
 */
@Configuration
@ComponentScan
@EnableTransactionManagement
@EnableAutoConfiguration
public class BeanConfig {
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {

        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                ErrorPage errorPage400 = new ErrorPage(HttpStatus.BAD_REQUEST,Constants.ERROR_PATH);
                ErrorPage errorPage401 = new ErrorPage(HttpStatus.UNAUTHORIZED,Constants.ERROR_PATH);
                ErrorPage errorPage403 = new ErrorPage(HttpStatus.FORBIDDEN,Constants.ERROR_PATH);
                ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND,Constants.ERROR_PATH);
                ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,Constants.ERROR_PATH);
                ErrorPage errorPage502 = new ErrorPage(HttpStatus.BAD_GATEWAY,Constants.ERROR_PATH);
                ErrorPage errorPage504 = new ErrorPage(HttpStatus.GATEWAY_TIMEOUT,Constants.ERROR_PATH);
                ErrorPage errorPage505 = new ErrorPage(HttpStatus.HTTP_VERSION_NOT_SUPPORTED,Constants.ERROR_PATH);
                ErrorPage errorPageDefault = new ErrorPage(Constants.ERROR_PATH);

                container.addErrorPages(errorPage400, errorPage401, errorPage403,
                        errorPage404, errorPage500, errorPage502, errorPage504, errorPage505,
                        errorPageDefault);
            }
        };
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(clientHttpRequestFactory());
    }

    private ClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(20000);
        factory.setConnectTimeout(5000);
        return factory;
    }
}
