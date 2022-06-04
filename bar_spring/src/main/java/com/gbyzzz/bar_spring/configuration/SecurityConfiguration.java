package com.gbyzzz.bar_spring.configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Anton Pinchuk
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/index.html", "/", "/image/*", "/login","/cocktail/*","/ingredient/*").permitAll()
                .antMatchers(HttpMethod.POST, "/cocktail/all_pages").permitAll()
                .antMatchers(HttpMethod.POST, "/image/upload").permitAll()
                .anyRequest().authenticated();
        http.csrf().disable();


    }
}
