package com.sky.spider;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sky.model.StockCompanySector;
import com.sky.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by Administrator on 2019/12/27/027.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpiderCompanyBaseInfo {

    private static final long  sleep = 300;

    @Autowired
    private StockCompanyBaseService stockCompanyBaseService ;

    @Autowired
    private StockCompanyProductService stockCompanyProductService ;

    @Autowired
    private StockCompanySectorService stockCompanySectorService;

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
}
