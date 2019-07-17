package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.sky.core.utils.DateUtils;
import com.sky.core.utils.SpiderUtils;
import com.sky.mapper.ForexIndexTypeMapper;
import com.sky.model.ForexIndexType;
import com.sky.service.ForexIndexTypeService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ThinkPad on 2019/7/8.
 */
@Service
@Transactional
public class ForexIndexTypeServiceImpl extends ServiceImpl<ForexIndexTypeMapper,ForexIndexType> implements ForexIndexTypeService {

    @Override
    public void spiderForexIndexType(String contry ,String url) {
        Document doc = SpiderUtils.HtmlJsoupGet(url);
        Elements elements = doc.body().getElementsByClass("cjsj_tab3");
        Element element = elements.get(0);
        Elements trs = element.getElementsByTag("tr");
        List<ForexIndexType> list = new ArrayList<ForexIndexType>();
        for(int i = 1 ; i < trs.size() ; i ++){
            Element trElement = trs.get(i);
            Elements tds = trElement.getElementsByTag("td");
            Element tdElement = tds.get(0);
            String name = tdElement.text();
            String subUrl = "https://rl.fx678.com" + tdElement.getElementsByTag("a").attr("href");
            Document SubDoc = SpiderUtils.HtmlJsoupGet(subUrl);
            Elements releaseElements = SubDoc.body().getElementsByClass("choose_add_1_two");
            Elements subReleases = releaseElements.get(0).getElementsByTag("li");
            String department = subReleases.get(1).text();
            String frequency = subReleases.get(2).text();
            Elements indexElements = SubDoc.body().getElementsByClass("choose_add_1_top");
            String explain = indexElements.get(0).text();
            String reason = indexElements.get(1).text();

            ForexIndexType indexType = new ForexIndexType();
            indexType.setTypeCode(IdWorker.getIdStr());
            indexType.setTypeName(name);
            indexType.setReleaseContry(contry);
            indexType.setReleaseDapartment(department.replace("数据公布机构 :",""));
            indexType.setReleaseFrequency(frequency.replace("发布频率 :",""));
            indexType.setIndexExplain(explain.replace("主指标数据释义： ",""));
            indexType.setIndexFocusReason(reason.replace("主指标关注原因： ",""));
            list.add(indexType);
        }
        insertBatch(list);
    }

    @Override
    public void spiderLastYearForexIndexType(String url) {
        List<ForexIndexType> list = new ArrayList<ForexIndexType>();
        Document doc = SpiderUtils.HtmlJsoupGet(url);
        Elements elements = doc.body().getElementsByClass("cjsj_tab");
        Element element = elements.get(0);
        Elements trs = element.getElementsByTag("tr");
        for(int i = 2 ; i < trs.size() ; i ++){
            Element trElement = trs.get(i);
            Elements tds = trElement.getElementsByClass("nowrap");
            if(tds.size() > 0){
                Element tdElement = tds.get(0);
                String name = tdElement.text();
                String subUrl = "https://rl.fx678.com" + tdElement.getElementsByTag("a").attr("href");
                Document SubDoc = SpiderUtils.HtmlJsoupGet(subUrl);
                Elements releaseElements = SubDoc.body().getElementsByClass("choose_add_1_two");
                Elements subReleases = releaseElements.get(0).getElementsByTag("li");
                String department = subReleases.get(1).text();
                String frequency = subReleases.get(2).text();
                Elements indexElements = SubDoc.body().getElementsByClass("choose_add_1_top");
                String explain = indexElements.get(0).text();
                String reason = indexElements.get(1).text();

                ForexIndexType indexType = new ForexIndexType();
                indexType.setTypeCode(IdWorker.getIdStr());
                indexType.setTypeName(name);
                indexType.setReleaseDapartment(department.replace("数据公布机构 :",""));
                indexType.setReleaseFrequency(frequency.replace("发布频率 :",""));
                indexType.setIndexExplain(explain.replace("主指标数据释义： ",""));
                indexType.setIndexFocusReason(reason.replace("主指标关注原因： ",""));
                list.add(indexType);
            }
        }
        if(list.size() > 0){
            insertBatch(list);
        }
    }
}
