package com.sky.config;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Configuration
public class WebConfig implements WebMvcConfigurer {


    Map<String, String> map = new HashMap<>();


//    @Bean
//    public FilterRegistrationBean webFilterRegistration() {
//        FilterRegistrationBean systemRegistration = new FilterRegistrationBean(new WebFilter());
//
//        systemRegistration.setName("webFilter");
//        systemRegistration.addUrlPatterns("/*");
//        systemRegistration.setOrder(2);
//        return systemRegistration;
//    }
//
//    @Bean
//    public FilterRegistrationBean tokenFilterRegistration() {
//        FilterRegistrationBean systemRegistration = new FilterRegistrationBean(new TokenFilter());
//        systemRegistration.setName("tokenFilter");
//        systemRegistration.addUrlPatterns("/*");
//        systemRegistration.setOrder(3);
//        systemRegistration.setInitParameters(map);
//        return systemRegistration;
//    }


    @Override
    public void configurePathMatch(PathMatchConfigurer pathMatchConfigurer) {

    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer contentNegotiationConfigurer) {

    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer asyncSupportConfigurer) {

    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer defaultServletHandlerConfigurer) {

    }

    @Override
    public void addFormatters(FormatterRegistry formatterRegistry) {

    }

    /**
     * 配置拦截器
     *
     * @param registry
     * @author lance
     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//
//        registry.addInterceptor(getSysInterceptor()).addPathPatterns("/**")
//                .excludePathPatterns("/*.ico", "/*/api-docs", "/swagger**",
//                        "/swagger-resources/**",
//                        "/webjars/**",
//                        "/configuration/**",
//                        "/js/**",
//                        "/img/**",
//                        "/css/**",
//                        "/font/**",
//                        "/json/**",
//                        "/libs/**",
//                        "/resource/**");
//
//        registry.addInterceptor(getLogInterceptor()).addPathPatterns("/**")
//                .excludePathPatterns("/*.ico", "/*/api-docs", "/swagger**",
//                        "/swagger-resources/**",
//                        "/webjars/**",
//                        "/configuration/**",
//                        "/js/**",
//                        "/img/**",
//                        "/css/**",
//                        "/font/**",
//                        "/json/**",
//                        "/libs/**",
//                        "/resource/**");
//
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry) {

    }

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {

    }

    @Override
    public void addViewControllers(ViewControllerRegistry viewControllerRegistry) {

    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry viewResolverRegistry) {

    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> list) {

    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> list) {

    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> list) {

    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> list) {

    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> list) {

    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> list) {

    }

    @Override
    public Validator getValidator() {
        return null;
    }

    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        return null;
    }



}
