package com.sky.annotation;


import com.alibaba.fastjson.JSONObject;
import com.sky.core.utils.RequestUtil;
import com.sky.model.SystemUser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamSource;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.lang.reflect.Method;

/**
 * Created by ThinkPad on 2019/5/13.
 */
@Aspect
@Component
public class LogRecordAspect {

    public static final Logger logger = LoggerFactory.getLogger(LogRecordAspect.class);
    // 执行最大时间 超过该时间则警告
    private static final int DEFAULT_TIME_LIMIT = 3000;
    private static final String MSG = "--基本信息--\n --路径:{}\n --IP:{}\n --用户:{}\n --方法：{}\n --描述：{}\n --位置：{}\n --参数：{}\n --返回：{}\n --耗时：{} ms";

    // 切点`
    @Pointcut("@annotation(com.sky.annotation.LogRecord)")
    public void executePointCut() {

    }


    // around 切面强化
    @Around("executePointCut()")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        if (logger.isDebugEnabled() || logger.isWarnEnabled()) {
            StopWatch clock = new StopWatch();
            clock.start();
            Object retrunobj = null;
            try {
                // 注意和finally中的执行顺序 finally是在return中的计算结束返回前执行
                return retrunobj = joinPoint.proceed(args);
            } catch (Exception e) {
                throw e;
            } finally {
                clock.stop();
                long totalTime = clock.getTotalTimeMillis();
                // 打印日志
                handleLog(joinPoint, args, retrunobj, totalTime);
            }
        } else {
            return joinPoint.proceed(args);
        }


    }



    /**
     * 日志处理
     *
     * @param joinPoint 位置
     * @param args      参数
     * @param retrunobj 响应
     * @param totalTime  耗时ms
     */
    private void handleLog(ProceedingJoinPoint joinPoint, Object[] args, Object retrunobj, long totalTime) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        LogRecord invokeLog = method.getAnnotation(LogRecord.class);
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        String urlPath = urlPathHelper.getLookupPathForRequest(request);
        String userName = "";
        try {
            SystemUser sysUser = (SystemUser) request.getSession().getAttribute("systemUser");
            if (sysUser != null) {
                userName = sysUser.getUserName();
            }
        } catch (Exception e) {

        }
        printLogMsg(urlPath ,RequestUtil.getIpAddrByRequest(request) ,userName , invokeLog.name(), invokeLog.description(), invokeLog.printReturn(), joinPoint, RequestUtil.getParameters(request).toString(), retrunobj, totalTime);

    }

    /**
     * @param name            操作名称
     * @param description     描述
     * @param printReturn     是否打印响应
     * @param joinPoint       位置
     * @param args            参数
     * @param returnObj       响应
     * @param totalTimeMillis 耗时ms
     */
    protected void printLogMsg(String url, String ip, String operator, String name, String description, boolean printReturn, JoinPoint joinPoint, String params, Object returnObj, long totalTimeMillis) {
//        Object[] params = argsDemote(args);
        if (totalTimeMillis < DEFAULT_TIME_LIMIT)
            logger.info(MSG, new Object[]{url, ip, operator, name, description, joinPoint.getStaticPart(), params, getPrintMsg(printReturn, returnObj), totalTimeMillis});
        else
            logger.warn(MSG, new Object[]{url, ip, operator, name, description, joinPoint.getStaticPart(), params, getPrintMsg(printReturn, returnObj), totalTimeMillis});
    }



    private String getPrintMsg(boolean printReturn, Object returnObj) {
        return printReturn ? ((returnObj != null) ? JSONObject.toJSONString(returnObj) : "null") : "[printReturn = false]";
    }

    private Object[] argsDemote(Object[] args) {
        if (args == null || args.length == 0) {
            return new Object[]{};
        }
        Object[] params = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            System.out.println("=========================");
            System.out.println(arg.toString());
            if (arg instanceof ServletRequest || arg instanceof ServletResponse || arg instanceof ModelMap || arg instanceof Model || arg instanceof InputStreamSource || arg instanceof File) {
                params[i] = args.toString();
            } else {
                params[i] = args[i];
            }
        }
        return params;
    }



}
