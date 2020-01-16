package com.sky.test;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sky.core.utils.DateUtils;
import com.sky.model.*;
import com.sky.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThinkPad on 2020/1/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test08 {

    @Autowired
    private ForexDealDataOneMinuteService forexDealDataOneMinuteService ;

    @Autowired
    private ForexDealDataFiveMinuteService forexDealDataFiveMinuteService ;

    @Autowired
    private ForexDealDataFifteenMinuteService forexDealDataFifteenMinuteService ;

    @Autowired
    private ForexDealDataThirtyMinuteService forexDealDataThirtyMinuteService ;

    @Autowired
    private ForexDealDataOneHourService forexDealDataOneHourService ;

    @Autowired
    private ForexDealDataFourHourService forexDealDataFourHourService ;

    @Autowired
    private ForexDealDataOneDayService forexDealDataOneDayService ;

    @Test
    public void test(){
        List<ForexDealDataOneMinute> dataList = forexDealDataOneMinuteService.selectList(new EntityWrapper<ForexDealDataOneMinute>().orderBy("deal_time asc ").last("LIMIT 2000"));
        List<ForexDealDataOneMinute> newList = analyseCycleDataList(dataList , 5);
        int type = 5 ;
        int pointsDataLimit = 200;//限制条数
        Integer size = newList.size();
        //判断是否有必要分批
        if (pointsDataLimit < size) {
            int part = size / pointsDataLimit;//分批数
            System.out.println("共有 ： " + size + "条，！" + " 分为 ：" + part + "批");
            for (int i = 0; i < part; i++) {
                List<ForexDealDataOneMinute> listPage = newList.subList(0, pointsDataLimit);
                saveForexDealData(type , listPage);

                //剔除
                newList.subList(0, pointsDataLimit).clear();
            }
            if (!newList.isEmpty()) {
                saveForexDealData(type , newList);
            }
        } else {
            saveForexDealData(type , newList);
        }
    }


    private void saveForexDealData(int type , List<ForexDealDataOneMinute> dataList){
        switch (type){
            case 5 :
                List<ForexDealDataFiveMinute> fiveList = new ArrayList<>();
                for(ForexDealDataOneMinute oneMinute : dataList){
                    ForexDealDataFiveMinute dealData = new ForexDealDataFiveMinute();
                    dealData.setDealTime(oneMinute.getDealTime());
                    dealData.setForexCode(oneMinute.getForexCode());
                    dealData.setOpenPrice(oneMinute.getOpenPrice());
                    dealData.setHighPrice(oneMinute.getHighPrice());
                    dealData.setLowPrice(oneMinute.getLowPrice());
                    dealData.setClosePrice(oneMinute.getClosePrice());
                    fiveList.add(dealData);
                }
                if(fiveList.size() > 0){
                    forexDealDataFiveMinuteService.insertBatch(fiveList);
                }
                break;
            case 15 :
                List<ForexDealDataFifteenMinute> fifteenList = new ArrayList<>();
                for(ForexDealDataOneMinute oneMinute : dataList){
                    ForexDealDataFifteenMinute dealData = new ForexDealDataFifteenMinute();
                    dealData.setDealTime(oneMinute.getDealTime());
                    dealData.setForexCode(oneMinute.getForexCode());
                    dealData.setOpenPrice(oneMinute.getOpenPrice());
                    dealData.setHighPrice(oneMinute.getHighPrice());
                    dealData.setLowPrice(oneMinute.getLowPrice());
                    dealData.setClosePrice(oneMinute.getClosePrice());
                    fifteenList.add(dealData);
                }
                if(fifteenList.size() > 0){
                    forexDealDataFifteenMinuteService.insertBatch(fifteenList);
                }
                break;
            case 30 :
                List<ForexDealDataThirtyMinute> thirtyList = new ArrayList<>();
                for(ForexDealDataOneMinute oneMinute : dataList){
                    ForexDealDataThirtyMinute dealData = new ForexDealDataThirtyMinute();
                    dealData.setDealTime(oneMinute.getDealTime());
                    dealData.setForexCode(oneMinute.getForexCode());
                    dealData.setOpenPrice(oneMinute.getOpenPrice());
                    dealData.setHighPrice(oneMinute.getHighPrice());
                    dealData.setLowPrice(oneMinute.getLowPrice());
                    dealData.setClosePrice(oneMinute.getClosePrice());
                    thirtyList.add(dealData);
                }
                if(thirtyList.size() > 0){
                    forexDealDataThirtyMinuteService.insertBatch(thirtyList);
                }
                break;

            case 60 :
                List<ForexDealDataOneHour> hourList = new ArrayList<>();
                for(ForexDealDataOneMinute oneMinute : dataList){
                    ForexDealDataOneHour dealData = new ForexDealDataOneHour();
                    dealData.setDealTime(oneMinute.getDealTime());
                    dealData.setForexCode(oneMinute.getForexCode());
                    dealData.setOpenPrice(oneMinute.getOpenPrice());
                    dealData.setHighPrice(oneMinute.getHighPrice());
                    dealData.setLowPrice(oneMinute.getLowPrice());
                    dealData.setClosePrice(oneMinute.getClosePrice());
                    hourList.add(dealData);
                }
                if(hourList.size() > 0){
                    forexDealDataOneHourService.insertBatch(hourList);
                }
                break;
            case 240 :
                List<ForexDealDataFourHour> fourHourList = new ArrayList<>();
                for(ForexDealDataOneMinute oneMinute : dataList){
                    ForexDealDataFourHour dealData = new ForexDealDataFourHour();
                    dealData.setDealTime(oneMinute.getDealTime());
                    dealData.setForexCode(oneMinute.getForexCode());
                    dealData.setOpenPrice(oneMinute.getOpenPrice());
                    dealData.setHighPrice(oneMinute.getHighPrice());
                    dealData.setLowPrice(oneMinute.getLowPrice());
                    dealData.setClosePrice(oneMinute.getClosePrice());
                    fourHourList.add(dealData);
                }
                if(fourHourList.size() > 0){
                    forexDealDataFourHourService.insertBatch(fourHourList);
                }
                break;
        }

    }



    private List<ForexDealDataOneMinute> analyseCycleDataList(List<ForexDealDataOneMinute> dataList , int cycle){
        List<ForexDealDataOneMinute> newList = new ArrayList<>();
        int pointsDataLimit = cycle;//限制条数
        Integer size = dataList.size();
        //判断是否有必要分批
        if (pointsDataLimit < size) {
            int part = size / pointsDataLimit;//分批数
            System.out.println("共有 ： " + size + "条，！" + " 分为 ：" + part + "批");
            for (int i = 0; i < part; i++) {
                List<ForexDealDataOneMinute> listPage = dataList.subList(0, pointsDataLimit);
                ForexDealDataOneMinute oneMinute = caculateNewDealData(listPage);
                newList.add(oneMinute);
                //剔除
                dataList.subList(0, pointsDataLimit).clear();
            }
            if (!dataList.isEmpty()) {
                ForexDealDataOneMinute oneMinute = caculateNewDealData(dataList);
                newList.add(oneMinute);
            }
        } else {
            ForexDealDataOneMinute oneMinute = caculateNewDealData(dataList);
            newList.add(oneMinute);
        }
        return newList;
    }


    private ForexDealDataOneMinute caculateNewDealData(List<ForexDealDataOneMinute> dataList){
        ForexDealDataOneMinute oneMinute = new ForexDealDataOneMinute();
        BigDecimal high = BigDecimal.ZERO ;
        BigDecimal low = BigDecimal.ZERO ;
        for(int i = 0 ; i < dataList.size() ; i++){
            ForexDealDataOneMinute minute = dataList.get(i);
            if(i == 0){
                high = minute.getHighPrice();
                low = minute.getLowPrice() ;
                oneMinute.setForexCode(minute.getForexCode());
                oneMinute.setOpenPrice(minute.getOpenPrice());
            }
            if(i == dataList.size() - 1 ){
                oneMinute.setDealTime(minute.getDealTime());
                oneMinute.setClosePrice(minute.getClosePrice());
            }

            if(minute.getHighPrice().compareTo(high) > 0){
                high = minute.getHighPrice();
            }

            if(minute.getLowPrice().compareTo(low) < 0){
                low = minute.getLowPrice();
            }
        }
        oneMinute.setHighPrice(high);
        oneMinute.setLowPrice(low);
        return oneMinute ;
    }
}
