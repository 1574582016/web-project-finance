package com.sky;

import com.sky.core.consts.SpiderUrlConst;
import com.sky.core.utils.DateUtils;
import com.sky.core.utils.SpiderUtils;
import com.sky.service.ForexIndexTypeService;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by ThinkPad on 2019/7/8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessForexIndexTest {

    @Autowired
    private ForexIndexTypeService forexIndexTypeService ;

    @Test
    public void processForexIndexType(){
        String url = "https://rl.fx678.com/USAdata.html";
        String contry = "美国";
        forexIndexTypeService.spiderForexIndexType(contry , url);
    }

    @Test
    public void spiderLastYearForexIndexType() throws InterruptedException {
        String url = "https://rl.fx678.com/date/";
        for(int x = 3 ; x < 365 ; x++){
            System.out.println("============================" + x);
            Date date = DateUtils.addDays(DateUtils.parseDate("2018-02-01","yyyy-MM-dd"),x);
            String newUrl = url + DateUtils.format(date,"yyyyMMdd") + ".html";
            System.out.println(DateUtils.format(date,"yyyyMMdd"));
            forexIndexTypeService.spiderLastYearForexIndexType(newUrl);
            Thread.sleep(500L);
        }
    }

    @Test
    public void test(){
        String url = "https://rl.fx678.com/date/20190921.html";
        Document doc = SpiderUtils.HtmlJsoupGet(url);
        Elements elements = doc.body().getElementsByClass("cjsj_tab");
        Element element = elements.get(0);
        System.out.println(element.html());
    }
}
