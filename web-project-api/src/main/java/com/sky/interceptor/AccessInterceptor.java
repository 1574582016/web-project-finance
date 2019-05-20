package com.sky.interceptor;

import com.sky.core.consts.SystemConst;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ThinkPad on 2018/10/29.
 */
public class AccessInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object obj = request.getSession().getAttribute(SystemConst.SYSTEMUSER);
        if (null == obj) {
            response.sendRedirect("/");
            return false;
        }
        return super.preHandle(request, response, handler);
    }
}
