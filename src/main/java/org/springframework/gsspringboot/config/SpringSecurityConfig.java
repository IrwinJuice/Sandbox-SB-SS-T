package org.springframework.gsspringboot.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.gsspringboot.utils.ClientResources;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.Filter;

@EnableOAuth2Client
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
    private OAuth2ClientContext oauth2ClientContext;

    /*
    //AuthenticationManagerBuilder - позволяет легко создать аутентификацию, в пямяти, в JDBC, LDAP
    //inMemoryAuthentication - создает аутентификацию в памяти

    auth.inMemoryAuthentication()
        .withUser("user123").password("{noop}qwe").roles("ROLE_USER")
        .and()
        .withUser("templates/admin").password("{noop}password").roles("ROLE_ADMIN");
     */
    // Нам нужен префикс {noop} так как:
    // В spring-security-core:5.0.0.RC1 по умолчанию
    // PasswordEncoder построен как DelegatingPasswordEncoder.
    // Когда вы храните пароль пользователя в памяти,
    // вы храниете его в виде обычного текста и
    // а при введении пароля на сайте, он проходит шифрование через DelegatingPasswordEncoder
    // при попытке сравнить два пароля вылетит ошибка
    //https://spring.io/blog/2017/11/01/spring-security-5-0-0-rc1-released#password-encoding
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(cryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf()
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())  /*csrf = Cross Site Request Forgery (Межсайтовая подделка запроса)
                          .csrf().disable() - отключена*/
                    .and()
                .addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class)
               .authorizeRequests() //Позволяет ограничивать доступ на основе HttpServletRequest
                        .antMatchers("/", "/vinylShop", "/about", "/login**").permitAll()
                //.antMatchers - Позволяет настроить HttpSecurity, для вызова при совпадении паттернов
                //.permitAll() - доступно всем пользователям
                //   com/t?st.jsp - matches com/test.jsp but also com/tast.jsp or com/txst.jsp
                //   com/*.jsp - matches all .jsp files in the com directory
                //   com/**//*test.jsp - matches all test.jsp files underneath the com path*/
                //   org/springframework/**/*.jsp - matches all .jsp files underneath the org/springframework path
                //   org/**/servlet/bla.jsp - matches org/springframework/servlet/bla.jsp but
                //  also org/springframework/testing/servlet/bla.jsp and org/servlet/bla.jsp
                //   com/{filename:\\w+}.jsp will match com/test.jsp and assign the value test to the filename variable

                        .antMatchers("/admin/**").hasAnyRole("ROLE_ADMIN")
                //.hasAnyRole - доступно только пользователям с указаной ролью
                        .antMatchers("/user/**").hasAnyRole("ROLE_USER")
                /* .anyRequest().authenticated()*/ //Любые други запросы требуют аутентификации
               .and()
                   .formLogin() //определяет локацию страницы логина
                       .loginPage("/signin") // у Spring Security есть собственная страница по умолчанию
                       .permitAll(true)//гарантия того что все пользователи имеют доступ к этой странице
               .and()
                   .logout()//обеспечивает выход из системы (есть расширяющие свойства)
                 .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
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
    @Bean
    public FilterRegistrationBean<OAuth2ClientContextFilter> oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
        FilterRegistrationBean<OAuth2ClientContextFilter> registration = new FilterRegistrationBean<OAuth2ClientContextFilter>();
        registration.setFilter(filter);
        registration.setOrder(-100);
        return registration;
    }

    private Filter ssoFilter() {
        OAuth2ClientAuthenticationProcessingFilter facebookFilter = new OAuth2ClientAuthenticationProcessingFilter(
                "/login/facebook");
        OAuth2RestTemplate facebookTemplate = new OAuth2RestTemplate(facebook(), oauth2ClientContext);
        facebookFilter.setRestTemplate(facebookTemplate);
        UserInfoTokenServices tokenServices = new UserInfoTokenServices(facebookResource().getUserInfoUri(),
                                                                        facebook().getClientId());
        tokenServices.setRestTemplate(facebookTemplate);
        facebookFilter.setTokenServices(
                new UserInfoTokenServices(facebookResource().getUserInfoUri(), facebook().getClientId()));
        return facebookFilter;
    }

    @Bean
    @ConfigurationProperties("facebook.client")
    public AuthorizationCodeResourceDetails facebook() {
        return new AuthorizationCodeResourceDetails();
    }

    @Bean
    @ConfigurationProperties("facebook.resource")
    public ResourceServerProperties facebookResource() {
        return new ResourceServerProperties();
    }
}
