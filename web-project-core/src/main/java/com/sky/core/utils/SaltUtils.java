package com.sky.core.utils;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.apache.shiro.crypto.hash.Md5Hash;


/**
 * 获取加密后密码的工具类
 * Created by Administrator on 2016/12/20.
 */
public class SaltUtils {

    private static final Log logger = LogFactory.get();

    /**
     * 统一使用盐为hcdboa
     */
    private static String salt = "economy";

    /**
     * 获取加密后字符串的方法
     *
     * @param password 原始密码
     * @return 加密后密码
     */
    public static String getMD5Password(String password) {
        if (StringUtils.isEmpty(password)) {
            return null;
        }
        String temp = new Md5Hash(password, salt, 1024).toHex();
        return temp;
    }

}
