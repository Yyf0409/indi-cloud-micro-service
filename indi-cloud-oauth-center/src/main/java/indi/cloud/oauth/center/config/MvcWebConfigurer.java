package indi.cloud.oauth.center.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcWebConfigurer implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/home").setViewName("home");
        //自定义的登陆页面
//          registry.addViewController("/login2").setViewName("login");
//        registry.addViewController("/oauth/confirm_access").setViewName("oauth_approval"); //自定义的授权页面
//        registry.addViewController("/oauth_error").setViewName("oauth_error");
    }
}