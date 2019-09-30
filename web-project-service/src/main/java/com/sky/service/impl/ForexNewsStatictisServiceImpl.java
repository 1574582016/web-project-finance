package com.sky.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.core.utils.SpiderUtils;
import com.sky.mapper.ForexNewsStatictisMapper;
import com.sky.model.ForexNewsStatictis;
import com.sky.service.ForexNewsStatictisService;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThinkPad on 2019/9/30.
 */
@Service
@Transactional
public class ForexNewsStatictisServiceImpl extends ServiceImpl<ForexNewsStatictisMapper,ForexNewsStatictis> implements ForexNewsStatictisService {
    @Override
    public boolean spiderForexNews(Integer page) {
        String url = "https://news.fx678.com/column/toutiao";
        if(page > 0){
            url = url + "/" + page;
        }
        Document doc = SpiderUtils.HtmlJsoupGet(url);
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

                ForexNewsStatictis isExist = selectOne(new EntityWrapper<ForexNewsStatictis>().where("news_time = {0}" , time + ":00").where("news_title = {0}" , title));
                if(isExist == null){
                    list.add(newsStatictis);
                }
//                list.add(newsStatictis);
            }
        }

        if(list.size() > 0){
            return insertBatch(list);
        }
        return false;
    }

    @Override
    public Page<ForexNewsStatictis> getForexNewsStatisticsList(Integer page, Integer size, String newsTitle, String newsType, String startDate, String endDate, String newsTopic, String newsHot) {
        Page<ForexNewsStatictis> pageInfo = new Page<ForexNewsStatictis>(page, size);
        List<ForexNewsStatictis> list = baseMapper.getForexNewsStatisticsList( pageInfo , newsTitle , newsType , startDate , endDate , newsTopic , newsHot);
        pageInfo.setRecords(list);
        return pageInfo;
    }
}
