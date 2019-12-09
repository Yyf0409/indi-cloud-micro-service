package indi.cloud.view.config;

import indi.cloud.view.filter.UserInfoFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class FilterConfigurer {

    @Bean
    public FilterRegistrationBean myFilterBean(UserInfoFilter filter) {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(filter);//设置为自定义的过滤器MyFilter
        filterRegistrationBean.addUrlPatterns("/*");//拦截所有请求
        filterRegistrationBean.setOrder(Integer.MAX_VALUE);//优先级为1
        return filterRegistrationBean;
    }
}
