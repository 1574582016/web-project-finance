package com.sky.scheduler;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sky.core.utils.DateUtils;
import com.sky.model.StockCode;
import com.sky.service.StockCodeService;
import com.sky.service.StockCompanyAnalyseService;
import com.sky.service.StockCompanyBaseService;
import com.sky.service.StockCompanyProductService;
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
public class StockCompanyBaceScheduler {

    @Autowired
    private StockCodeService stockCodeService ;

    @Autowired
    private StockCompanyBaseService stockCompanyBaseService ;

    @Autowired
    private StockCompanyProductService stockCompanyProductService ;

    @Autowired
    private StockCompanyAnalyseService stockCompanyAnalyseService ;

    private static final long  sleep = 300;

    @Scheduled(cron = "0 0 11 * * ?")
    public void processStockCompanyBace() throws InterruptedException {
        List<StockCode> codeList = stockCodeService.selectList(new EntityWrapper<StockCode>().where("create_time regexp {0}" , DateUtils.getDate()));
        for (StockCode stockCode : codeList) {
            stockCompanyBaseService.spiderStockCompanyBase(stockCode.getStockMarket()+stockCode.getStockCode());
            Thread.sleep(sleep);
        }
    }

    @Scheduled(cron = "0 0 11 * * ?")
    public void processStockCompanyProduct() throws InterruptedException {
        List<StockCode> codeList = stockCodeService.selectList(new EntityWrapper<StockCode>().where("create_time regexp {0}" , DateUtils.getDate()));
        for (StockCode stockCode : codeList) {
            stockCompanyProductService.spiderStockCompanyProduct(stockCode.getStockMarket() , stockCode.getStockCode());
            Thread.sleep(sleep);
        }
    }

    @Scheduled(cron = "0 0 11 * * ?")
    public void processStockCompanyAnalyse() throws InterruptedException {
        List<StockCode> codeList = stockCodeService.selectList(new EntityWrapper<StockCode>().where("create_time regexp {0}" , DateUtils.getDate()));
        for (StockCode stockCode : codeList) {
            stockCompanyAnalyseService.spiderStockCompanyAnalyse(stockCode.getStockMarket() , stockCode.getStockCode());
            Thread.sleep(sleep);
        }
    }
}
