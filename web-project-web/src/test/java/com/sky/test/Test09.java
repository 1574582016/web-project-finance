package com.sky.test;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sky.core.utils.SpiderUtils;
import com.sky.model.FinanceMarket;
import com.sky.model.FinanceMarketStock;
import com.sky.service.FinanceMarketService;
import com.sky.service.FinanceMarketStockService;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThinkPad on 2020/2/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test09 {

    @Autowired
    private FinanceMarketService financeMarketService;

    @Autowired
    private FinanceMarketStockService financeMarketStockService ;

    @Test
    public void test(){
        List<FinanceMarket> list = financeMarketService.selectList(new EntityWrapper<FinanceMarket>().where("market_type = 4 and market_describe is null"));
        for(FinanceMarket market : list){
            String url = "http://webf10.gw.com.cn/BK/B1/SH"+ market.getMarketCode() +"_B1.html";
            Document document = SpiderUtils.HtmlJsoupGet(url);
            Elements elements = document.getElementsByClass("moreBox");
            market.setMarketDescribe(elements.text());
            financeMarketService.updateById(market);
        }
//        financeMarketService.updateBatchById(list);
    }

    @Test
    public void test02(){
        List<FinanceMarket> list = financeMarketService.selectList(new EntityWrapper<FinanceMarket>().where("market_type = 4"));
        for(FinanceMarket market : list){
            String url = "http://webf10.gw.com.cn/BK/B4/SH"+ market.getMarketCode() +"_B4.html";
            Document document = SpiderUtils.HtmlJsoupGet(url);
            Elements table = document.getElementsByClass("f10tabel_new");
            if(table.size() > 0){
                Elements elements = table.get(0).getElementsByTag("tr");
                List<FinanceMarketStock> list2 = new ArrayList<>();
                for(int i = 1 ; i < elements.size() ; i++){
                    Elements element2 = elements.get(i).getElementsByTag("td");
                    FinanceMarketStock marketStock = new FinanceMarketStock();
                    marketStock.setMarketCode(market.getMarketCode());
                    marketStock.setMarketName(market.getMarketName());
                    for(int j = 1 ; j < element2.size() ; j++){
                        Element element = element2.get(j);
                        switch (j){
                            case 1 : marketStock.setStockCode(element.text()); break;
                            case 2 : marketStock.setStockName(element.text()); break;
                            case 4 : marketStock.setStockDescribe(element.text()); break;
                        }
                    }
                    list2.add(marketStock);
                }
                financeMarketStockService.insertBatch(list2);
            }

        }
    }
}
