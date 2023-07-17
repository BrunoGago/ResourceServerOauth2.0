package com.appsdeveloperblog.ws.api.ResourceServer.configurations.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        //Configure Http Security
        httpSecurity.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/users/status/check")
                .hasAuthority("SCOPE_profile")
                .anyRequest().authenticated()
                .and()
                .oauth2ResourceServer().jwt();
    }
}
