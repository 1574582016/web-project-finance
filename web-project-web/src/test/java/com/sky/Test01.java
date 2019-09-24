package com.sky;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sky.core.utils.CommonHttpUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by ThinkPad on 2019/9/24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test01 {

    @Test
    public void test(){
        String url = "http://data.eastmoney.com/DataCenter_V3/stock2016/TradeDetail/pagesize=200,page=1,sortRule=-1,sortType=,startDate=2019-08-24,endDate=2019-09-24,gpfw=0,js=var%20data_tab_1.html?rt=26154751";
        String jsStr = CommonHttpUtil.sendPost(url);
        System.out.println(jsStr);
        jsStr = jsStr.substring(jsStr.indexOf("data_tab_1=") + 11 , jsStr.length());
        System.out.println(jsStr);
        JSONArray jsonArray = JSON.parseObject(jsStr).getJSONArray("data");
        for(int i = 0 ; i < jsonArray.size() ; i ++){
           JSONObject jsonObject = jsonArray.getJSONObject(i);
            System.out.println(jsonObject.toString());
        }
//        JSONObject jsonObject = JSON.parseObject(jsStr);
//        JSONObject dataObject = jsonObject.getJSONObject("data");
//        System.out.println(dataObject);
    }
}
