package com.sky.test;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sky.core.utils.SpiderUtils;
import com.sky.model.EnglishRootAffix;
import com.sky.model.LearnEnglishWord;
import com.sky.service.EnglishRootAffixService;
import com.sky.service.LearnEnglishWordService;
import com.sky.vo.EnglishWorldResult_VO;
import com.sky.vo.EnglishWorld_VO;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * Created by Administrator on 2019/12/15/015.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EnglishRootTest {

    @Autowired
    private EnglishRootAffixService rootAffixService ;

    @Autowired
    private LearnEnglishWordService learnEnglishWordService ;

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
        List<EnglishRootAffix> list = rootAffixService.selectList(new EntityWrapper<EnglishRootAffix>().where("root_type = '词根'"));
        for(EnglishRootAffix affix : list){
            String rootAffixs = affix.getSameRoot();
            System.out.println("==========================" + rootAffixs);
            String[] roots = rootAffixs.split(",");
            String rootStr = "";
            for(String root : roots){
                if(root.length() > 1){
                    rootStr += root + ",";
                }
            }
            affix.setSameRoot(rootStr);
            rootAffixService.updateById(affix);
        }
    }

    @Test
    public void test02(){
        String sameRoot = "brev,brevi,brief,bridg";
        sameRoot = getDifferentRoot(sameRoot);
        System.out.println(sameRoot);
        String[] roots = sameRoot.split(",");
        Map<String , List<EnglishWorldResult_VO>> map = new HashMap<>();
        for(String root : roots){
            List<EnglishWorldResult_VO> voList = new ArrayList<>();
            List<EnglishWorld_VO> list = learnEnglishWordService.getEnglishWorldByRoot(root);
            List<Integer> numList = caculateDiffrent(list);
            for(int num : numList){
                EnglishWorldResult_VO result_vo = new EnglishWorldResult_VO();
                List<JSONObject> noneList = new ArrayList<>();
                List<JSONObject> verbList = new ArrayList<>();
                List<JSONObject> adjList = new ArrayList<>();
                List<JSONObject> advList = new ArrayList<>();
                for(EnglishWorld_VO word : list){
                    if(num == word.getAffixNum().intValue()){
                        JSONObject jsonObject = new JSONObject();
                        String chinese = word.getChinese();
                        if(chinese.indexOf("vt.") != -1 || chinese.indexOf("vi.") != -1){
                            jsonObject.put("english",word.getEnglish());
                            jsonObject.put("pronunciation",word.getPronunciation());
                            jsonObject.put("chinese",word.getChinese());
                            verbList.add(jsonObject);
                            continue;
                        }
                        if(chinese.indexOf("n.") != -1){
                            jsonObject.put("english",word.getEnglish());
                            jsonObject.put("pronunciation",word.getPronunciation());
                            jsonObject.put("chinese",word.getChinese());
                            noneList.add(jsonObject);
                            continue;
                        }
                        if(chinese.indexOf("adj.") != -1){
                            jsonObject.put("english",word.getEnglish());
                            jsonObject.put("pronunciation",word.getPronunciation());
                            jsonObject.put("chinese",word.getChinese());
                            adjList.add(jsonObject);
                            continue;
                        }
                        if(chinese.indexOf("adv.") != -1){
                            jsonObject.put("english",word.getEnglish());
                            jsonObject.put("pronunciation",word.getPronunciation());
                            jsonObject.put("chinese",word.getChinese());
                            advList.add(jsonObject);
                            continue;
                        }
                    }
                }
                result_vo.setNoneList(noneList);
                result_vo.setVerbList(verbList);
                result_vo.setAdjList(adjList);
                result_vo.setAdvList(advList);
                voList.add(result_vo);
            }
            map.put(root , voList);
        }
        System.out.println(map.toString());
    }

    private List<Integer> caculateDiffrent(List<EnglishWorld_VO> list){
        List<Integer> numList = new ArrayList<>();
        int just = -1;
        for(EnglishWorld_VO world_vo : list){
            if(just != world_vo.getAffixNum().intValue()){
                just = world_vo.getAffixNum().intValue();
                numList.add(world_vo.getAffixNum());
            }
        }
        return numList;
    }

    private String getDifferentRoot(String sameRoot){
        Set<String> list = new HashSet<>();
        String[] roots = sameRoot.split(",");
        for(String root: roots){
            boolean just = false ;
            for(String roote : roots){
                if(!root.equals(roote) && ( root.indexOf(roote) != -1 || roote.indexOf(root) !=  -1 )){
                    just = true ;
                    if(root.indexOf(roote) != 0){
                        list.add(root);
                    }
                    if(roote.indexOf(root) != 0){
                        list.add(roote);
                    }
                }
            }
            if(!just){
                list.add(root);
            }
        }
        String str = "";
        for (String root : list) {
            str += root + ",";
        }

        return str;
    }
}
