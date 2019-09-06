package com.sky.scheduler;

import com.sky.service.StockCompanyNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by ThinkPad on 2019/9/4.
 */
//@Component
public class StockCompanyNoticeScheduler {

    private static final long  sleep = 300;

    @Autowired
    private StockCompanyNoticeService stockCompanyNoticeService ;

    @Scheduled(fixedRate = 1000 * 60 * 60 )
    public void processStockCompanyNotice() throws InterruptedException {
        String bigClass = "重大事件";
        String middleClass = "";
        String url ="";
        for(int j = 1 ; j <= 3 ; j++){
            if(j == 1){
                middleClass = "重大合同";
                for (int i = 1 ; i <= 1000 ; i++) {
                    url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=5&CodeType=1&PageIndex="+ i +"&PageSize=100&jsObj=CDQEMdrj&SecNodeType=7&TIME=&rt=52248959";
                    boolean just = stockCompanyNoticeService.spiderStockCompanyNotice(url , bigClass , middleClass);
                    Thread.sleep(sleep);

                    if(just){
                        continue;
                    }
                }
            }

            if(j == 2){
                middleClass = "投资相关";
                for (int i = 1 ; i <= 1000 ; i++) {
                    url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=5&CodeType=1&PageIndex="+ i +"&PageSize=100&jsObj=ozcAWkTr&SecNodeType=8&TIME=&rt=52248960";
                    boolean just = stockCompanyNoticeService.spiderStockCompanyNotice(url , bigClass , middleClass);
                    Thread.sleep(sleep);

                    if(just){
                        continue;
                    }
                }
            }

            if(j == 3){
                middleClass = "股权激励";
                for (int i = 1 ; i <= 1000 ; i++) {
                    url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=5&CodeType=1&PageIndex="+ i +"&PageSize=100&jsObj=bURaPnRJ&SecNodeType=9&TIME=&rt=52248961";
                    boolean just = stockCompanyNoticeService.spiderStockCompanyNotice(url , bigClass , middleClass);
                    Thread.sleep(sleep);
                    if(just){
                        continue;
                    }
                }
            }
        }
    }
}
