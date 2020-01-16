package com.sky.test;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sky.core.utils.DateUtils;
import com.sky.model.ForexDealData;
import com.sky.model.ForexDealDataFiveMinute;
import com.sky.model.ForexDealDataOneMinute;
import com.sky.model.StockDealData;
import com.sky.service.ForexDealDataOneMinuteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by ThinkPad on 2020/1/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test08 {

    @Autowired
    private ForexDealDataOneMinuteService forexDealDataOneMinuteService ;

    @Test
    public void test(){
        List<ForexDealDataOneMinute> dataList = forexDealDataOneMinuteService.selectList(new EntityWrapper<ForexDealDataOneMinute>().orderBy("deal_time asc ").last("LIMIT 100"));

        int pointsDataLimit = 5;//限制条数
        Integer size = dataList.size();
        //判断是否有必要分批
        if (pointsDataLimit < size) {
            int part = size / pointsDataLimit;//分批数
            System.out.println("共有 ： " + size + "条，！" + " 分为 ：" + part + "批");
            for (int i = 0; i < part; i++) {
                List<ForexDealDataOneMinute> listPage = dataList.subList(0, pointsDataLimit);
                System.out.println("========="+ i +"============" + caculateNewDealData(listPage));

                //剔除
                dataList.subList(0, pointsDataLimit).clear();
            }
            if (!dataList.isEmpty()) {
                System.out.println("=====================" + dataList.toString());
//                saveStockDealData(t , dataList);
            }
        } else {
            System.out.println("=====================" + dataList.toString());
//            saveStockDealData(t , dataList);
        }
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
