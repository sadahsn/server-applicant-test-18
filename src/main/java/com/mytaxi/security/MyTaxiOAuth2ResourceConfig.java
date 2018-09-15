package com.mytaxi.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

/**
 * This class is responsible for Oauth2 resource configuration.
 */
@Configuration
@EnableResourceServer
public class MyTaxiOAuth2ResourceConfig extends ResourceServerConfigurerAdapter
{

    private static final String RESOURCE_ID = "my_rest_api";


    @Override
    public void configure(ResourceServerSecurityConfigurer resources)
    {
        resources.resourceId(RESOURCE_ID).stateless(false);
    }


    @Override
    public void configure(HttpSecurity http) throws Exception
    {

        http
            .anonymous().disable()
            .requestMatchers().antMatchers("/v1/**")
            .and().authorizeRequests()
            .antMatchers("/v1/**").authenticated()
            .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }

}
