package com.sky;

import com.sky.core.consts.SpiderUrlConst;
import com.sky.service.StockCodeService;
import com.sky.service.StockCompanyBaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by ThinkPad on 2019/5/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessCompanyInfo {

    @Autowired
    private StockCodeService stockCodeService ;

    @Autowired
    private StockCompanyBaseService stockCompanyBaseService ;

    @Test
    public void processStockCode(){
        String url = "http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?cb=jQuery112408476549379467933_1559019856960&type=CT&token=4f1862fc3b5e77c150a2b985b12db0fd&sty=FCOIATC&js=(%7Bdata%3A%5B(x)%5D%2CrecordsFiltered%3A(tot)%7D)&cmd=C.BK04381&st=(ChangePercent)&sr=-1&p=1&ps=20000&_=1559019856972";
        stockCodeService.spiderStockCode(url ,"食品饮料");
    }

    @Test
    public void processStockCompanyBace(){
        stockCompanyBaseService.spiderStockCompanyBase("食品饮料");
    }
}
