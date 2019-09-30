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
import com.sky.vo.FestivalStatic_VO;
import com.sky.vo.StockOrderStatic_VO;
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
    public List<StockDealData> spiderStockDealData(Integer periodType , String skuCode  ,String mk) {
        List<StockDealData> list = new ArrayList<>();
        try {
            String type = "k";
            switch (periodType){
                case 1 : type = "k" ; break;
                case 2 : type = "wk" ; break;
                case 3 : type = "mk" ; break;

                case 4 : type = "m5k" ; break;
                case 5 : type = "m15k" ; break;
                case 6 : type = "m30k" ; break;
                case 7 : type = "m60k" ; break;
            }



            String url = "http://pdfm.eastmoney.com/EM_UBG_PDTI_Fast/api/js?rtntype=5&token=4f1862fc3b5e77c150a2b985b12db0fd&cb=jQuery183017615742790226108_1568593087961&id=" + skuCode + mk + "&type=" + type + "&authorityType=&_=1568593108420";
            System.out.println(url);
            String jsStr = SpiderUtils.HttpClientBuilderGet(url);
            jsStr = jsStr.substring(jsStr.indexOf("(") + 1 , jsStr.indexOf(")"));
            JSONObject jsonObject = JSON.parseObject(jsStr);
            String stockCode = jsonObject.getString("code");
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for(int i = 0 ; i < jsonArray.size() ; i++){
                String dataString = jsonArray.getString(i);
                String[] datas = dataString.split(",");
                StockDealData dealData = new StockDealData();
                dealData.setDealPeriod(periodType);
                dealData.setStockCode(stockCode);
                for(int x = 0 ; x < datas.length ; x ++){
                    switch (x){
                        case 0 : dealData.setDealTime(datas[x]); break;
                        case 1 : dealData.setOpenPrice(new BigDecimal(datas[x])); break;
                        case 2 : dealData.setClosePrice(new BigDecimal (datas[x])); break;
                        case 3 : dealData.setHighPrice(new BigDecimal (datas[x])); break;
                        case 4 : dealData.setLowPrice(new BigDecimal (datas[x])); break;
                        case 5 : dealData.setDealCount(new BigDecimal (datas[x])); break;
                        case 6 : dealData.setDealMoney(datas[x]); break;
                        case 7 : dealData.setAmplitude(datas[x]); break;
                        case 8 : dealData.setHandRate(new BigDecimal (datas[x])); break;
                    }
                }
            list.add(dealData);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list ;
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

    @Override
    public List<StockDealData> getPointDayScopeList(String stockCode, String pointDay, String days) {
        return baseMapper.getPointDayScopeList(stockCode ,pointDay ,days);
    }

    @Override
    public List<StockOrderStatic_VO> getStockOrderStaticList(String sectorName, String orderType, String startDay, String endDay) {
        return baseMapper.getStockOrderStaticList( sectorName, orderType, startDay, endDay);
    }

    @Override
    public List<FestivalStatic_VO> getStockFestivalStaticList(String sectorName, String startDay, String endDay, String startTime, String endTime) {
        return baseMapper.getStockFestivalStaticList( sectorName, startDay, endDay, startTime, endTime);
    }
}
