package com.appsdeveloperblog.ws.api.ResourceServer.configurations.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;


//@Secured
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)//enable the add secure anotation above the methods and classes
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {



    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());

        //Configure Http Security
        //Obs: User role can be defined in any of the methods above
        httpSecurity.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/users/status/check")
                //.hasAuthority("SCOPE_profile")
                .hasAnyRole("developer")
                //.hasAnyAuthority("ROLE_developer")
                //.hasAnyRole("developer", "user)
                .anyRequest().authenticated()
                .and()
                .oauth2ResourceServer().jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter);
    }
}
