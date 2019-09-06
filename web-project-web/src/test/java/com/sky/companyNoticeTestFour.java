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
public class companyNoticeTestFour {

    private static final long  sleep = 300;

    @Autowired
    private StockCompanyNoticeService stockCompanyNoticeService ;

    @Test
    public void processStockCompanyNotice() throws InterruptedException {
        String bigClass = "财务报告";
        String middleClass = "";
        String url ="";
        for(int j = 1 ; j <= 4 ; j++){
            if(j == 1){
                middleClass = "定期报告";
                for (int i = 1 ; i <= 1000 ; i++) {
                    url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=1&CodeType=1&PageIndex="+ i +"&PageSize=100&jsObj=yKryDOMa&SecNodeType=1&TIME=&rt=52248963";
                    boolean just = stockCompanyNoticeService.spiderStockCompanyNotice(url , bigClass , middleClass);
                    Thread.sleep(sleep);

                    if(just){
                        break;
                    }
                }
            }

            if(j == 2){
                middleClass = "利润分配";
                for (int i = 1 ; i <= 1000 ; i++) {
                    url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=1&CodeType=1&PageIndex="+ i +"&PageSize=100&jsObj=pjlsLIWT&SecNodeType=13&TIME=&rt=52248964";
                    boolean just = stockCompanyNoticeService.spiderStockCompanyNotice(url , bigClass , middleClass);
                    Thread.sleep(sleep);

                    if(just){
                        break;
                    }
                }
            }

            if(j == 3){
                middleClass = "业绩报告";
                for (int i = 1 ; i <= 1000 ; i++) {
                    url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=1&CodeType=1&PageIndex="+ i +"&PageSize=100&jsObj=shoSSvdL&SecNodeType=5&TIME=&rt=52248964";
                    boolean just = stockCompanyNoticeService.spiderStockCompanyNotice(url , bigClass , middleClass);
                    Thread.sleep(sleep);
                    if(just){
                        break;
                    }
                }
            }

            if(j == 4){
                middleClass = "业绩快报";
                for (int i = 1 ; i <= 1000 ; i++) {
                    url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=1&CodeType=1&PageIndex="+ i +"&PageSize=100&jsObj=zQwFlkzb&SecNodeType=6&TIME=&rt=52248966";
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
