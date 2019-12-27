package com.sky.test;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sky.core.utils.SpiderUtils;
import com.sky.model.EnglishRootAffix;
import com.sky.service.EnglishRootAffixService;
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
 * Created by Administrator on 2019/12/15/015.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EnglishRootTest {

    @Autowired
    private EnglishRootAffixService rootAffixService ;

    @Test
    public void test(){
        try {
            for(int i = 1 ; i < 17 ; i ++){
                String url="http://www.etymon.cn/yingyucizhui/list_2_"+ i +".html";
                System.out.println(url);
                Document document = SpiderUtils.HtmlJsoupGet(url);
                Elements elements = document.getElementsByTag("dt");
                List<EnglishRootAffix> affixList = new ArrayList<>();
                for(Element element : elements){
                    String linkStr = element.getElementsByAttribute("href").attr("href");
                    if(linkStr.indexOf("43117") == -1){
                        String subUrl = "http://www.etymon.cn" + linkStr ;
                        Document document2 = SpiderUtils.HtmlJsoupGet(subUrl);
                        Elements elements2 = document2.getElementsByClass("highlight");
                        EnglishRootAffix affix = new EnglishRootAffix();
                        affix.setRootType("词缀");
                        for(int z = 0 ; z < elements2.size() ; z ++){
                            Element element2 = elements2.get(z);
                            String str = element2.text();
                            if(str.indexOf("同源词") != -1){
                                str = str.substring(0 , str.indexOf("同源词"));
                            }
                            if(z == 0){
                                affix.setSameRoot(str);
                            }
                            if(z == 1){
                                affix.setRootResource(str);
                            }
                        }
//                        affixList.add(affix);
                        EnglishRootAffix rootAffix = rootAffixService.selectOne(new EntityWrapper<EnglishRootAffix>().where("same_root = {0}" , affix.getSameRoot()));
                        if(rootAffix == null){
                            rootAffixService.insert(affix);
                        }
                        Thread.sleep(3000);
                    }
                }
//                System.out.println(affixList.toString());
//                if(affixList.size() > 0){
//                    rootAffixService.insertBatch(affixList);
//                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @Test
    public void test01(){
        List<EnglishRootAffix> list = rootAffixService.selectList(null);
        for(EnglishRootAffix affix : list){
            String rootAffixs = affix.getRootAffix();
            String[] roots = rootAffixs.split(",");
            List<EnglishRootAffix> subList = new ArrayList<>();
            for(String root : roots){
                EnglishRootAffix englishRootAffix = new EnglishRootAffix();
                englishRootAffix.setSameRoot(rootAffixs);
                englishRootAffix.setRootAffix(root);
                englishRootAffix.setRootType(affix.getRootType());
                englishRootAffix.setRootMean(affix.getRootMean());
                englishRootAffix.setRootResource(affix.getRootResource());
                subList.add(englishRootAffix);
            }
            rootAffixService.insertBatch(subList);
            rootAffixService.deleteById(affix);
        }
    }
}
