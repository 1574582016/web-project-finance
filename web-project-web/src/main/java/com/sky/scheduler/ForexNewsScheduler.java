package com.sky.scheduler;

import com.sky.core.utils.DateUtils;
import com.sky.service.EconomyNewsStatictisService;
import com.sky.service.ForexNewsStatictisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by ThinkPad on 2019/9/30.
 */
//@Component
@EnableScheduling
public class ForexNewsScheduler {

    private final static Logger logger = LoggerFactory.getLogger(ForexNewsScheduler.class);

    @Autowired
    private ForexNewsStatictisService forexNewsStatictisService ;

    @Scheduled(fixedRate = 1000 * 60 * 30 )
    public void processEveryDayNews() {
        try {
            logger.info("爬取外汇新闻-->>开始时间：" + DateUtils.getDateTime());
            Long start = System.currentTimeMillis();

                for(int page = 0 ; page <= 150 ; page ++){
                    boolean just = forexNewsStatictisService.spiderForexNews(page);
                    if(just){
                        Thread.sleep(200);
                    }else{
                        break;
                    }
                }
            Long end = System.currentTimeMillis();
            logger.info("爬取外汇新闻-->>结束时间：" + DateUtils.getDateTime());
            logger.info("爬取外汇新闻-->>执行时间：" + ((end - start) / 1000) + " 秒");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
