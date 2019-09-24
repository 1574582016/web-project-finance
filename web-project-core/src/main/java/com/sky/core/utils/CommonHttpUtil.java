package com.sky.core.utils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.*;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * Created by ThinkPad on 2019/9/17.
 */
public class CommonHttpUtil {

    public static String sendGet(String url) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            // 建立实际的连接
            connection.connect();
            System.out.println(connection.toString());
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public static String sendPost(String url) {
        String response = null;
        HttpClient client = new HttpClient(new MultiThreadedHttpConnectionManager());
        client.getHttpConnectionManager().getParams().setConnectionTimeout(500000);
        client.getHttpConnectionManager().getParams().setSoTimeout(500000);
        client.getParams().setIntParameter("http.connection.timeout", 500000);
        client.getParams().setIntParameter("http.socket.timeout", 500000);

        PostMethod httpmethod = new PostMethod(url);
        httpmethod.setFollowRedirects(false);
        httpmethod.setRequestHeader("Accept-Language", "zh-cn");
        httpmethod.setRequestHeader("User-Agent",
                "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; BOIE9;ZHCN)");
        httpmethod.setRequestHeader("Connection", "Keep-Alive");
        httpmethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "gb2312");
        try {
            int statusCode = client.executeMethod(httpmethod);
            if (statusCode == HttpStatus.SC_OK) {
                // read
                // res = httpmethod.getResponseBodyAsString();
                ByteArrayOutputStream bao = new ByteArrayOutputStream();
                BufferedOutputStream bos = new BufferedOutputStream(bao);
                BufferedInputStream bis = null;
                bis = new BufferedInputStream(httpmethod.getResponseBodyAsStream());
                byte[] buff = new byte[2048];
                int bytesRead;
                // Simple read/write loop.
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }
                if (bis != null)
                    bis.close();
                if (bos != null) {
                    bos.flush();
                    response = bao.toString("gb2312");
                }
            } else {
            }
        } catch (HttpException e) {
            response = "";
        } catch (IOException e) {
            response = "";
        } finally {
            httpmethod.releaseConnection();
        }
        return response;
    }

}
