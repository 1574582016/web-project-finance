package com.sky.scheduler;

import com.sky.service.StockCompanyNoticeService;
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

    private static final long  sleep = 300;

    @Autowired
    private StockCompanyNoticeService stockCompanyNoticeService ;

    @Scheduled(fixedRate = 1000 * 60 * 5 )
    public void processStockCompanyNotice() throws InterruptedException {
        try {
            processStockCompanyNotice1();
            processStockCompanyNotice2();
            processStockCompanyNotice3();
            processStockCompanyNotice4();
            processStockCompanyNotice5();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public void processStockCompanyNotice1() throws InterruptedException {
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


    public void processStockCompanyNotice2() throws InterruptedException {
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


    public void processStockCompanyNotice3() throws InterruptedException {
        String bigClass = "融资公告";
        String middleClass = "";
        String url ="";
        for(int j = 1 ; j <= 3 ; j++){
            if(j == 1){
                middleClass = "增发";
                for (int i = 1 ; i <= 1000 ; i++) {
                    url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=2&CodeType=1&PageIndex="+ i +"&PageSize=100&jsObj=ZyMUZLii&SecNodeType=3&TIME=&rt=52248967";
                    boolean just = stockCompanyNoticeService.spiderStockCompanyNotice(url , bigClass , middleClass);
                    Thread.sleep(sleep);

                    if(just){
                        break;
                    }
                }
            }

            if(j == 2){
                middleClass = "新股发行";
                for (int i = 1 ; i <= 1000 ; i++) {
                    url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=2&CodeType=1&PageIndex="+ i +"&PageSize=100&jsObj=eWwiIBWO&SecNodeType=2&TIME=&rt=52248968";
                    boolean just = stockCompanyNoticeService.spiderStockCompanyNotice(url , bigClass , middleClass);
                    Thread.sleep(sleep);

                    if(just){
                        break;
                    }
                }
            }

            if(j == 3){
                middleClass = "配股";
                for (int i = 1 ; i <= 1000 ; i++) {
                    url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=2&CodeType=1&PageIndex="+ i +"&PageSize=100&jsObj=YjRTOdkP&SecNodeType=4&TIME=&rt=52248969";
                    boolean just = stockCompanyNoticeService.spiderStockCompanyNotice(url , bigClass , middleClass);
                    Thread.sleep(sleep);
                    if(just){
                        break;
                    }
                }
            }
        }
    }


    public void processStockCompanyNotice4() throws InterruptedException {
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


    public void processStockCompanyNotice5() throws InterruptedException {
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
