package com.sky.tools;

import com.sky.core.consts.SystemConst;
import com.sky.model.SystemUser;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by ThinkPad on 2019/5/18.
 */
public class UserUtil {

    public static SystemUser getCurrentUser(){
        return (SystemUser) getSession().getAttribute(SystemConst.SYSTEMUSER);
    }

    private static HttpSession getSession() {
        HttpSession session = null;
        try {
            session = getRequest().getSession();
        } catch (Exception e) {
        }
        return session;
    }

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attrs.getRequest();
    }
}
