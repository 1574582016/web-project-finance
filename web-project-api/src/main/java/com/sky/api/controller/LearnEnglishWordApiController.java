package com.sky.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.sky.annotation.LogRecord;
import com.sky.api.AbstractController;
import com.sky.model.EnglishRootAffix;
import com.sky.model.LearnEnglishClass;
import com.sky.model.LearnEnglishWord;
import com.sky.service.EnglishRootAffixService;
import com.sky.vo.EnglishWorldResult_VO;
import com.sky.vo.EnglishWorld_VO;
import com.sky.vo.LearnEnglishRoot_VO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * Created by ThinkPad on 2018/10/11.
 */
@RestController
@RequestMapping("/api/english")
public class LearnEnglishWordApiController extends AbstractController {

    @LogRecord(name = "getEnglishWordList" ,description = "查询英语单词列表")
    @PostMapping("/getEnglishWordList")
    public Object getEnglishWordList(@RequestParam(required = false, defaultValue = PAGE_NUM) Integer page,
                                     @RequestParam(required = false, defaultValue = PAGE_SIZE) Integer size,
                                     String english ,
                                     String chinese ,
                                     String level ,
                                     String master,
                                     String startDate ,
                                     String endDate  ){
        Wrapper<LearnEnglishWord> wrapper = new EntityWrapper<LearnEnglishWord>();
        if (!StringUtils.isEmpty(english)) {
            wrapper.where("english REGEXP {0}", english);
        }
        if (!StringUtils.isEmpty(chinese)) {
            wrapper.where("chinese REGEXP {0}", chinese);
        }
        if (!StringUtils.isEmpty(level)) {
            wrapper.where("level = {0}", level);
        }
        if (!StringUtils.isEmpty(master)) {
            wrapper.where("master = {0}", master);
        }
        if (!StringUtils.isEmpty(startDate)) {
            wrapper.where("learn_date >= {0}", startDate);
        }
        if (!StringUtils.isEmpty(endDate)) {
            wrapper.where("learn_date < date_add({0}, interval 1 day)", endDate);
        }
        wrapper.orderBy("id desc");
        Page<LearnEnglishWord> pageNew = new Page<LearnEnglishWord>(page, size);
        Page selectedPage = learnEnglishWordService.selectPage(pageNew, wrapper);
        return PageData(selectedPage);
    }

    @LogRecord(name = "getEnglishWordInfo" ,description = "根据ID查询英语单词信息")
    @PostMapping("/getEnglishWordInfo")
    public Object getEnglishWordInfo(String id){
        LearnEnglishWord englishWord = learnEnglishWordService.selectById(id);
        return ResponseEntity.ok(MapSuccess("查询成功",englishWord));
    }

    @LogRecord(name = "editEnglishWord" ,description = "编辑英语单词信息")
    @PostMapping("/editEnglishWord")
    public Object editEnglishWord(@RequestBody LearnEnglishWord body){
        System.out.println(body.toString());
        int num = learnEnglishWordService.selectCount(new EntityWrapper<LearnEnglishWord>().where("english = {0}",body.getEnglish()));
        if(num == 1 && body.getId() == null){//添加时
            return ResponseEntity.ok(MapError("该单词已存在！"));
        }
        learnEnglishWordService.insertOrUpdate(body);
        return ResponseEntity.ok(MapSuccess("保存成功！"));
    }

    @LogRecord(name = "getEnglishClassList" ,description = "查询英语单词分类列表")
    @PostMapping("/getEnglishClassList")
    public Object getEnglishClassList(String fragment ){
        Wrapper<LearnEnglishClass> wrapper = new EntityWrapper<LearnEnglishClass>();
        if (!StringUtils.isEmpty(fragment)) {
            wrapper.where("fragment REGEXP {0}", fragment);
        }
        List<LearnEnglishClass> list = learnEnglishClassService.selectList(wrapper);
        return ResponseEntity.ok(MapSuccess("查询成功！",list));
    }

    @LogRecord(name = "getEnglishRootClassList" ,description = "查询英语单词分类列表")
    @PostMapping("/getEnglishRootClassList")
    public Object getEnglishRootClassList(String comonRoot){
        List<EnglishRootAffix> rootsList = englishRootAffixService.selectList(new EntityWrapper<EnglishRootAffix>().where("root_type = '词根' and same_root = {0}" , comonRoot).groupBy("same_root"));
        List<JSONObject> sameList = new ArrayList<>();
        for(EnglishRootAffix affix : rootsList){
            String sameRoot = affix.getSameRoot();
            sameRoot = getDifferentRoot(sameRoot);
            System.out.println(sameRoot);
            String[] roots = sameRoot.split(",");
            List<LearnEnglishRoot_VO> rootList = new ArrayList<>();
            for(String root : roots){
                List<EnglishWorldResult_VO> voList = new ArrayList<>();
                List<EnglishWorld_VO> list = learnEnglishWordService.getEnglishWorldByRoot(root);
                List<Integer> numList = caculateDiffrent(list);
                int colrow = 0;
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
                colrow = colrow + voList.size() ;
                LearnEnglishRoot_VO root_vo = new LearnEnglishRoot_VO();
                root_vo.setRoot(root);
                root_vo.setList(voList);
                root_vo.setColRow(colrow);
                rootList.add(root_vo);
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("sameRoot" , affix.getSameRoot() + "<br>" + affix.getRootMean());
            jsonObject.put("rootList", rootList);
            sameList.add(jsonObject);
        }
        return ResponseEntity.ok(MapSuccess("查询成功！",sameList));
    }

    private List<Integer> caculateDiffrent(List<EnglishWorld_VO> list){
        List<Integer> numList = new ArrayList<>();
        int just = -1;
        for(EnglishWorld_VO world_vo : list){
            if(just != world_vo.getAffixNum().intValue()){
                just = world_vo.getAffixNum().intValue();
                numList.add(world_vo.getAffixNum());
            }
//            String chinese = world_vo.getChinese();
//            String[] strs = chinese.split(" ");
//            String chStr = "";
//            for(String str : strs){
//                chStr += str + "<br>";
//            }
//            world_vo.setChinese(chStr);
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
