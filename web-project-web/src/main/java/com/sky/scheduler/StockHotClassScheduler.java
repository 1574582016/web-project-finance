package com.sky.scheduler;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sky.core.utils.DateUtils;
import com.sky.model.StockHotClass;
import com.sky.model.StockMarketClass;
import com.sky.service.StockHotClassService;
import com.sky.service.StockMarketClassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by ThinkPad on 2019/11/7.
 */
//@Component
@EnableScheduling
public class StockHotClassScheduler {

    private final static Logger logger = LoggerFactory.getLogger(StockHotClassScheduler.class);

    @Autowired
    private StockMarketClassService stockMarketClassService ;


    @Autowired
    private StockHotClassService stockHotClassService ;

    @Scheduled(fixedRate = 1000 * 60 * 60 )
    public void spiderStockMarketClass() {
        try {
            logger.info("爬取行业热点种类-->>开始时间：" + DateUtils.getDateTime());
            Long start = System.currentTimeMillis();
            stockMarketClassService.spiderStockMarketClass();
            Long end = System.currentTimeMillis();
            logger.info("爬取行业热点种类-->>结束时间：" + DateUtils.getDateTime());
            logger.info("爬取行业热点种类-->>执行时间：" + ((end - start) / 1000) + " 秒");
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    @Scheduled(fixedRate = 1000 * 60 * 90 )
    public void spiderStockHotClass(){
        EntityWrapper<StockMarketClass> entityWrapper = new EntityWrapper();
        entityWrapper.where("class_type = '概念板块'");
        List<StockMarketClass> list = stockMarketClassService.selectList(entityWrapper);
        for(StockMarketClass marketClass : list){
            List<StockHotClass> subList = stockHotClassService.spiderStockHotClass(marketClass.getClassCode() , marketClass.getClassName());
            if(subList.size() > 0){
                stockHotClassService.insertBatch(subList);
            }
        }

    }
}
