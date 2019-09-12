package com.sky.scheduler;

import com.sky.core.utils.DateUtils;
import com.sky.service.EconomyNewsStatictisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by ThinkPad on 2019/9/11.
 */
@Component
@EnableScheduling
public class EconomyNewsScheduler {

    private final static Logger logger = LoggerFactory.getLogger(EconomyNewsScheduler.class);

    @Autowired
    private EconomyNewsStatictisService economyNewsStatictisService ;

    @Scheduled(fixedRate = 1000 * 60 * 30 )
    public void processEveryDayNews() {
        try {
            logger.info("爬取时事新闻-->>开始时间：" + DateUtils.getDateTime());
            Long start = System.currentTimeMillis();
            for(int type = 1 ; type <= 5 ; type ++){
                for(int page = 1 ; page <= 1000 ; page ++){
                    boolean just = economyNewsStatictisService.processEveryDayNews(type ,page);
                    if(just){
                        Thread.sleep(200);
                    }else{
                        break;
                    }
                }
            }
            Long end = System.currentTimeMillis();
            logger.info("爬取时事新闻-->>结束时间：" + DateUtils.getDateTime());
            logger.info("爬取时事新闻-->>执行时间：" + ((end - start) / 1000) + " 秒");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
