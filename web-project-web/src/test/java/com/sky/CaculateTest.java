package com.sky;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sky.core.utils.DateUtils;
import com.sky.model.MessagePriceStatic;
import com.sky.model.StockCompanyNotice;
import com.sky.model.StockDealData;
import com.sky.scheduler.StockCompanyNoticeScheduler;
import com.sky.service.MessagePriceStaticService;
import com.sky.service.StockCompanyNoticeService;
import com.sky.service.StockDealDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by ThinkPad on 2019/9/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CaculateTest {

    @Autowired
    private StockCompanyNoticeService stockCompanyNoticeService;

    @Autowired
    private StockDealDataService stockDealDataService ;

    @Autowired
    private MessagePriceStaticService messagePriceStaticService ;


    @Test
    public void test(){
        try {
            List<StockCompanyNotice> noticeList = stockCompanyNoticeService.selectList(new EntityWrapper<StockCompanyNotice>().where("notice_type not in ('股权激励计划' ,'重大合同' ,'其他')").groupBy("notice_type").orderBy("notice_type"));
            for(StockCompanyNotice notice : noticeList){
                String messageType = notice.getNoticeType();

                List<StockCompanyNotice> list = stockCompanyNoticeService.selectList(new EntityWrapper<StockCompanyNotice>().where("notice_type = {0}" , messageType).groupBy("stock_code,publish_time").orderBy("stock_code,publish_time"));
                for(StockCompanyNotice companyNotice : list){
                    List<MessagePriceStatic> arrList = new ArrayList<>();
                    String pointDay = companyNotice.getPublishTime();
                    List<StockDealData> dataList = stockDealDataService.getPointDayScopeList(companyNotice.getStockCode() , pointDay , "10");
                    Map<Integer ,String> lastMap  = new TreeMap<Integer, String>(
                            new Comparator<Integer>() {
                                public int compare(Integer obj1, Integer obj2) {
                                    // 降序排序
                                    return obj1.compareTo(obj2);
                                }
                            });
                    Map<Integer ,String> furtureMap  = new TreeMap<Integer, String>(
                            new Comparator<Integer>() {
                                public int compare(Integer obj1, Integer obj2) {
                                    // 降序排序
                                    return obj2.compareTo(obj1);
                                }
                            });
                    for(StockDealData dealData : dataList){
                        int days = DateUtils.differentDaysByString(dealData.getDealTime() , pointDay);
                        if(days > 0){
                            lastMap.put(days ,dealData.getDealTime());
                        }

                        if(days == 0){
                            MessagePriceStatic priceStatic = createModel( dealData , messageType , 0);
                            arrList.add(priceStatic);
                        }

                        if(days < 0){
                            furtureMap.put(days ,dealData.getDealTime());
                        }
                    }

                    Set<Integer> keySet = lastMap.keySet();
                    Iterator<Integer> iter = keySet.iterator();
                    int num = 0;
                    while (iter.hasNext()) {
                        Integer key = iter.next();
                        int timeType = 0 ;
                        if(num == 0){
                            timeType = -3 ;
                        }
                        if(num == 1){
                            timeType = -2 ;
                        }
                        if(num == 2){
                            timeType = -1 ;
                        }
                        for(StockDealData dealData : dataList){
                            if(dealData.getDealTime().equals(lastMap.get(key))){
                                MessagePriceStatic priceStatic = createModel( dealData , messageType , timeType);
                                arrList.add(priceStatic);
                            }
                        }

                        num ++;
                        if(num == 3){
                            break;
                        }
                    }

                    Set<Integer> keySet2 = furtureMap.keySet();
                    Iterator<Integer> iter2 = keySet2.iterator();
                    int num2 = 0;
                    while (iter2.hasNext()) {
                        Integer key = iter2.next();
                        int timeType = 0 ;
                        if(num2 == 0){
                            timeType = 1 ;
                        }
                        if(num2 == 1){
                            timeType = 2 ;
                        }
                        if(num2 == 2){
                            timeType = 3 ;
                        }
                        for(StockDealData dealData : dataList){
                            if(dealData.getDealTime().equals(furtureMap.get(key))){
                                MessagePriceStatic priceStatic = createModel( dealData , messageType , timeType);
                                arrList.add(priceStatic);
                            }
                        }
                        num2 ++;
                        if(num2 == 3){
                            break;
                        }
                    }
                    System.out.println(arrList.toString());
                    if(arrList.size() > 0){
                        messagePriceStaticService.insertBatch(arrList);
                    }
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test2(){
        List<StockCompanyNotice> list = stockCompanyNoticeService.selectList(new EntityWrapper<StockCompanyNotice>().where("notice_type = {0}" , "股权激励计划").groupBy("stock_code,publish_time").orderBy("stock_code,publish_time"));
        BigDecimal upper = BigDecimal.ZERO ;
        BigDecimal down = BigDecimal.ZERO ;
        BigDecimal flat = BigDecimal.ZERO ;
        BigDecimal upperPrice = BigDecimal.ZERO ;
        BigDecimal downPrice = BigDecimal.ZERO ;

        BigDecimal maxPrice = BigDecimal.ZERO ;
        BigDecimal minPrice = BigDecimal.ZERO ;

        for(StockCompanyNotice companyNotice : list) {
            String pointDay = companyNotice.getPublishTime();
            List<StockDealData> dataList = stockDealDataService.getPointDayScopeList(companyNotice.getStockCode(), pointDay, null);

            for(StockDealData dealData : dataList){
                MessagePriceStatic priceStatic = new MessagePriceStatic();
                priceStatic.setMessageType("股权激励计划");
                priceStatic.setStockCode(dealData.getStockCode());
                priceStatic.setTimeType(0);

                BigDecimal closePrice = dealData.getClosePrice();
                BigDecimal openPrice = dealData.getOpenPrice();
                BigDecimal diffPrice = closePrice.subtract(openPrice).setScale(2,BigDecimal.ROUND_HALF_UP);



                if(diffPrice.compareTo(BigDecimal.ZERO) > 0){
                    upper = upper.add(BigDecimal.ONE);
                    upperPrice = upperPrice.add(diffPrice);
                    priceStatic.setDirectType(1);
                    priceStatic.setDiffPrice(diffPrice);

                    if(diffPrice.compareTo(maxPrice) > 0){
                        maxPrice = diffPrice ;
                    }
                }

                if(diffPrice.compareTo(BigDecimal.ZERO) == 0){
                    flat = flat.add(BigDecimal.ONE);
                    priceStatic.setDirectType(0);
                    priceStatic.setDiffPrice(diffPrice);
                }

                if(diffPrice.compareTo(BigDecimal.ZERO) < 0){
                    down = down.add(BigDecimal.ONE);
                    downPrice = downPrice.add(diffPrice);
                    priceStatic.setDirectType(-1);
                    priceStatic.setDiffPrice(diffPrice.multiply(BigDecimal.valueOf(-1)));

                    if(diffPrice.compareTo(minPrice) < 0){
                        minPrice = diffPrice ;
                    }
                }
                messagePriceStaticService.insert(priceStatic);

            }
        }
        BigDecimal upperRate = upper.multiply(BigDecimal.valueOf(100)).divide((upper.add(down)), 2, BigDecimal.ROUND_HALF_UP);
        BigDecimal downRate = down.multiply(BigDecimal.valueOf(100)).divide((upper.add(down)), 2, BigDecimal.ROUND_HALF_UP);
        BigDecimal upperAverage = upperPrice.divide(upper, 2, BigDecimal.ROUND_HALF_UP);
        BigDecimal downAverage = downPrice.divide(upper, 2, BigDecimal.ROUND_HALF_UP);
        System.out.println("===========upper===========" + upper);
        System.out.println("===========flat===========" + flat);
        System.out.println("===========down===========" + down);
        System.out.println("===========upperPrice===========" + upperPrice);
        System.out.println("===========downPrice===========" + downPrice);

        System.out.println("===========maxPrice===========" + maxPrice);
        System.out.println("===========minPrice===========" + minPrice);


        System.out.println("===========upperRate===========" + upperRate);
        System.out.println("===========downRate===========" + downRate);
        System.out.println("===========upperAverage===========" + upperAverage);
        System.out.println("===========downAverage===========" + downAverage);
    }


    private MessagePriceStatic createModel(StockDealData dealData ,String messageTitle ,Integer timeType){
        MessagePriceStatic priceStatic = new MessagePriceStatic();
        priceStatic.setMessageType(messageTitle);
        priceStatic.setStockCode(dealData.getStockCode());
        priceStatic.setTimeType(timeType);

        BigDecimal closePrice = dealData.getClosePrice();
        BigDecimal openPrice = dealData.getOpenPrice();
        BigDecimal diffPrice = closePrice.subtract(openPrice).setScale(2,BigDecimal.ROUND_HALF_UP);

        if(diffPrice.compareTo(BigDecimal.ZERO) > 0){
            priceStatic.setDirectType(1);
            priceStatic.setDiffPrice(diffPrice);
        }

        if(diffPrice.compareTo(BigDecimal.ZERO) == 0){
            priceStatic.setDirectType(0);
            priceStatic.setDiffPrice(diffPrice);
        }

        if(diffPrice.compareTo(BigDecimal.ZERO) < 0){
            priceStatic.setDirectType(-1);
            priceStatic.setDiffPrice(diffPrice.multiply(BigDecimal.valueOf(-1)));
        }

        return priceStatic ;
    }
}
