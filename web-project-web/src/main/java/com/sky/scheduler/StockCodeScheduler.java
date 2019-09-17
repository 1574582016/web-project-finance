package com.sky.scheduler;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sky.model.StockMarketClass;
import com.sky.service.StockCodeService;
import com.sky.service.StockMarketClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by ThinkPad on 2019/9/16.
 */
//@Component
@EnableScheduling
public class StockCodeScheduler {

    @Autowired
    private StockMarketClassService stockMarketClassService ;

    @Autowired
    private StockCodeService stockCodeService ;

    @Scheduled(fixedRate = 1000 * 60 * 120 )
    public void processStockCode(){
        EntityWrapper<StockMarketClass> entityWrapper = new EntityWrapper();
        entityWrapper.where("class_type = '行业板块'");
        List<StockMarketClass> codeList = stockMarketClassService.selectList(entityWrapper);
        for(StockMarketClass marketClass : codeList){
            stockCodeService.spiderStockCode(marketClass.getClassCode() ,marketClass.getClassName());
        }
    }
}
