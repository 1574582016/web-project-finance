package com.sky.api.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.sky.api.AbstractController;
import com.sky.model.LearnEnglishWord;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Created by ThinkPad on 2018/10/11.
 */
@RestController
@RequestMapping("/api/english")
public class LearnEnglishWordApiController extends AbstractController {

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

    @PostMapping("/getEnglishWordInfo")
    public Object getEnglishWordInfo(String id){
        LearnEnglishWord englishWord = learnEnglishWordService.selectById(id);
        return ResponseEntity.ok(MapSuccess("查询成功",englishWord));
    }

    @PostMapping("/editEnglishWord")
    public Object editEnglishWord(@RequestBody LearnEnglishWord body){
        System.out.println(body.toString());
        int num = learnEnglishWordService.selectCount(new EntityWrapper<LearnEnglishWord>().where("english = {0}",body.getEnglish()));
        if(num == 1 && body.getId() == null){//添加时
            return ResponseEntity.ok(MapError("该单词已存在！"));
        }
        body.setType(body.getEnglish().substring(0,1).toUpperCase());
        learnEnglishWordService.insertOrUpdate(body);
        return ResponseEntity.ok(MapSuccess("保存成功！"));
    }
}
