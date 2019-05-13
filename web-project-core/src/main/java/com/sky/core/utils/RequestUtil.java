package com.sky.core.utils;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.commons.collections.MultiMap;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by ThinkPad on 2019/5/13.
 */
public class RequestUtil {

    public static String getIpAddrByRequest(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static Map<String, String> getParameters(HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Map<String, String> map = new HashMap<String, String>();
        int count = 0;
        int total = 200;

        Map<?, ?> params = request.getParameterMap();
        Iterator<?> it = params.keySet().iterator();
        String key = null;
        String value = null;
        while (it.hasNext() && count < total) {
            count++;
            key = (String) it.next();
            if ("submit".equals(key))
                continue;
            value = request.getParameter(key);
            map.put(key, value);
        }
        return map;
    }
}
