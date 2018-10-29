package com.sky.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ThinkPad on 2018/10/29.
 */
public class AccessInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 后台session控制
        String[] noFilters = new String[] { "/", "/login","/index" };
        String uri = request.getRequestURI();
        boolean just = true;
        for (String s : noFilters) {
            if (uri.indexOf(s) != -1) {
                just = false;
                break;
            }
            if(just){
                Object obj = request.getSession().getAttribute("systemUser");
                if (null == obj) {
                    response.sendRedirect("/");
                    return false;
                }
            }

        }
        return super.preHandle(request, response, handler);
    }
}
