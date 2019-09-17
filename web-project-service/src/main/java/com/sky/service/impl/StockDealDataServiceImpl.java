package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.core.utils.SpiderUtils;
import com.sky.mapper.StockDealDataMapper;
import com.sky.model.StockDealData;
import com.sky.service.StockDealDataService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThinkPad on 2019/9/16.
 */
@Service
@Transactional
public class StockDealDataServiceImpl extends ServiceImpl<StockDealDataMapper,StockDealData> implements StockDealDataService {

    private static String type2 = "2" ;

    @Override
    public List<StockDealData> spiderStockDealData(Integer periodType , String skuCode) {
        List<StockDealData> list = new ArrayList<>();
        try {
            String url = "http://pdfm.eastmoney.com/EM_UBG_PDTI_Fast/api/js?rtntype=5&token=4f1862fc3b5e77c150a2b985b12db0fd&cb=jQuery183017615742790226108_1568593087961&id=" + skuCode +"1&type=k&authorityType=&_=1568593108420";
            String jsStr = SpiderUtils.HttpClientBuilderGet(url);
            jsStr = jsStr.substring(jsStr.indexOf("(") + 1 , jsStr.indexOf(")"));
            JSONObject jsonObject = JSON.parseObject(jsStr);
            String stockCode = jsonObject.getString("code");
            String stockName = jsonObject.getString("name");
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            String stats = jsonObject.getString("stats");
            if(StringUtils.isNotBlank(stats) && stats.equals("false")){
                return spiderStockDealData2(periodType ,skuCode);
            }else{
                for(int i = 0 ; i < jsonArray.size() ; i++){
                    String dataString = jsonArray.getString(i);
                    String[] datas = dataString.split(",");
                    StockDealData dealData = new StockDealData();
                    dealData.setDealPeriod(1);
                    dealData.setStockCode(stockCode);
                    for(int x = 0 ; x < datas.length ; x ++){
                        switch (x){
                            case 0 : dealData.setDealTime(datas[x]); break;
                            case 1 : dealData.setOpenPrice(new BigDecimal(datas[x])); break;
                            case 2 : dealData.setClosePrice(new BigDecimal (datas[x])); break;
                            case 3 : dealData.setHighPrice(new BigDecimal (datas[x])); break;
                            case 4 : dealData.setLowPrice(new BigDecimal (datas[x])); break;
                            case 5 : dealData.setDealCount(new BigDecimal (datas[x])); break;
                            case 6 : dealData.setDealMoney(new BigDecimal (datas[x])); break;
                            case 7 : dealData.setAmplitude(datas[x]); break;
                            case 8 : dealData.setHandRate(new BigDecimal (datas[x])); break;
                        }
                    }
                list.add(dealData);
//                    StockDealData isExist = selectOne(new EntityWrapper<StockDealData>().where("deal_period = {0} and deal_time = {1} and stock_code = {2}" , periodType ,dealData.getDealTime() , dealData.getStockCode()));
//                    if(isExist == null){
//                        list.add(dealData);
//                    }else{
//                        break;
//                    }
                }
//            batchList(list);
//            if(list.size() > 0){
//               insertBatch(list);
//            }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list ;
    }

    private List<StockDealData> spiderStockDealData2(Integer periodType , String skuCode) {
        List<StockDealData> list = new ArrayList<>();
        try{
            String url = "http://pdfm.eastmoney.com/EM_UBG_PDTI_Fast/api/js?rtntype=5&token=4f1862fc3b5e77c150a2b985b12db0fd&cb=jQuery183017615742790226108_1568593087961&id=" + skuCode +"2&type=k&authorityType=&_=1568593108420";
            String jsStr = SpiderUtils.HttpClientBuilderGet(url);
            jsStr = jsStr.substring(jsStr.indexOf("(") + 1 , jsStr.indexOf(")"));
            JSONObject jsonObject = JSON.parseObject(jsStr);
            String stockCode = jsonObject.getString("code");
            String stockName = jsonObject.getString("name");
            JSONArray jsonArray = jsonObject.getJSONArray("data");

            for(int i = 0 ; i < jsonArray.size() ; i++){
                String dataString = jsonArray.getString(i);
                String[] datas = dataString.split(",");
                StockDealData dealData = new StockDealData();
                dealData.setDealPeriod(1);
                dealData.setStockCode(stockCode);
                for(int x = 0 ; x < datas.length ; x ++){
                    switch (x){
                        case 0 : dealData.setDealTime(datas[x]); break;
                        case 1 : dealData.setOpenPrice(new BigDecimal(datas[x])); break;
                        case 2 : dealData.setClosePrice(new BigDecimal (datas[x])); break;
                        case 3 : dealData.setHighPrice(new BigDecimal (datas[x])); break;
                        case 4 : dealData.setLowPrice(new BigDecimal (datas[x])); break;
                        case 5 : dealData.setDealCount(new BigDecimal (datas[x])); break;
                        case 6 : dealData.setDealMoney(new BigDecimal (datas[x])); break;
                        case 7 : dealData.setAmplitude(datas[x]); break;
                        case 8 : dealData.setHandRate(new BigDecimal (datas[x])); break;
                    }
                }
                list.add(dealData);
//                StockDealData isExist = selectOne(new EntityWrapper<StockDealData>().where("deal_period = {0} and deal_time = {1} and stock_code = {2}" , periodType ,dealData.getDealTime() , dealData.getStockCode()));
//                if(isExist == null){
//                    list.add(dealData);
//                }else{
//                    break;
//                }
            }
//        batchList(list);
//        if(list.size() > 0){
//            insertBatch(list);
//        }
        }catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }


    public void batchList(List<StockDealData> dataList){
        //分批处理
        if(null!=dataList&&dataList.size()>0){
            int pointsDataLimit = 400;//限制条数
            Integer size = dataList.size();
            //判断是否有必要分批
            if(pointsDataLimit<size){
                int part = size/pointsDataLimit;//分批数
                System.out.println("共有 ： "+size+"条，！"+" 分为 ："+part+"批");
                for (int i = 0; i < part; i++) {
                    List<StockDealData> listPage = dataList.subList(0, pointsDataLimit);
                    insertBatch(listPage);
                     //剔除
                     dataList.subList(0, pointsDataLimit).clear();
                }
                if(!dataList.isEmpty()){
                     System.out.println(dataList);//表示最后剩下的数据
                }
            }else{
                insertBatch(dataList);
                System.out.println(dataList);
            }
        }else{
            System.out.println("没有数据!!!");
        }
    }
}
