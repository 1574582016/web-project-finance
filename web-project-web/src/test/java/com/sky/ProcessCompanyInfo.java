package com.sky;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sky.core.consts.SpiderUrlConst;
import com.sky.core.utils.SpiderUtils;
import com.sky.model.StockCode;
import com.sky.model.StockCompanyBase;
import com.sky.model.StockMarketClass;
import com.sky.service.*;
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

    @Test
    public void processStockCode(){
        EntityWrapper<StockMarketClass> entityWrapper = new EntityWrapper();
        entityWrapper.where("class_type = '行业板块'");
        List<StockMarketClass> codeList = stockMarketClassService.selectList(entityWrapper);
        for(StockMarketClass marketClass : codeList){
            String url = "http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?cb=jQuery112402481894141903367_1559101505431&type=CT&token=4f1862fc3b5e77c150a2b985b12db0fd&sty=FCOIATC&js=(%7Bdata%3A%5B(x)%5D%2CrecordsFiltered%3A(tot)%7D)&cmd=C."+ marketClass.getClassCode() +"&st=(ChangePercent)&sr=-1&p=1&ps=20000&_=1559101505641";
            stockCodeService.spiderStockCode(url ,marketClass.getClassName());
        }
    }

    @Test
    public void processStockCompanyBace() throws InterruptedException {
        List<StockCode> codeList = stockCodeService.getEmptyStockCompanyList(null);
        for (StockCode stockCode : codeList) {
            stockCompanyBaseService.spiderStockCompanyBase(stockCode.getStockMarket()+stockCode.getStockCode());
//            Thread.sleep(sleep);
        }

    }

    @Test
    public void processStockCompanyProduct() throws InterruptedException {
        EntityWrapper<StockCode> entityWrapper = new EntityWrapper();
        entityWrapper.where("stock_sector = {0}", sector);
        List<StockCode> codeList = stockCodeService.selectList(entityWrapper);
        for (StockCode stockCode : codeList) {
            stockCompanyProductService.spiderStockCompanyProduct(stockCode.getStockMarket() , stockCode.getStockCode());
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
        EntityWrapper<StockCode> entityWrapper = new EntityWrapper();
        entityWrapper.where("stock_sector = {0}", sector);
        List<StockCode> codeList = stockCodeService.selectList(entityWrapper);
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
}
