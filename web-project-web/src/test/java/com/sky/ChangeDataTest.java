package com.sky;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sky.core.utils.SpiderUtils;
import com.sky.model.StockChoseClass;
import com.sky.model.StockCompanyNotice;
import com.sky.service.StockChoseClassService;
import com.sky.service.StockCompanyNoticeService;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThinkPad on 2019/9/6.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ChangeDataTest {

    @Autowired
    private StockCompanyNoticeService stockCompanyNoticeService ;

    @Autowired
    private StockChoseClassService stockChoseClassService ;

    @Test
    public void test(){
        String url = "http://data.eastmoney.com/DataCenter_V3/stock2016/TradeDetail/pagesize=1000,page=1,sortRule=-1,sortType=,startDate=2019-09-05,endDate=2019-09-05,gpfw=0,js=var%20data_tab_1.html?rt=26129421";
        Document doc = SpiderUtils.HtmlJsoupGet(url);
        String resultStr = doc.text();
//        System.out.println(resultStr.substring(resultStr.indexOf("data_tab_1=") + 11,resultStr.length()));
        resultStr = resultStr.substring(resultStr.indexOf("data_tab_1=") + 11,resultStr.length());
        JSONArray jsonArray = JSON.parseObject(resultStr).getJSONArray("data");
        for(int i = 0 ; i < jsonArray.size() ; i  ++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            System.out.println("=======" + i + "=========" + jsonObject.toString());
        }
    }

    @Test
    public void test2(){
        String url = "http://data.eastmoney.com/stock_choice/data/data.js?_=1567819105720";
        String jsonString = SpiderUtils.HttpClientBuilderGet(url);
        JSONArray tabs = JSON.parseObject(jsonString).getJSONArray("tabs");
        JSONArray cont = JSON.parseObject(jsonString).getJSONArray("cont");
        for(int i = 0 ; i < tabs.size() ; i++){
           JSONObject jsonObject = tabs.getJSONObject(i);
            System.out.println(jsonObject);
        }
        List<StockChoseClass> list = new ArrayList<>();
        for(int i = 0 ; i < cont.size() ; i++){
            JSONObject jsonObject = cont.getJSONObject(i);
            JSONArray jsonArray = jsonObject.getJSONArray("list_cont");

            for(int x = 0 ; x < jsonArray.size() ; x++){
                JSONObject jsonObject2 = jsonArray.getJSONObject(x);
                String selectStr = jsonObject2.getString("selectorcache");
                if(StringUtils.isNotBlank(selectStr)){
                    JSONArray selectorcache = jsonObject2.getJSONArray("selectorcache");
                    for(int y = 0 ; y < selectorcache.size() ; y++){
                        JSONObject jsonObject3 = selectorcache.getJSONObject(y);
                        JSONArray uls = jsonObject3.getJSONArray("ul");

                        for(int z = 0 ; z < uls.size() ; z ++){
                            JSONObject jsonObject4 = uls.getJSONObject(z);
                            String parentId = jsonObject4.getString("id");
                            JSONArray LI = jsonObject4.getJSONArray("li");
                            for(int o = 0 ; o < LI.size() ; o ++){
                                JSONObject jsonObject5 = LI.getJSONObject(o);
                                StockChoseClass choseClass = new StockChoseClass();
                                choseClass.setParentCode(parentId);
                                choseClass.setClassCode(jsonObject5.getString("id"));
                                choseClass.setClassName(jsonObject5.getString("text"));
                                System.out.println(parentId + "=================" + jsonObject5);
                                list.add(choseClass);
                            }
                        }

                    }
                }

                JSONArray ul = jsonObject2.getJSONArray("ul");
                for(int t = 0 ; t < ul.size() ; t++){
                    JSONObject jsonObjectul1 = ul.getJSONObject(t);
                    System.out.println("==============" + jsonObjectul1);
                    StockChoseClass choseClass = new StockChoseClass();
                    choseClass.setClassUnit(jsonObjectul1.getString("unit"));
                    choseClass.setClassCode(jsonObjectul1.getString("id"));
                    choseClass.setClassName(jsonObjectul1.getString("text"));
                    list.add(choseClass);
                }

            }
        }

        System.out.println(list.toString());
        stockChoseClassService.insertBatch(list);
    }
}
