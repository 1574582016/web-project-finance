package com.sky;

import com.sky.service.StockCompanyNoticeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by ThinkPad on 2019/9/4.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class companyNoticeTestOne {

    private static final long  sleep = 300;

    @Autowired
    private StockCompanyNoticeService stockCompanyNoticeService ;

    @Test
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
                        break;
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
                        break;
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
                        break;
                    }
                }
            }
        }
    }


}
