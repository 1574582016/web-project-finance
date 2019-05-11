package com.sky;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sky.core.consts.SpiderUrlConst;
import com.sky.core.utils.HttpUtil;
import com.sky.core.utils.SpiderUtils;
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
}
