package com.sky.scheduler;

import com.sky.core.utils.DateUtils;
import com.sky.model.IndexDealData;
import com.sky.model.StockCompanySector;
import com.sky.model.StockDealData;
import com.sky.service.IndexDealDataService;
import com.sky.service.StockCompanyProfitService;
import com.sky.service.StockCompanySectorService;
import com.sky.service.StockDealDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by ThinkPad on 2019/11/7.
 */
//@Component
@EnableScheduling
public class IndexDealDataScheduler {

    private final static Logger logger = LoggerFactory.getLogger(IndexDealDataScheduler.class);

    @Autowired
    private IndexDealDataService indexDealDataService ;

    @Autowired
    private StockDealDataService stockDealDataService ;

    @Autowired
    private StockCompanySectorService stockCompanySectorService;

    @Autowired
    private StockCompanyProfitService stockCompanyProfitService ;

    @Scheduled(cron = "0 21 12 * * ?")
    public void test0(){
        List<StockCompanySector> list = stockCompanySectorService.selectList(null);
        for(StockCompanySector stockCode : list) {
            String mk = "1";
            if (!stockCode.getStockCode().substring(0, 1).equals("6")) {
                mk = "2";
            }
            List<StockDealData> dataList = stockDealDataService.spiderStockDealData(1, stockCode.getStockCode(), mk);
            if(dataList.size() > 0){
                stockDealDataService.insertBatch(dataList);
            }
        }
    }

//    @Scheduled(cron = "0 0 16 * * ?")
    public void spiderIndexDealData() throws InterruptedException, ExecutionException {
        try {
            logger.info("爬取指数交易数据-->>开始时间：" + DateUtils.getDateTime());
            Long start = System.currentTimeMillis();
            String klt = "";
            for(int t = 1 ; t <=7 ;t++){
                switch (t){
                    case 1 : klt = "101" ;break;
                    case 2 : klt = "102" ;break;
                    case 3 : klt = "103" ;break;

                    case 4 : klt = "60" ;break;
                    case 5 : klt = "30" ;break;
                    case 6 : klt = "15" ;break;
                    case 7 : klt = "5" ;break;
                }
                List<IndexDealData> singleList = indexDealDataService.spiderIndexDealData(t , "1.000001" ,klt);
                if(singleList.size() > 0){
                    indexDealDataService.insertBatch(singleList);
                }
                Thread.sleep(300);
            }
            Long end = System.currentTimeMillis();
            logger.info("爬取指数交易数据-->>结束时间：" + DateUtils.getDateTime());
            logger.info("爬取指数交易数据-->>执行时间：" + ((end - start) / 1000) + " 秒");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
