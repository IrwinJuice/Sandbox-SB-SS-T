package org.springframework.gsspringboot.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.gsspringboot.service.UserDetailsServiceImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;


@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private BCryptPasswordEncoder cryptPasswordEncoder;

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired

    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(cryptPasswordEncoder);
        //AuthenticationManagerBuilder - позволяет легко создать аутентификацию, в пямяти, в JDBC, LDAP

        /*auth.inMemoryAuthentication()*/
                //inMemoryAuthentication - создает аутентификацию в памяти

               /* .withUser("user123").password("{noop}qwe").roles("USER")*/

                // Нам нужен префикс {noop} так как:
                // В spring-security-core:5.0.0.RC1 по умолчанию
                // PasswordEncoder построен как DelegatingPasswordEncoder.
                // Когда вы храните пароль пользователя в памяти,
                // вы храниете его в виде обычного текста и
                // а при введении пароля на сайте, он проходит шифрование через DelegatingPasswordEncoder
                // при попытке сравнить два пароля вылетит ошибка
                //https://spring.io/blog/2017/11/01/spring-security-5-0-0-rc1-released#password-encoding

                /*************
                .and()
                .withUser("templates/admin").password("{noop}password").roles("ADMIN");*/
    }

    // roles admin allow to access /admin/**
    // roles user allow to access /user/**
    // custom 403 access denied handler
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()  /*csrf = Cross Site Request Forgery (Межсайтовая подделка запроса)
                                    .csrf().disable() - отключена*/
                .authorizeRequests() //Позволяет ограничивать доступ на основе HttpServletRequest
                    .antMatchers("/", "/vinylShop", "/completeMP", "/about").permitAll()
                    //.antMatchers - Позволяет настроить HttpSecurity, для вызова при совпадении паттернов
                    //.permitAll() - доступно всем пользователям
                        //   com/t?st.jsp - matches com/test.jsp but also com/tast.jsp or com/txst.jsp
                        //   com/*.jsp - matches all .jsp files in the com directory
                        //   com/**//*test.jsp - matches all test.jsp files underneath the com path*/
                        //   org/springframework/**/*.jsp - matches all .jsp files underneath the org/springframework path
                        //   org/**/servlet/bla.jsp - matches org/springframework/servlet/bla.jsp but
                        //  also org/springframework/testing/servlet/bla.jsp and org/servlet/bla.jsp
                        //   com/{filename:\\w+}.jsp will match com/test.jsp and assign the value test to the filename variable

                .antMatchers("/completeAP").hasAnyRole("ROLE_ADMIN")
                    //.hasAnyRole - доступно только пользователям с указаной ролью

                   /* .antMatchers("/user/**").hasAnyRole("ROLE_USER")*/

                    .anyRequest().authenticated() //Любые други запросы требуют аутентификации
                .and()
                .formLogin() //определяет локацию страницы логина
                    .loginPage("/loginPage") // у Spring Security есть собственная страница по умолчанию
                    .permitAll(true) //гарантия того что все пользователи имеют доступ к этой странице
                .and()
                .logout()//обеспечивает выход из системы (есть расширяющие свойства)
                  //  .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/")
                    .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
                //.exceptionHandling - позволяет настраивать обработку исключений
                //.accessDeniedHandler - кастомная страница ошибки доступа
    }

    @Override
    public void configure(WebSecurity web){
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        cryptPasswordEncoder = new BCryptPasswordEncoder();
        return cryptPasswordEncoder;
    }
}
