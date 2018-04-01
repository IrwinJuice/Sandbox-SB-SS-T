package org.springframework.gsspringboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    // roles admin allow to access /admin/**
    // roles user allow to access /user/**
    // custom 403 access denied handler
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        // csrf = Cross Site Request Forgery (Межсайтовая подделка запроса)
        http
                .csrf().disable()  /*csrf = Cross Site Request Forgery (Межсайтовая подделка запроса)
                                    .csrf().disable() - отключена*/
                .authorizeRequests() //Позволяет ограничивать доступ на основе HttpServletRequest
                    .antMatchers("/", "/home", "/about").permitAll()
                    //.antMatchers - Позволяет настроить HttpSecurity, для вызова при совпадении паттернов
                    //.permitAll() - доступно всем пользователям

    }
}
