package com.sky.interceptor;


import com.sky.interceptor.AccessInterceptor;
import com.sky.interceptor.LogInterceptor;
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
                .excludePathPatterns("/" , "/api/user/login" ,"/libs/**" ,"/js/**" ,"/css/**");

//        registry.addInterceptor(logInterceptor()).addPathPatterns("/api/**").excludePathPatterns("/api/menu/getMenuList");
    }

//    @Bean
//    LogInterceptor logInterceptor(){
//        return new LogInterceptor();
//    }
}
