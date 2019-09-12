/**
 * @Title: StringUtils.java
 * @Package com.gtrx.utils
 * @Description: TODO(用一句话描述该文件做什么)
 * @author mengqch
 * @date 2015年1月16日 下午1:15:24
 * @version V1.0
 */
package com.sky.core.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author mengqch
 * @ClassName: StringUtils
 * @Description: 字符串工具类
 */
public class StringUtils {

    static Logger logger = LoggerFactory.getLogger(StringUtils.class);

    public static String[] chars = new String[]{
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
    };

    /**
     * @throws
     * @Title: StringUtils
     * @param: @return
     * @return: String
     * @Creator: mqc
     * @Description: 根据UUID生成8位长度的唯一标识码
     * @Date: 2016-8-10 上午10:33:49
     * @Modifier:
     * @Date:
     * @Remark:
     * @Version: V1.0
     */
    public static String generateShortUuid() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();

    }

    /**
     * 对字符串进行加密处理
     *
     * @param value
     * @return 加密后的字符串
     */
    public static String hexMD5(String value) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(value.getBytes("utf-8"));
            byte[] digest = messageDigest.digest();
            return byteToHexString(digest);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * @param @param bytes
     * @return String
     * @throws
     * @Title: byteToHexString
     * @Description: 将字节数组转化为String字符串
     */
    public static String byteToHexString(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < bytes.length; ++i) {
            int v = bytes[i];
            if (v < 0) {
                v += 256;
            }
            String n = Integer.toHexString(v);
            if (n.length() == 1)
                n = "0" + n;
            builder.append(n);
        }
        return builder.toString();
    }

    /**
     * @param @param  characters
     * @param @return
     * @return boolean
     * @throws
     * @Title: isNull
     * @Description: 判断字符串是否为空
     */
    public static boolean isNull(String characters) {
        if (null == characters || "".equals(characters)) {
            return true;
        }
        return false;
    }

    public static boolean isNotNull(String value) {
        if (value == null) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isBlank(String value) {
        if (isNotNull(value)) {
            if (value.trim().equals("") || value.isEmpty()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean isNotBlank(String value) {
        if (isNotNull(value)) {
            if (value.trim().equals("")) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public static boolean isEmpty(String value) {
        if (isNull(value) || isBlank(value)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNotEmpty(String value) {
        if (isNotNull(value) && isNotBlank(value)) {
            return true;
        } else {
            return false;
        }
    }

//    public static void main(String[] args) {
//        String str = "1";
//        str = hexMD5(str);
//        logger.info("str is :{}", str);
//    }

    public static String toISO(String str) {
        return changeCharset(str, "utf-8", "iso-8859-1");
    }

    public static String toUTF_8(String str) {
        return changeCharset(str, "iso-8859-1", "utf-8");
    }

    private static String changeCharset(String str, String oldCharset, String newCharset) {
        try {
            byte[] bytes = str.getBytes(oldCharset);
            str = new String(bytes, newCharset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

}