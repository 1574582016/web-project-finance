package com.sky;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sky.core.utils.DateUtils;
import com.sky.core.utils.SpiderUtils;
import com.sky.model.*;
import com.sky.service.*;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
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

    @Autowired
    private EconomyNewsStatictisService economyNewsStatictisService ;

    @Autowired
    private StockCodeService stockCodeService ;

    @Autowired
    private StockDealDataService stockDealDataService ;

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

    @Test
    public void test3(){
        for(int x = 1 ; x < 1000 ; x ++){
            String url = "";
            if(x == 1){
                url = "http://finance.eastmoney.com/a/cywjh.html";
            }else{
                url = "http://finance.eastmoney.com/a/cywjh_"+ x +".html";
            }
            Document doc = SpiderUtils.HtmlJsoupGet(url);
            Elements elements = doc.getElementsByClass("repeatList").get(0).getElementsByTag("li");
            List<EconomyNewsStatictis> list = new ArrayList<>();
            for(int i = 0 ; i < elements.size() ; i ++){
                Element element1 = elements.get(i).getElementsByClass("text").get(0).getElementsByClass("title").get(0);
                Element element2 = elements.get(i).getElementsByClass("text").get(0).getElementsByClass("info").get(0);
                Element element3 = elements.get(i).getElementsByClass("text").get(0).getElementsByClass("time").get(0);
                String title = element1.text();
                String newurl = element1.getElementsByTag("a").get(0).attr("href");
                String contains= element2.attr("title");
                String time = element3.html();
                System.out.println(title);
                System.out.println(newurl);
                System.out.println(contains);
                System.out.println(time);
                time = time.replace("月","-").replace("日","");
                time = newurl.substring(newurl.indexOf("/a/")+3,newurl.indexOf("/a/")+7) + "-"+ time +":00";
                System.out.println(time);
                EconomyNewsStatictis dayNews = new EconomyNewsStatictis();
                dayNews.setNewsTitle(title);
//                dayNews.setNewsContent(href);
                dayNews.setNewsTime(time);
                EconomyNewsStatictis news = economyNewsStatictisService.selectOne(new EntityWrapper<EconomyNewsStatictis>().where("news_time = {0}" , time).where("news_title = {0}" , title));
                if(news == null){
                    list.add(dayNews);
                }
            }
            if(list.size() > 0){
                economyNewsStatictisService.insertBatch(list);
            }else{
                break;
            }

        }
    }


    @Test
    public void test4(){
        for(int x = 1 ; x < 2 ; x ++){
            String url = "";
            if(x == 1){
                url = "http://finance.eastmoney.com/a/cgnjj.html";
            }else{
                url = "http://finance.eastmoney.com/a/cgnjj_"+ x +".html";
            }
            Document doc = SpiderUtils.HtmlJsoupGet(url);
            Elements elements = doc.getElementsByClass("repeatList").get(0).getElementsByTag("li");
            for(int i = 0 ; i < elements.size() ; i ++){
                Element element1 = elements.get(i).getElementsByClass("text").get(0).getElementsByClass("title").get(0);
                Element element2 = elements.get(i).getElementsByClass("text").get(0).getElementsByClass("info").get(0);
                Element element3 = elements.get(i).getElementsByClass("text").get(0).getElementsByClass("time").get(0);
                String title = element1.text();
                String newurl = element1.getElementsByTag("a").get(0).attr("href");
                String contains= element2.attr("title");
                String time = element3.html();
                System.out.println(title);
                System.out.println(newurl);
                System.out.println(contains);
                System.out.println(time);
            }
        }
    }


    @Test
    public void test5(){
        try{
            for(int type = 1 ; type <= 5 ; type ++){
                for(int page = 1 ; page <= 1000 ; page ++){
                    boolean just = economyNewsStatictisService.processEveryDayNews(type ,page);
                    if(just){
                        Thread.sleep(200);
                    }else{
                        break;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test
    public void test6(){
        String url = "http://pdfm.eastmoney.com/EM_UBG_PDTI_Fast/api/js?rtntype=5&token=4f1862fc3b5e77c150a2b985b12db0fd&cb=jQuery183017615742790226108_1568593087961&id=3000592&type=k&authorityType=&_=1568593108420";
        String jsStr = SpiderUtils.HttpClientBuilderGet(url);
        jsStr = jsStr.substring(jsStr.indexOf("(") + 1 , jsStr.indexOf(")"));
        JSONObject jsonObject = JSON.parseObject(jsStr);
        String stockCode = jsonObject.getString("code");
        String stockName = jsonObject.getString("name");
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        System.out.println("========stockCode=============" + stockCode);
        System.out.println("========stockName=============" + stockName);
        List<StockDealData> list = new ArrayList<>();
        for(int i = 0 ; i < jsonArray.size() ; i++){
            String dataString = jsonArray.getString(i);
            String[] datas = dataString.split(",");
            StockDealData dealData = new StockDealData();
            dealData.setDealPeriod(1);
            dealData.setStockCode(stockCode);
            for(int x = 0 ; x < datas.length ; x ++){
                switch (x){
                    case 0 : dealData.setDealTime(datas[x]); break;
                    case 1 : dealData.setOpenPrice(new BigDecimal (datas[x])); break;
                    case 2 : dealData.setClosePrice(new BigDecimal (datas[x])); break;
                    case 3 : dealData.setHighPrice(new BigDecimal (datas[x])); break;
                    case 4 : dealData.setLowPrice(new BigDecimal (datas[x])); break;
                    case 5 : dealData.setDealCount(new BigDecimal (datas[x])); break;
                    case 6 : dealData.setDealMoney(new BigDecimal (datas[x])); break;
                    case 7 : dealData.setAmplitude(datas[x]); break;
                    case 8 : dealData.setExt(datas[x]); break;
                }
            }
            System.out.println(dealData.toString());
            list.add(dealData);
        }
        System.out.println(list.toString());
    }


    @Test
    public void test7(){
        try {
            List<StockCode> codeList = stockCodeService.selectList(null);
            for (StockCode stockCode : codeList) {
                System.out.println("================stockCode=========================" + stockCode);
                List<StockDealData> dataList = stockDealDataService.spiderStockDealData(1, stockCode.getStockCode());

                if(null!=dataList&&dataList.size()>0){
                    int pointsDataLimit = 200;//限制条数
                    Integer size = dataList.size();
                    //判断是否有必要分批
                    if(pointsDataLimit<size){
                        int part = size/pointsDataLimit;//分批数
                        System.out.println("共有 ： "+size+"条，！"+" 分为 ："+part+"批");
                        for (int i = 0; i < part; i++) {
                            List<StockDealData> listPage = dataList.subList(0, pointsDataLimit);
                            stockDealDataService.insertBatch(listPage);
                            //剔除
                            dataList.subList(0, pointsDataLimit).clear();
                        }
                        if(!dataList.isEmpty()){
                            stockDealDataService.insertBatch(dataList);
                        }
                    }else{
                        stockDealDataService.insertBatch(dataList);
                    }
                }else{
                    System.out.println("没有数据!!!");
                }

                Thread.sleep(1000);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}


