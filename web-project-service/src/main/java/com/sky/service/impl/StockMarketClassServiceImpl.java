package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.core.utils.SpiderUtils;
import com.sky.mapper.StockMarketClassMapper;
import com.sky.model.StockMarketClass;
import com.sky.service.StockMarketClassService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThinkPad on 2019/5/29.
 */
@Service
@Transactional
public class StockMarketClassServiceImpl extends ServiceImpl<StockMarketClassMapper,StockMarketClass> implements StockMarketClassService {

    @Override
    public void spiderStockMarketClass() {
        String url = "http://quote.eastmoney.com/config/sidemenu.json";
        String subjectString = SpiderUtils.HttpClientBuilderGet(url);
        JSONArray jsonArray = JSON.parseArray(subjectString);
        JSONArray sectorArray = jsonArray.getJSONObject(5).getJSONArray("next").getJSONObject(2).getJSONArray("next");
        JSONArray regionArray = jsonArray.getJSONObject(5).getJSONArray("next").getJSONObject(1).getJSONArray("next");
        JSONArray subjectArray = jsonArray.getJSONObject(5).getJSONArray("next").getJSONObject(0).getJSONArray("next");
        List<StockMarketClass> list = new ArrayList<StockMarketClass>();
        for(int x = 0 ; x < sectorArray.size() ; x ++){
            JSONObject detailObject = sectorArray.getJSONObject(x);
            String className = detailObject.getString("title");
            String classCode = detailObject.getString("key");
            StockMarketClass stockMarketClass = new StockMarketClass();
            stockMarketClass.setClassCode(classCode.substring(11 ,classCode.length()));
            stockMarketClass.setClassName(className);
            stockMarketClass.setClassType("行业板块");
            StockMarketClass marketClass = selectOne(new EntityWrapper<StockMarketClass>().where("class_code = {0} and class_name = {1} and class_type = {2} ",stockMarketClass.getClassCode() , stockMarketClass.getClassName(), stockMarketClass.getClassType()));
            if(marketClass == null){
                list.add(stockMarketClass);
            }
        }

        for(int y = 0 ; y < regionArray.size() ; y ++){
            JSONObject detailObject = regionArray.getJSONObject(y);
            String className = detailObject.getString("title");
            String classCode = detailObject.getString("key");
            StockMarketClass stockMarketClass = new StockMarketClass();
            stockMarketClass.setClassCode(classCode.substring(11 ,classCode.length()));
            stockMarketClass.setClassName(className);
            stockMarketClass.setClassType("地域板块");
            StockMarketClass marketClass = selectOne(new EntityWrapper<StockMarketClass>().where("class_code = {0} and class_name = {1} and class_type = {2} ",stockMarketClass.getClassCode() , stockMarketClass.getClassName(), stockMarketClass.getClassType()));
            if(marketClass == null){
                list.add(stockMarketClass);
            }
        }

        for(int z = 0 ; z < subjectArray.size() ; z ++){
            JSONObject detailObject = subjectArray.getJSONObject(z);
            String className = detailObject.getString("title");
            String classCode = detailObject.getString("key");
            StockMarketClass stockMarketClass = new StockMarketClass();
            stockMarketClass.setClassCode(classCode.substring(11 ,classCode.length()));
            stockMarketClass.setClassName(className);
            stockMarketClass.setClassType("概念板块");
            StockMarketClass marketClass = selectOne(new EntityWrapper<StockMarketClass>().where("class_code = {0} and class_name = {1} and class_type = {2} ",stockMarketClass.getClassCode() , stockMarketClass.getClassName(), stockMarketClass.getClassType()));
            if(marketClass == null){
                list.add(stockMarketClass);
            }
        }
        if(list.size() > 0){
            insertBatch(list);
        }
    }
}
