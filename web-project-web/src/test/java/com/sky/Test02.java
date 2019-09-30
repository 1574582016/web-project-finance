package com.sky;

import com.sky.core.utils.SpiderUtils;
import com.sky.model.ForexNewsStatictis;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThinkPad on 2019/9/29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test02 {

    @Test
    public void test(){
        Document doc = SpiderUtils.HtmlJsoupGet("https://news.fx678.com/column/toutiao");
        Elements elements = doc.getElementsByClass("list");
        List<ForexNewsStatictis> list = new ArrayList<>();
        for(int i = 0 ; i < elements.size() ; i++){
            Elements elementLi = elements.get(i).getElementsByTag("li");
            for(int j = 0 ; j < elementLi.size() ; j++){
               Element element = elementLi.get(j);
                String time = element.getElementsByClass("recent").text();
                Elements cElements = element.getElementsByTag("a");

                ForexNewsStatictis newsStatictis = new ForexNewsStatictis();

                newsStatictis.setNewsTime(time + ":00");

                String href = "";
                String title = "";
                String contain = "";
                for(int x = 0 ; x < cElements.size() ; x ++){
                    Element cElement = cElements.get(x);
                    String urlLink = cElement.getElementsByAttribute("href").get(0).attr("href");
                    if(StringUtils.isBlank(title)){
                        title = cElement.getElementsByTag("h3").text();
                    }
                    if(StringUtils.isBlank(contain)){
                        contain = cElement.getElementsByTag("p").text();
                    }
                    if(StringUtils.isBlank(href)){
                        if(urlLink.indexOf(".shtml") > 0){
                            href = "https:" + urlLink;
                        }
                    }
                }
                newsStatictis.setNewsTitle(title);
                newsStatictis.setNewsUrl(href);
                newsStatictis.setNewsContent(contain);
                list.add(newsStatictis);
            }
        }
        System.out.println(list.toString());
    }

/**
 * 开会——议程
 *     ——企业问题
 *
 *JOAN: Okay,Let's get started.Do you all have the agenda?
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
  */
}
