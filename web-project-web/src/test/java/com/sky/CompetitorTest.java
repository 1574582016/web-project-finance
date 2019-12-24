package com.sky;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sky.core.utils.SpiderUtils;

/**
 * Created by ThinkPad on 2019/12/24.
 */
public class CompetitorTest {

    public static void main(String[] args){
        String url = "http://data.eastmoney.com/zlsj/detail.aspx?type=ajax&st=2&sr=-1&p=1&ps=30&jsObj=UwvjQGfy&stat=0&code=603986&date=2019-09-30&rt=52572628";
        System.out.println(url);
        String resultStrint = SpiderUtils.HttpClientBuilderGet(url);
//        System.out.println(resultStrint);
        resultStrint = resultStrint.substring(15,resultStrint.length());
//        System.out.println(resultStrint);
        JSONObject jsonObject = JSON.parseObject(resultStrint);
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        for(int i = 0 ; i < jsonArray.size() ; i++){
            System.out.println(jsonArray.get(i));
            JSONObject jsonDetail = jsonArray.getJSONObject(i);
            String SHName = jsonDetail.getString("SHName");
            String SHCode = jsonDetail.getString("SHCode");
            String SHType = jsonDetail.getString("Type");
            String ShareHDNum = jsonDetail.getString("ShareHDNum");
            String TypeCode = jsonDetail.getString("TypeCode");
            String RDate = jsonDetail.getString("RDate");
            RDate = RDate.substring(RDate.indexOf("(") + 1,RDate.indexOf(")"));
            System.out.println("=======SHName=========" + SHName);
            System.out.println("=======SHCode=========" + SHCode);
            System.out.println("=======SHType=========" + SHType);
            System.out.println("=======ShareHDNum=========" + ShareHDNum);
            System.out.println("=======TypeCode=========" + TypeCode);
            System.out.println("=======RDate=========" + RDate);
        }
    }
}
