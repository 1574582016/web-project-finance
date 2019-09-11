package com.sky.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.core.utils.SpiderUtils;
import com.sky.mapper.EconomyNewsStatictisMapper;
import com.sky.model.EconomyNewsStatictis;
import com.sky.service.EconomyNewsStatictisService;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThinkPad on 2019/5/14.
 */
@Service
@Transactional
public class EconomyNewsStatictisServiceImpl extends ServiceImpl<EconomyNewsStatictisMapper,EconomyNewsStatictis> implements EconomyNewsStatictisService {

    @Override
    public Page<EconomyNewsStatictis> getEconomyNewsStatisticsList(Integer page,
                                                                   Integer size  ,
                                                                   String newsTitle ,
                                                                   String newsType ,
                                                                   String startDate ,
                                                                   String endDate ,
                                                                   String newsTopic ,
                                                                   String newsHot) {
        Page<EconomyNewsStatictis> pageInfo = new Page<EconomyNewsStatictis>(page, size);
        List<EconomyNewsStatictis> list = baseMapper.getEconomyNewsStatisticsList( pageInfo , newsTitle , newsType , startDate , endDate , newsTopic , newsHot);
        pageInfo.setRecords(list);
        return pageInfo;
    }

    @Override
    public boolean processEveryDayNews(int type , int page){
            boolean just = true;
            try {
                String newsType = "";
                String url = "";
                switch (type){
                    case 1:
                        newsType = "咨询精华" ;
                        url = "http://finance.eastmoney.com/a/cywjh.html";
                        break;
                    case 2:
                        newsType = "国内经济" ;
                        url = "http://finance.eastmoney.com/a/cgnjj.html";
                        break;
                    case 3:
                        newsType = "国际经济" ;
                        url = "http://finance.eastmoney.com/a/cgjjj.html";
                        break;
                    case 4:
                        newsType = "证券聚焦" ;
                        url = "http://finance.eastmoney.com/a/czqyw.html";
                        break;
                    case 5:
                        newsType = "公司资讯" ;
                        url = "http://finance.eastmoney.com/a/cgsxw.html";
                        break;
                }

                String newUrl = url;
                if (page > 1) {
                    newUrl = url.split(".html")[0] + "_" + page + ".html";
                }

                List<EconomyNewsStatictis> list = new ArrayList<>();
                Document doc = SpiderUtils.HtmlJsoupGet(newUrl);
                Elements elements = doc.getElementsByClass("repeatList").get(0).getElementsByTag("li");
                for(int i = 0 ; i < elements.size() ; i ++){
                    Element element1 = elements.get(i).getElementsByClass("text").get(0).getElementsByClass("title").get(0);
                    Element element2 = elements.get(i).getElementsByClass("text").get(0).getElementsByClass("info").get(0);
                    Element element3 = elements.get(i).getElementsByClass("text").get(0).getElementsByClass("time").get(0);
                    String title = element1.text();
                    String newurl = element1.getElementsByTag("a").get(0).attr("href");
                    String contains= element2.attr("title");
                    String time = element3.html();
                    time = time.replace("月","-").replace("日","");
                    time = newurl.substring(newurl.indexOf("/a/")+3,newurl.indexOf("/a/")+7) + "-"+ time +":00";

                    contains = contains.replace("【" + title + "】" ,"");
                    EconomyNewsStatictis newsStatictis = new EconomyNewsStatictis();
                    newsStatictis.setNewsTime(time);
                    newsStatictis.setNewsType(newsType);
                    newsStatictis.setNewsTitle(title);
                    newsStatictis.setNewsContent(contains);
                    newsStatictis.setNewsUrl(newurl);

                    EconomyNewsStatictis news = selectOne(new EntityWrapper<EconomyNewsStatictis>().where("news_time = {0}" , time).where("news_title = {0}" , title));
                    if(news == null){
                        list.add(newsStatictis);
                    }
                }
                System.out.println(list.toString());
                if(list.size() > 0){
                    just = insertBatch(list);
                }else{
                    just = false ;
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            return just ;
    }
}
