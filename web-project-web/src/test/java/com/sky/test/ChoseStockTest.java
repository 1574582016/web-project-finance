package com.sky.test;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sky.model.StockDealData;
import com.sky.service.StockDealDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2020/1/30/030.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ChoseStockTest {

    @Autowired
    private StockDealDataService stockDealDataService ;

    @Test
    public void test(){
        final BigDecimal rate = BigDecimal.valueOf(0.1);
        List<StockDealData> list = stockDealDataService.selectList(new EntityWrapper<StockDealData>().where("stock_code = '002315'").orderBy("deal_time" ,false));
        BigDecimal currentPrice = list.get(0).getClosePrice();
        BigDecimal oneHightPrice = BigDecimal.ZERO ;
        String oneDealTime = "";
        BigDecimal oneJustPrice = currentPrice.multiply(rate).setScale(2 , BigDecimal.ROUND_HALF_UP);
        BigDecimal towHightPrice = BigDecimal.ZERO ;
        String towDealTime = "";
        for(int i = 1 ; i < list.size() ; i ++){
            StockDealData dealData = list.get(i);
            BigDecimal lastPrice = getHightPrice(dealData);
            if(oneHightPrice.compareTo(BigDecimal.ZERO) == 0){
                if(lastPrice.compareTo(currentPrice.add(oneJustPrice)) > 0){
                    StockDealData oneDealData = getMoreHightPrice(lastPrice.setScale(0,BigDecimal.ROUND_DOWN) , list , i + 1 , dealData);
                    oneHightPrice = getHightPrice(oneDealData) ;
                    oneDealTime = oneDealData.getDealTime();
                }
            }else {
                BigDecimal towJustPrice = oneHightPrice.multiply(rate).setScale(2,BigDecimal.ROUND_HALF_UP);
                if(lastPrice.compareTo(oneHightPrice.add(towJustPrice)) > 0){
                    towHightPrice = lastPrice ;
                    towDealTime = dealData.getDealTime();
                }
            }
            if(towHightPrice.compareTo(BigDecimal.ZERO) > 0){
                break;
            }
        }
        System.out.println("=============" + currentPrice);
        System.out.println(oneDealTime + "===============" + oneHightPrice + "==============" + towDealTime + "===============" + towHightPrice);

    }

    private BigDecimal getHightPrice(StockDealData dealData){
          BigDecimal openPrice = dealData.getOpenPrice();
          BigDecimal closePrice = dealData.getClosePrice();
          BigDecimal resultPrice = BigDecimal.ZERO ;
          if(openPrice.compareTo(closePrice) >= 0){
              resultPrice = openPrice ;
          }else {
              resultPrice = closePrice ;
          }
          return resultPrice ;
    }

    private StockDealData getMoreHightPrice(BigDecimal nowPrice , List<StockDealData> list ,int num , StockDealData stockDealData){
        System.out.println("========nowPrice========" + nowPrice);
        BigDecimal justPrice = nowPrice ;
        boolean just = true ;
        for(int i = num ; i < list.size() ; i ++){
            StockDealData dealData = list.get(i);
            BigDecimal lastPrice = getHightPrice(dealData).setScale(0,BigDecimal.ROUND_DOWN);
            System.out.println("========lastPrice========" + lastPrice);
            if(lastPrice.compareTo(justPrice) > 0){
                justPrice = getHightPrice(dealData);
                just = true ;
            }
            if(!just){
                return dealData ;
            }
        }
        return stockDealData;
    }
}
