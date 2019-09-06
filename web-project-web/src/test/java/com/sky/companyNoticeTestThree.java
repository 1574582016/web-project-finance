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
public class companyNoticeTestThree {

    private static final long  sleep = 300;

    @Autowired
    private StockCompanyNoticeService stockCompanyNoticeService ;

    @Test
    public void processStockCompanyNotice() throws InterruptedException {
        String bigClass = "资产重组";
        String middleClass = "";
        String url ="";
        for(int j = 1 ; j <= 3 ; j++){
            if(j == 1){
                middleClass = "要约收购";
                for (int i = 1 ; i <= 1000 ; i++) {
                    url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=6&CodeType=1&PageIndex="+ i +"&PageSize=100&jsObj=MlXyjZIG&SecNodeType=10&TIME=&rt=52248970";
                    boolean just = stockCompanyNoticeService.spiderStockCompanyNotice(url , bigClass , middleClass);
                    Thread.sleep(sleep);

                    if(just){
                        break;
                    }
                }
            }

            if(j == 2){
                middleClass = "吸收合并";
                for (int i = 1 ; i <= 1000 ; i++) {
                    url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=6&CodeType=1&PageIndex="+ i +"&PageSize=100&jsObj=bTpiJWNq&SecNodeType=11&TIME=&rt=52248971";
                    boolean just = stockCompanyNoticeService.spiderStockCompanyNotice(url , bigClass , middleClass);
                    Thread.sleep(sleep);

                    if(just){
                        break;
                    }
                }
            }

            if(j == 3){
                middleClass = "回购";
                for (int i = 1 ; i <= 1000 ; i++) {
                    url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=6&CodeType=1&PageIndex="+ i +"&PageSize=100&jsObj=bIbPRAPP&SecNodeType=12&TIME=&rt=52248972";
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
