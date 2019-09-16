package com.sky.scheduler;

import com.sky.core.utils.DateUtils;
import com.sky.service.EconomyNewsStatictisService;
import com.sky.service.StockCompanyNoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by ThinkPad on 2019/9/4.
 */
//@Component
@EnableScheduling
public class StockCompanyNoticeScheduler {

    private final static Logger logger = LoggerFactory.getLogger(StockCompanyNoticeScheduler.class);

    private static final long  sleep = 300;

    @Autowired
    private StockCompanyNoticeService stockCompanyNoticeService ;

    @Scheduled(fixedRate = 1000 * 60 * 60 )
    public void processStockCompanyNotice() {
        try {
            logger.info("爬取公告信息-->>开始时间：" + DateUtils.getDateTime());
            Long start = System.currentTimeMillis();
            for(int bigType = 1 ; bigType <= 7 ; bigType++){
                for(int middleType = 1 ; middleType <= 4 ; middleType ++){
                    for(int page = 1 ; page <= 1000 ; page++){
                        boolean just = stockCompanyNoticeService.spiderStockCompanyNotice(bigType , middleType ,page);
                        System.out.println("=======================" + just);
                        if(just){
                            Thread.sleep(200);
                        }else{
                            break;
                        }
                    }
                }
            }
            Long end = System.currentTimeMillis();
            logger.info("爬取公告信息-->>结束时间：" + DateUtils.getDateTime());
            logger.info("爬取公告信息-->>执行时间：" + ((end - start) / 1000) + " 秒");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
