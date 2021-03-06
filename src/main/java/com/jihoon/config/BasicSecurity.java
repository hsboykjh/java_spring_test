package com.jihoon.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
public class BasicSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private ApplicationProperties applicationProperties;

    /**
     * Set configuration BASIC AUTHENTICATION.
     * ID , PASSWORD , ROLE info is in the applicationProperties (application.properties).
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(applicationProperties.getAuthId())
                .password(applicationProperties.getAuthPassword())
                .roles(applicationProperties.getAuthRole());
    }

    /**
     * Set configuration http AUTHENTICATION.
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().fullyAuthenticated()
                .and().httpBasic()
                .and().csrf().disable();
    }
}