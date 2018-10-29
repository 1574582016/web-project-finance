package com.sky.config;


import com.sky.interceptor.AccessInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.*;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public HandlerInterceptor getSysInterceptor() {
        return new AccessInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getSysInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/*.ico", "/*/api-docs", "/swagger**",
                        "/swagger-resources/**",
                        "/webjars/**",
                        "/configuration/**",
                        "/js/**",
                        "/img/**",
                        "/css/**",
                        "/font/**",
                        "/json/**",
                        "/libs/**",
                        "/resource/**");
    }
}
