package com.sky;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.sky.core.consts.SpiderUrlConst;
import com.sky.core.utils.HttpUtil;
import com.sky.core.utils.SpiderUtils;
import com.sky.model.FuturesMarketProduct;
import com.sky.service.FuturesMarketProductService;
import com.sky.service.StockIndexService;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by ThinkPad on 2019/5/5.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessStockIndexTest {

    @Autowired
    private StockIndexService stockIndexService ;

    @Test
    public void processSHIndex(){
        int timeType = 0;
        String url = "";
        String[] urls = SpiderUrlConst.getSHIndexUrls();
        for(int i = 0 ; i < urls.length ; i ++){
            timeType = i + 1 ;
            url = urls[i];
            stockIndexService.processStockIndex(url ,1 , timeType);
        }
    }

    @Test
    public void processSZIndex(){
        int timeType = 0;
        String url = "";
        String[] urls = SpiderUrlConst.getSZIndexUrls();
        for(int i = 0 ; i < urls.length ; i ++){
            timeType = i + 1 ;
            url = urls[i];
            stockIndexService.processStockIndex(url ,2 , timeType);
        }
    }

    @Test
    public void processZXIndex(){
        int timeType = 0;
        String url = "";
        String[] urls = SpiderUrlConst.getZXIndexUrls();
        for(int i = 0 ; i < urls.length ; i ++){
            timeType = i + 1 ;
            url = urls[i];
            stockIndexService.processStockIndex(url ,3 , timeType);
        }
    }

    @Test
    public void processCYIndex(){
        int timeType = 0;
        String url = "";
        String[] urls = SpiderUrlConst.getCYIndexUrls();
        for(int i = 0 ; i < urls.length ; i ++){
            timeType = i + 1 ;
            url = urls[i];
            stockIndexService.processStockIndex(url ,4 , timeType);
        }
    }

    @Test
    public void processDQSIndex(){
        int timeType = 0;
        String url = "";
        String[] urls = SpiderUrlConst.getDQSIndexUrls();
        for(int i = 0 ; i < urls.length ; i ++){
            timeType = i + 1 ;
            url = urls[i];
            stockIndexService.processStockIndex(url ,5 , timeType);
        }
    }

    @Test
    public void processNSDKIndex(){
        int timeType = 0;
        String url = "";
        String[] urls = SpiderUrlConst.getNSDKIndexUrls();
        for(int i = 0 ; i < urls.length ; i ++){
            timeType = i + 1 ;
            url = urls[i];
            stockIndexService.processStockIndex(url ,6 , timeType);
        }
    }

    @Test
    public void processBZPEIndex(){
        int timeType = 0;
        String url = "";
        String[] urls = SpiderUrlConst.getBZPEYIndexUrls();
        for(int i = 0 ; i < urls.length ; i ++){
            timeType = i + 1 ;
            url = urls[i];
            stockIndexService.processStockIndex(url ,7 , timeType);
        }
    }

    @Autowired
    private FuturesMarketProductService futuresMarketProductService ;

    @Test
    public void processqihuo(){
        int timeType = 0;
        String url = "http://quote.eastmoney.com/center/sidemenu.json";
        String jsonString = SpiderUtils.HttpClientBuilderGet(url);
//        System.out.println(jsonString);
        JSONArray jsonArray = JSON.parseArray(jsonString);
        JSONObject jsonObject = jsonArray.getJSONObject(13);
        JSONArray qh = jsonObject.getJSONArray("next");
        for(int i = 0 ; i < qh.size() ; i++){
            JSONObject cl = qh.getJSONObject(i);
            String title = cl.getString("title");
            String key = cl.getString("key");
            JSONArray product = cl.getJSONArray("next");
            for(int j = 0 ; j < product.size() ; j++){
                JSONObject pj = product.getJSONObject(j);
                String ptitle = pj.getString("title");
                String pkey = pj.getString("key");
                FuturesMarketProduct marketProduct = new FuturesMarketProduct();
                marketProduct.setProductCode(IdWorker.getIdStr());
                marketProduct.setMarketName(title);
                marketProduct.setMarketIdentify(key);
                marketProduct.setProductName(ptitle);
                marketProduct.setProductIdentify(pkey);
                futuresMarketProductService.insert(marketProduct);
            }
        }
    }
}
