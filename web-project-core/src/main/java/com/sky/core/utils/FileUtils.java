package com.sky.core.utils;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import eu.bitwalker.useragentutils.UserAgent;

import javax.imageio.ImageIO;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>处理上传下载中的文件中文编码</pre>
 *
 * @author 许言杰(qoodit.kfc)
 * @version v1.0
 * @create 2018/1/6 - 23:51
 * @email xuyanjie@qiaodit.com
 */
public class FileUtils extends cn.hutool.core.io.FileUtil {

    private static final Log logger = LogFactory.get();

    /**
     * 设置下载文件中文件的名称
     *
     * @param filename
     * @param request
     * @return 通用浏览器文件码名
     */
    public static String encodeFileName(String filename, HttpServletRequest request) {

        String encodedFileName;

        String browser = UserAgent.parseUserAgentString(request.getHeader("User-Agent")).getBrowser().getGroup().getName();
        logger.error("logger:{ encodeFileName  browser ===> [" + browser + "]}");
        try {
            switch (browser) {
                case "Chrome":
                case "Internet Explorer":
                case "Safari":
                    encodedFileName = URLEncoder.encode(filename, "utf-8").replaceAll("\\+", "%20");
                    break;
                case "Firefox":
                case "Opera":
                default:
                    encodedFileName = MimeUtility.encodeWord(filename);
                    break;
            }
            return encodedFileName;
        } catch (UnsupportedEncodingException e) {
            return filename;
        }
    }

    /**
     * 判断是否是白名单
     */
    public static boolean isWhiteBeg(String requestUrl, List<String> whiteList) {

        if (requestUrl.length() == 1 && "/".equals(requestUrl)
                || whiteList.size() <= 0) {
            return true;
        } else {
            for (String urlTemp : whiteList) {
                if (requestUrl.indexOf(urlTemp.toLowerCase()) > -1) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 读取白名单配置文件
     */
    public static List<String> readWhiteFile(InputStream stream) {

        List<String> list = new ArrayList<>();
        String line;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"))) {
            while ((line = br.readLine()) != null) {
                if ("".equals(line) || line.contains("#")) {
                } else {
                    list.add(line);
                }
            }
        } catch (Exception e) {
            e.getLocalizedMessage();
        }

        return list;
    }

    public static String GenerateFile(String imgSource, String savePath, String saveName, HttpServletRequest request) throws IOException {
        return null;
    }


    public static Map<String, Object> lookUpFilePath(String proPath, String name) {
        Long st = System.currentTimeMillis();
        try {
            String tmp = new StringBuffer(name).reverse().toString().replaceFirst("x", "");
            final String suffixName = new StringBuffer(tmp).reverse().toString();

            logger.warn("lookUpFilePath in [{}] filename is [{}] suffixName is [{}]", proPath, name, suffixName);

            final String[] loopPath = new String[1];

            Files.walkFileTree(Paths.get(proPath), new SimpleFileVisitor<Path>() {
                @Override//访问目录前
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override//遍历文件时
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (file.endsWith(suffixName)) {
                        loopPath[0] = file.toString();
                        return FileVisitResult.TERMINATE;
                    }
                    return FileVisitResult.CONTINUE;
                }
            });

            if (!StringUtils.isEmpty(loopPath[0])) {
                Map<String, Object> map = new HashMap<>();
                map.put("path", loopPath[0]);
                map.put("time", System.currentTimeMillis() - st);
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static boolean deleteSamilarFile(String path ,String fimeName){
        boolean b=false;
        File file = new File(path);
        File[] tempFile = file.listFiles();
        for(int i = 0; i < tempFile.length; i++){
            if(tempFile[i].getName().startsWith(fimeName)||tempFile[i].getName().endsWith(fimeName)){
                boolean del=deleteFile(path+tempFile[i].getName());
            if(del){
                b=true;
             }else{
                System.out.println("文件"+tempFile[i].getName()+"删除失败");
            }
        }
    }
     return b;
    }

    private static boolean deleteFile(String path){
        System.out.println(path);
        boolean del=false;
        File file=new File(path);
        if(file.isFile()){
           file.delete();
           del=true;
         }
     return del;
     }

}
