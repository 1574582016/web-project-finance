package com.sky;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sky.core.utils.DateUtils;
import com.sky.model.StockCompanySector;
import com.sky.model.StockDealData;
import com.sky.service.StockCompanySectorService;
import com.sky.service.StockDealDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ThinkPad on 2019/11/21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SplitTimeTest {

    @Autowired
    private StockDealDataService stockDealDataService ;

    @Autowired
    private StockCompanySectorService stockCompanySectorService ;

    @Test
    public void test(){
       String startDay = "2015-01-01";
       for(int i = 0 ; i < 1000 ; i ++){
           Date pointDate = DateUtils.addDays(DateUtils.parseDate(startDay , "yyyy-MM-dd") , i);
           String pointDay = DateUtils.formatDate( pointDate, "yyyy-MM-dd");
           JSONObject jsonObject = DateUtils.getYearMonthWeekDay(pointDay);
           System.out.println(pointDay + "================" + jsonObject.getInteger("year") + "年" + jsonObject.getInteger("month") + "月" + jsonObject.getInteger("week") + "周" + jsonObject.getInteger("day") + "天");
       }
    }

    @Test
    public void test01(){
        List<StockDealData> list = stockDealDataService.selectList(new EntityWrapper<StockDealData>().where("stock_code = '000333' and deal_period = 1 and deal_time >= '2015-01-01'"));
        for(StockDealData dealData : list){
            String dealTime = dealData.getDealTime() ;
            System.out.println(dealTime +"=========================="+ DateUtils.getYearMonthWeekDay(dealTime).toString());
        }
    }

    @Test
    public void test02(){
        String pointDay = "2019-11-22";
        Date pointDate = DateUtils.parseDate(pointDay , "yyyy-MM-dd");
        String year = DateUtils.getYear(pointDate);
        String month = DateUtils.getMonth(pointDate);
        String week = DateUtils.getWeek(pointDate);
        String day = DateUtils.getDay(pointDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(pointDate);
        int monthWeek = calendar.get(Calendar.WEEK_OF_MONTH);
        System.out.println(year + "================" + month + "=============" + monthWeek + "=================" + week + "==================" + day);

    }


    @Test
    public void test03(){
        List<StockCompanySector> sectorList = stockCompanySectorService.selectList(new EntityWrapper<StockCompanySector>().where("LEFT(stock_code,2) != 68"));
        for(StockCompanySector sector : sectorList){
            List<StockDealData> list = stockDealDataService.selectList(new EntityWrapper<StockDealData>().where("stock_code = {0} and deal_period = 1 " ,sector.getStockCode()));
            for(StockDealData dealData : list){
                String dealTime = dealData.getDealTime() ;
                JSONObject jsonObject = DateUtils.getYearMonthWeekDay(dealTime);
                dealData.setPointYear(jsonObject.getInteger("year"));
                dealData.setPointMonth(jsonObject.getInteger("month"));
                dealData.setPointWeek(jsonObject.getInteger("week"));
                dealData.setPointDay(jsonObject.getInteger("day"));
                stockDealDataService.updateById(dealData);
            }
        }
    }


}
