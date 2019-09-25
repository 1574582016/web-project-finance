package com.sky;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sky.core.utils.CommonHttpUtil;
import com.sky.model.StockTigerList;
import com.sky.service.StockTigerListService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThinkPad on 2019/9/24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test01 {

    @Autowired
    private StockTigerListService stockTigerListService ;

    @Test
    public void test(){
        String url = "http://data.eastmoney.com/DataCenter_V3/stock2016/TradeDetail/pagesize=200,page=1,sortRule=-1,sortType=,startDate=2019-09-24,endDate=2019-09-24,gpfw=0,js=var%20data_tab_1.html?rt=26154751";
        String jsStr = CommonHttpUtil.sendPost(url);
//        System.out.println(jsStr);
        jsStr = jsStr.substring(jsStr.indexOf("data_tab_1=") + 11 , jsStr.length());
//        System.out.println(jsStr);
        JSONArray jsonArray = JSON.parseObject(jsStr).getJSONArray("data");
        List<StockTigerList> list = new ArrayList<>();
        for(int i = 0 ; i < jsonArray.size() ; i ++){
           JSONObject jsonObject = jsonArray.getJSONObject(i);
            StockTigerList tigerList = new StockTigerList();
            tigerList.setStockCode(jsonObject.getString("SCode"));
            tigerList.setStockName(jsonObject.getString("SName"));
            tigerList.setPublishTime(jsonObject.getString("Tdate"));
            tigerList.setUpperRange(jsonObject.getBigDecimal("Chgradio"));
            tigerList.setHandRate(jsonObject.getBigDecimal("Dchratio"));
            tigerList.setFocusReason(jsonObject.getString("Ctypedes"));
            tigerList.setBuyMoney(jsonObject.getBigDecimal("Bmoney"));
            tigerList.setSellMoney(jsonObject.getBigDecimal("Smoney"));
            list.add(tigerList);
        }
        stockTigerListService.insertBatch(list);
    }
}
