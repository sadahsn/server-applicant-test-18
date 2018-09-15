package com.mytaxi.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

/**
 * This class is responsible for Oauth2 authorization server configuration.
 */
@Configuration
//@Component
@EnableAuthorizationServer
public class MyTaxiOAuth2ServerConfig extends AuthorizationServerConfigurerAdapter
{

    private AuthenticationManager authenticationManager;


    public MyTaxiOAuth2ServerConfig(AuthenticationConfiguration authenticationConfiguration) throws Exception
    {
        this.authenticationManager =
            authenticationConfiguration.getAuthenticationManager();
    }


    @Override
    public void configure(
        ClientDetailsServiceConfigurer clients) throws Exception
    {
        clients.inMemory()
            .withClient("client")
            .authorizedGrantTypes("password")
            .secret("{noop}secret")
            .accessTokenValiditySeconds(600)//10 minutes expiration time
            .scopes("all");
    }


    @Override
    public void configure(
        AuthorizationServerEndpointsConfigurer endpoints) throws Exception
    {
        endpoints.authenticationManager(authenticationManager);
    }

}
