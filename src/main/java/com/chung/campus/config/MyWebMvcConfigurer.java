package com.chung.campus.config;

import com.chung.campus.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    @Bean
    public LoginInterceptor getLoginInterceptor(){
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePaths = new ArrayList<>();
        excludePaths.add("/css/**");
        excludePaths.add("/bootstrap-3.3.7-dist/**");
        excludePaths.add("/js/**");
        excludePaths.add("/login");
        excludePaths.add("/Wx**");
        excludePaths.add("/icon/**");
        excludePaths.add("/index.html");
        excludePaths.add("/");
        registry.addInterceptor(getLoginInterceptor()).addPathPatterns("/**").excludePathPatterns(excludePaths);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
    }
}
