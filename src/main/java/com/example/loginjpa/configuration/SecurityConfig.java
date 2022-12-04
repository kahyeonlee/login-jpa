package com.example.loginjpa.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.security.PermitAll;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .httpBasic().disable()// ui 쪽 disable
                .csrf().disable() //cross site
                .cors().and() // cross site 에서 도메인이 다를때
                .authorizeRequests()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/api/v1/users/join","api/v1/users/login").permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
//                .addFilterBefore(new JwtTokenFilter(userService,secretKey), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
