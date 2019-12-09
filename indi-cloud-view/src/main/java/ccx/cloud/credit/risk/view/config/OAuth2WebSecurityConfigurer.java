package ccx.cloud.credit.risk.view.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class OAuth2WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

//    private ClientCredentialsResourceDetails clientResource(){
//
//        ClientCredentialsResourceDetails r = new  ClientCredentialsResourceDetails();
//        r.setClientId("files-center");
//        r.setClientSecret("secret");
//        List scope = new ArrayList();
//        scope.add("all");
//        r.setScope(scope);
//
//        return r;
//    }
//
//    @Bean
//    public OAuth2RestTemplate oAuth2RestTemplate() {
//        return new OAuth2RestTemplate(clientResource());
//    }

    @Value("${spring.security.oauth2.client.provider.auth-center.default-success-url}")
    private String defaultSuccessUrl;

    @Override
    public void configure(WebSecurity web){
        web.ignoring().antMatchers("/favicon.ico","/static/**","/welcome","/product_des","/about_us");
    }

    @Value("${spring.security.oauth2.client.provider.auth-center.logout-uri}")
    private String logoutUri;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers(
                        "/",
                        "/index",
                        "/welcome",
                        "/product_des",
                        "/about_us",
                        "/getFile",
                        "/giveFile").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()      // (1)

//                .loginPage("http://127.0.0.1:20001/login")
                .defaultSuccessUrl(defaultSuccessUrl)
                .and()
                .logout()           // (2)
                .logoutSuccessUrl(logoutUri)
//                .logoutSuccessUrl("/")
//                .logoutUrl("http://127.0.0.1:20001/logout")
                .and()
                .csrf().disable();  // (3)
        http.headers().frameOptions().disable();
    }
}
