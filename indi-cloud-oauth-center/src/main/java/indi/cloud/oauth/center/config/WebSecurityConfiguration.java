package indi.cloud.oauth.center.config;

import indi.cloud.oauth.center.filter.SimpleCORSFilter;
import indi.cloud.oauth.center.handler.CustomLoginSuccessHandler;
import indi.cloud.oauth.center.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private SimpleCORSFilter simpleCORSFilter;

    @Resource(name = "MyUserDetailsService")
    private MyUserDetailsService myUserDetailsService;


    //    @Autowired
//    private MyAuthenticationSuccessHandler myAuthenticationSuccess;
    @Autowired
    private MyAuthenticationProvider myAuthenticationProvider;
//    @Autowired
//    private MyAuthenticationFailHandler myAuthenticationFailHandler;
//    @Autowired
//    private MyUserDetailService myUserDetailService;
//    @Autowired
//    private HikariDataSource dataSource;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService)
                .passwordEncoder(bCryptPasswordEncoder());
        auth.parentAuthenticationManager(authenticationManagerBean());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(myAuthenticationProvider);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/assets/**");
        web.ignoring().antMatchers("/favicon.ico");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .requestMatchers()
                .antMatchers("/", "/api/**", "/oauth/**", "/login", "login_page")
                ;

        http
                .authorizeRequests()
                .antMatchers("/api/**").authenticated()
                .anyRequest().authenticated();

        http
                .formLogin()
                .loginPage("/login_page")
                .loginProcessingUrl("/login")
//                .defaultSuccessUrl("/")
                 .successHandler(new CustomLoginSuccessHandler())
                //.failureHandler(new CustomLoginFailHandler())
                .permitAll();



        http.csrf().disable();

        System.out.println( "isCustomer : " +   http.formLogin().isCustomLoginPage());

        http.addFilterBefore(simpleCORSFilter, SecurityContextPersistenceFilter.class);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}