package com.sky.core.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.sky.core.json.JsonResult;

import org.jboss.logging.Logger;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lxl on 2018/9/20.
 */
public class BaseController {

    protected Logger log = Logger.getLogger(this.getClass());

    /**
     * 分页默认页码
     */
    protected static final String PAGE_NUM = "1";
    /**
     * 分页默认每页显示数据条数
     */
    protected static final String PAGE_SIZE = "10";


    public HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        return request;
    }

    public HttpServletResponse getResponse() {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getResponse();
        return response;
    }

    public HttpSession getSession() {
        HttpSession session = this.getRequest().getSession();
        return session;
    }

    public ServletContext getServletContent() {
        // ServletContext application= request.getServletContext();

        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        ServletContext servletContext = webApplicationContext.getServletContext();
        return servletContext;
    }

    public ModelAndView getModelAndView() {
        return new ModelAndView();
    }

    public ModelAndView get404ModelAndView() {
        ModelAndView view = new ModelAndView();
        view.setViewName("404");
        return view;
    }

    public String getRemortIP() {
        HttpServletRequest request = this.getRequest();
        String ip = "";
        if (request.getHeader("x-forwarded-for") == null) {
            ip = request.getRemoteAddr();
        } else {
            ip = request.getHeader("x-forwarded-for");
        }
        return ip;
    }

    public int getPort() {
        return this.getRequest().getServerPort();
    }

    public String getIpAddr() {
        HttpServletRequest request = this.getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


    public JsonResult MapError() {
        return new JsonResult(false, 500, null, null);
    }


    public JsonResult MapError(String msg) {
        return new JsonResult(false, 500, msg, null);
    }


    public JsonResult MapSuccess() {
        return new JsonResult(true, 200, null , null);
    }


    public JsonResult MapSuccess(String msg) {
        JsonResult result = MapSuccess();
        result.setMessage(msg);
        return result;
    }

    public JsonResult MapSuccess(String msg, Object obj) {
        Map map = new HashMap<String ,Object>();
        map.put("result",obj);
        return new JsonResult(true, 200, msg, map);
    }

    public JsonResult MapSuccess(String msg, Map<String ,Object> obj) {
        return new JsonResult(true, 200, msg, obj);
    }

    public JsonResult MapSuccess(Integer code, String msg, Object obj) {
        Map map = new HashMap<String ,Object>();
        map.put("result",obj);
        return new JsonResult(true, code, msg,map);
    }

    public JsonResult MapSuccess(Integer code, String msg, Map<String ,Object> obj) {
        return new JsonResult(true, code, msg,obj);
    }

    public Map<String ,Object> PageData(Page page){
        Map<String ,Object> map = new HashMap<String ,Object>();
        map.put("total",page.getTotal());
        map.put("rows",page.getRecords());
        return map;
    }
}
