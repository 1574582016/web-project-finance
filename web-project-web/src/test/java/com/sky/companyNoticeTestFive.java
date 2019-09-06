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
public class companyNoticeTestFive {

    private static final long  sleep = 300;

    @Autowired
    private StockCompanyNoticeService stockCompanyNoticeService ;

    @Test
    public void processStockCompanyNotice() throws InterruptedException {
        String bigClass = "";
        String middleClass = "";
        String url ="";
        for(int j = 1 ; j <= 3 ; j++){
            if(j == 1){
                bigClass = "风险提示";
                for (int i = 1 ; i <= 1000 ; i++) {
                    url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=3&CodeType=1&PageIndex="+ i +"&PageSize=100&jsObj=XQMcKBjl&SecNodeType=0&TIME=&rt=52248944";
                    boolean just = stockCompanyNoticeService.spiderStockCompanyNotice(url , bigClass , middleClass);
                    Thread.sleep(sleep);

                    if(just){
                        break;
                    }
                }
            }

            if(j == 2){
                bigClass = "信息变更";
                for (int i = 1 ; i <= 1000 ; i++) {
                    url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=4&CodeType=1&PageIndex="+ i +"&PageSize=100&jsObj=WNyrOKvn&SecNodeType=0&TIME=&rt=52248946";
                    boolean just = stockCompanyNoticeService.spiderStockCompanyNotice(url , bigClass , middleClass);
                    Thread.sleep(sleep);

                    if(just){
                        break;
                    }
                }
            }

            if(j == 3){
                bigClass = "持股变动";
                for (int i = 1 ; i <= 1000 ; i++) {
                    url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=7&CodeType=1&PageIndex="+ i +"&PageSize=100&jsObj=fOGcQIzJ&SecNodeType=0&TIME=&rt=52248947";
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
