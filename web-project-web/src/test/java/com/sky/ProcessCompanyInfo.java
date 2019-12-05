package com.sky;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sky.core.consts.SpiderUrlConst;
import com.sky.core.utils.DateUtils;
import com.sky.core.utils.SpiderUtils;
import com.sky.model.*;
import com.sky.service.*;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThinkPad on 2019/5/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessCompanyInfo {

    private static final String sector = "综合行业";

    private static final long  sleep = 300;

    private static final String sectorCode = "BK05391";

    @Autowired
    private StockCodeService stockCodeService ;

    @Autowired
    private StockCompanyBaseService stockCompanyBaseService ;

    @Autowired
    private StockCompanyProductService stockCompanyProductService ;

    @Autowired
    private StockCompanyAnalyseService stockCompanyAnalyseService ;

    @Autowired
    private StockMarketClassService stockMarketClassService ;

    @Autowired
    private StockCompanyHotPointService stockCompanyHotPointService ;

    @Autowired
    private StockCompanyNoticeService stockCompanyNoticeService ;

    @Autowired
    private StockCompanySectorService stockCompanySectorService;

    @Test
    public void processStockCode(){
        EntityWrapper<StockMarketClass> entityWrapper = new EntityWrapper();
        entityWrapper.where("class_type = '行业板块'");
        List<StockMarketClass> codeList = stockMarketClassService.selectList(entityWrapper);
        for(StockMarketClass marketClass : codeList){
            stockCodeService.spiderStockCode(marketClass.getClassCode() ,marketClass.getClassName());
        }
    }

    @Test
    public void processStockCompanyBace() throws InterruptedException {
        List<StockCompanySector> codeList = stockCompanySectorService.selectList(new EntityWrapper<StockCompanySector>().where("stock_code NOT IN (SELECT stock_a_code FROM stock_company_base WHERE stock_a_code IS NOT NULL)"));
        for (StockCompanySector sector : codeList) {
            String stockCode = sector.getStockCode() ;
            String str = stockCode.substring(0,1);
            System.out.println(stockCode + "=======================" + str);
            String market = "sh";
            if(str.equals("0") || str.equals("3")){
                market = "sz";
            }
            stockCompanyBaseService.spiderStockCompanyBase(market+stockCode);
            Thread.sleep(sleep);
        }

    }

    @Test
    public void processStockCompanyProduct() throws InterruptedException {
        List<StockCompanySector> sectorList = stockCompanySectorService.selectList(null);
        for (StockCompanySector stockCode : sectorList) {
            String market = "sz";
            if(stockCode.getStockCode().substring(0,1).equals("6")){
               market = "sh";
            }
            stockCompanyProductService.spiderStockCompanyProduct(market , stockCode.getStockCode());
            Thread.sleep(sleep);
        }
    }

    @Test
    public void processStockCompanyProduct2() throws InterruptedException {
        List<StockCode> codeList = stockCodeService.getEmptyStockProdectList(null);
        for (StockCode stockCode : codeList) {
            stockCompanyProductService.spiderStockCompanyProduct(stockCode.getStockMarket() , stockCode.getStockCode());
            Thread.sleep(sleep);
        }
    }

    @Test
    public void processStockCompanyAnalyse() throws InterruptedException {
        List<StockCode> codeList = stockCodeService.selectList(new EntityWrapper<StockCode>().where("create_time regexp {0}" , DateUtils.getDate()));
        for (StockCode stockCode : codeList) {
            stockCompanyAnalyseService.spiderStockCompanyAnalyse(stockCode.getStockMarket() , stockCode.getStockCode());
            Thread.sleep(sleep);
        }
    }

    @Test
    public void processStockCompanyAnalyse2() throws InterruptedException {
        List<StockCode> codeList = stockCodeService.getEmptyStockAnalyseList(null);
        for (StockCode stockCode : codeList) {
            stockCompanyAnalyseService.spiderStockCompanyAnalyse(stockCode.getStockMarket() , stockCode.getStockCode());
            Thread.sleep(sleep);
        }
    }

    @Test
    public void processMenu(){
        stockMarketClassService.spiderStockMarketClass();
    }

    @Test
    public void processHotPoint(){
        List<StockCompanyBase> list = stockCompanyBaseService.selectList(new EntityWrapper<StockCompanyBase>().where("stock_plate_class = 'A'"));
        List<StockCompanyHotPoint> hotList = new ArrayList<>();
        for(StockCompanyBase companyBase : list){
           String subjectMatter = companyBase.getCompanySubjectMatter();
            if(StringUtils.isNotBlank(subjectMatter)){
                String[] matters= subjectMatter.split(" ") ;
                StockCompanyHotPoint hotPoint = new StockCompanyHotPoint();
                for(String name : matters){
                    hotPoint.setStockCode(companyBase.getStockACode());
                    hotPoint.setPointName(name);
                    hotList.add(hotPoint);
                }
            }
        }
        stockCompanyHotPointService.insertBatch(hotList);
    }


    @Test
    public void processStockCompanyNotice0() throws InterruptedException {
        try{
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
        }catch (Exception e){
            e.printStackTrace();
        }
    }

//    @Test
//    public void processStockCompanyNotice1() throws InterruptedException {
//        String bigClass = "重大事件";
//        String middleClass = "";
//        String url ="";
//        for(int j = 1 ; j <= 3 ; j++){
//            if(j == 1){
//                middleClass = "重大合同";
//                for (int i = 1 ; i <= 1000 ; i++) {
//                    url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=5&CodeType=1&PageIndex="+ i +"&PageSize=100&jsObj=CDQEMdrj&SecNodeType=7&TIME=&rt=52248959";
//                    boolean just = stockCompanyNoticeService.spiderStockCompanyNotice(url , bigClass , middleClass);
//                    Thread.sleep(sleep);
//
//                    if(just){
//                        break;
//                    }
//                }
//            }
//
//            if(j == 2){
//                middleClass = "投资相关";
//                for (int i = 1 ; i <= 1000 ; i++) {
//                    url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=5&CodeType=1&PageIndex="+ i +"&PageSize=100&jsObj=ozcAWkTr&SecNodeType=8&TIME=&rt=52248960";
//                    boolean just = stockCompanyNoticeService.spiderStockCompanyNotice(url , bigClass , middleClass);
//                    Thread.sleep(sleep);
//
//                    if(just){
//                        break;
//                    }
//                }
//            }
//
//            if(j == 3){
//                middleClass = "股权激励";
//                for (int i = 1 ; i <= 1000 ; i++) {
//                    url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=5&CodeType=1&PageIndex="+ i +"&PageSize=100&jsObj=bURaPnRJ&SecNodeType=9&TIME=&rt=52248961";
//                    boolean just = stockCompanyNoticeService.spiderStockCompanyNotice(url , bigClass , middleClass);
//                    Thread.sleep(sleep);
//                    if(just){
//                        break;
//                    }
//                }
//            }
//        }
//    }
//
//    @Test
//    public void processStockCompanyNotice2() throws InterruptedException {
//        String bigClass = "财务报告";
//        String middleClass = "";
//        String url ="";
//        for(int j = 1 ; j <= 4 ; j++){
//            if(j == 1){
//                middleClass = "定期报告";
//                for (int i = 1 ; i <= 1000 ; i++) {
//                    url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=1&CodeType=1&PageIndex="+ i +"&PageSize=100&jsObj=yKryDOMa&SecNodeType=1&TIME=&rt=52248963";
//                    boolean just = stockCompanyNoticeService.spiderStockCompanyNotice(url , bigClass , middleClass);
//                    Thread.sleep(sleep);
//
//                    if(just){
//                        break;
//                    }
//                }
//            }
//
//            if(j == 2){
//                middleClass = "利润分配";
//                for (int i = 1 ; i <= 1000 ; i++) {
//                    url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=1&CodeType=1&PageIndex="+ i +"&PageSize=100&jsObj=pjlsLIWT&SecNodeType=13&TIME=&rt=52248964";
//                    boolean just = stockCompanyNoticeService.spiderStockCompanyNotice(url , bigClass , middleClass);
//                    Thread.sleep(sleep);
//
//                    if(just){
//                        break;
//                    }
//                }
//            }
//
//            if(j == 3){
//                middleClass = "业绩报告";
//                for (int i = 1 ; i <= 1000 ; i++) {
//                    url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=1&CodeType=1&PageIndex="+ i +"&PageSize=100&jsObj=shoSSvdL&SecNodeType=5&TIME=&rt=52248964";
//                    boolean just = stockCompanyNoticeService.spiderStockCompanyNotice(url , bigClass , middleClass);
//                    Thread.sleep(sleep);
//                    if(just){
//                        break;
//                    }
//                }
//            }
//
//            if(j == 4){
//                middleClass = "业绩快报";
//                for (int i = 1 ; i <= 1000 ; i++) {
//                    url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=1&CodeType=1&PageIndex="+ i +"&PageSize=100&jsObj=zQwFlkzb&SecNodeType=6&TIME=&rt=52248966";
//                    boolean just = stockCompanyNoticeService.spiderStockCompanyNotice(url , bigClass , middleClass);
//                    Thread.sleep(sleep);
//                    if(just){
//                        break;
//                    }
//                }
//            }
//
//        }
//    }
//
//    @Test
//    public void processStockCompanyNotice3() throws InterruptedException {
//        String bigClass = "融资公告";
//        String middleClass = "";
//        String url ="";
//        for(int j = 1 ; j <= 3 ; j++){
//            if(j == 1){
//                middleClass = "增发";
//                for (int i = 1 ; i <= 1000 ; i++) {
//                    url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=2&CodeType=1&PageIndex="+ i +"&PageSize=100&jsObj=ZyMUZLii&SecNodeType=3&TIME=&rt=52248967";
//                    boolean just = stockCompanyNoticeService.spiderStockCompanyNotice(url , bigClass , middleClass);
//                    Thread.sleep(sleep);
//
//                    if(just){
//                        break;
//                    }
//                }
//            }
//
//            if(j == 2){
//                middleClass = "新股发行";
//                for (int i = 1 ; i <= 1000 ; i++) {
//                    url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=2&CodeType=1&PageIndex="+ i +"&PageSize=100&jsObj=eWwiIBWO&SecNodeType=2&TIME=&rt=52248968";
//                    boolean just = stockCompanyNoticeService.spiderStockCompanyNotice(url , bigClass , middleClass);
//                    Thread.sleep(sleep);
//
//                    if(just){
//                        break;
//                    }
//                }
//            }
//
//            if(j == 3){
//                middleClass = "配股";
//                for (int i = 1 ; i <= 1000 ; i++) {
//                    url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=2&CodeType=1&PageIndex="+ i +"&PageSize=100&jsObj=YjRTOdkP&SecNodeType=4&TIME=&rt=52248969";
//                    boolean just = stockCompanyNoticeService.spiderStockCompanyNotice(url , bigClass , middleClass);
//                    Thread.sleep(sleep);
//                    if(just){
//                        break;
//                    }
//                }
//            }
//        }
//    }
//
//    @Test
//    public void processStockCompanyNotice4() throws InterruptedException {
//        String bigClass = "资产重组";
//        String middleClass = "";
//        String url ="";
//        for(int j = 1 ; j <= 3 ; j++){
//            if(j == 1){
//                middleClass = "要约收购";
//                for (int i = 1 ; i <= 1000 ; i++) {
//                    url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=6&CodeType=1&PageIndex="+ i +"&PageSize=100&jsObj=MlXyjZIG&SecNodeType=10&TIME=&rt=52248970";
//                    boolean just = stockCompanyNoticeService.spiderStockCompanyNotice(url , bigClass , middleClass);
//                    Thread.sleep(sleep);
//
//                    if(just){
//                        break;
//                    }
//                }
//            }
//
//            if(j == 2){
//                middleClass = "吸收合并";
//                for (int i = 1 ; i <= 1000 ; i++) {
//                    url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=6&CodeType=1&PageIndex="+ i +"&PageSize=100&jsObj=bTpiJWNq&SecNodeType=11&TIME=&rt=52248971";
//                    boolean just = stockCompanyNoticeService.spiderStockCompanyNotice(url , bigClass , middleClass);
//                    Thread.sleep(sleep);
//
//                    if(just){
//                        break;
//                    }
//                }
//            }
//
//            if(j == 3){
//                middleClass = "回购";
//                for (int i = 1 ; i <= 1000 ; i++) {
//                    url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=6&CodeType=1&PageIndex="+ i +"&PageSize=100&jsObj=bIbPRAPP&SecNodeType=12&TIME=&rt=52248972";
//                    boolean just = stockCompanyNoticeService.spiderStockCompanyNotice(url , bigClass , middleClass);
//                    Thread.sleep(sleep);
//                    if(just){
//                        break;
//                    }
//                }
//            }
//        }
//    }
//
//    @Test
//    public void processStockCompanyNotice5() throws InterruptedException {
//        String bigClass = "";
//        String middleClass = "";
//        String url ="";
//        for(int j = 1 ; j <= 3 ; j++){
//            if(j == 1){
//                bigClass = "风险提示";
//                for (int i = 1 ; i <= 1000 ; i++) {
//                    url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=3&CodeType=1&PageIndex="+ i +"&PageSize=100&jsObj=XQMcKBjl&SecNodeType=0&TIME=&rt=52248944";
//                    boolean just = stockCompanyNoticeService.spiderStockCompanyNotice(url , bigClass , middleClass);
//                    Thread.sleep(sleep);
//
//                    if(just){
//                        break;
//                    }
//                }
//            }
//
//            if(j == 2){
//                bigClass = "信息变更";
//                for (int i = 1 ; i <= 1000 ; i++) {
//                    url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=4&CodeType=1&PageIndex="+ i +"&PageSize=100&jsObj=WNyrOKvn&SecNodeType=0&TIME=&rt=52248946";
//                    boolean just = stockCompanyNoticeService.spiderStockCompanyNotice(url , bigClass , middleClass);
//                    Thread.sleep(sleep);
//
//                    if(just){
//                        break;
//                    }
//                }
//            }
//
//            if(j == 3){
//                bigClass = "持股变动";
//                for (int i = 1 ; i <= 1000 ; i++) {
//                    url = "http://data.eastmoney.com/notices/getdata.ashx?StockCode=&FirstNodeType=7&CodeType=1&PageIndex="+ i +"&PageSize=100&jsObj=fOGcQIzJ&SecNodeType=0&TIME=&rt=52248947";
//                    boolean just = stockCompanyNoticeService.spiderStockCompanyNotice(url , bigClass , middleClass);
//                    Thread.sleep(sleep);
//                    if(just){
//                        break;
//                    }
//                }
//            }
//        }
//
//    }

}
