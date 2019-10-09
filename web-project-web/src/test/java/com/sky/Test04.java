package com.sky;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sky.core.utils.CommonHttpUtil;
import com.sky.model.SectorDealData;
import com.sky.model.StockIndexClass;
import com.sky.service.SectorDealDataService;
import com.sky.service.StockIndexClassService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThinkPad on 2019/10/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test04 {

    @Autowired
    private SectorDealDataService sectorDealDataService ;

    @Autowired
    private StockIndexClassService stockIndexClassService ;

    @Test
    public void test(){
        List<SectorDealData> dataList = sectorDealDataService.spiderSectorDealData(1 , "BK0735");
        System.out.println(dataList.toString());
    }

    @Test
    public void test2(){
        List<StockIndexClass> list = new ArrayList<>();
        for(int i = 1 ; i < 35 ;i++){
            String url = "http://www.csindex.com.cn/zh-CN/indices/index?page="+ i +"&page_size=50&by=asc&order=%E5%8F%91%E5%B8%83%E6%97%B6%E9%97%B4&data_type=json&class_1=1&class_2=2&class_3=3";
            String jsStr = CommonHttpUtil.sendGet(url);
            System.out.println(jsStr);
            JSONObject jsonObject = JSON.parseObject(jsStr);
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            for(int j = 0 ; j < jsonArray.size() ; j++){
               JSONObject jsonObject1 = jsonArray.getJSONObject(j);
                StockIndexClass indexClass = new StockIndexClass();
                indexClass.setIndexCode(jsonObject1.getString("index_code"));
                indexClass.setIndxSname(jsonObject1.getString("indx_sname"));
                indexClass.setStockNum(jsonObject1.getString("num"));
                indexClass.setBasePoint(jsonObject1.getString("base_point"));
                indexClass.setBaseDate(jsonObject1.getString("base_date"));
                indexClass.setOnlineDate(jsonObject1.getString("online_date"));
                indexClass.setIndexIntro(jsonObject1.getString("index_c_intro"));
                indexClass.setIndexFullname(jsonObject1.getString("index_c_fullname"));
                indexClass.setClassSeries(jsonObject1.getString("class_series"));
                indexClass.setClassRegion(jsonObject1.getString("class_region"));
                indexClass.setClassAssets(jsonObject1.getString("class_assets"));
                indexClass.setClassClassify(jsonObject1.getString("class_classify"));
                indexClass.setClassCurrency(jsonObject1.getString("class_currency"));
                list.add(indexClass);
            }
        }
        if(list.size() > 0){
            stockIndexClassService.insertBatch(list);
        }

    }


}
