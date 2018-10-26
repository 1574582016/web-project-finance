package com.sky.api.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.sky.api.AbstractController;
import com.sky.model.LearnQuestion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ThinkPad on 2018/10/18.
 */
@RestController
@RequestMapping("/api/question")
public class LearnQuestionApiController extends AbstractController {

    @PostMapping("/getLearnQuestionList")
    public Object getLearnQuestionList(@RequestParam(required = false, defaultValue = PAGE_NUM) Integer page,
                                    @RequestParam(required = false, defaultValue = PAGE_SIZE) Integer size,
                                    String content ,
                                    String type ,
                                    String startDate ,
                                    String endDate ){
        Page selectedPage = learnQuestionService.getLearnQuestionList( page , size , content, type, startDate , endDate);
        return PageData(selectedPage);
    }

    @PostMapping("/editLearnQuestion")
    public Object editLearnQuestion(@RequestBody LearnQuestion body){
        if(body.getId() == null){
            body.setQuestionCode(IdWorker.getIdStr());
        }
        learnQuestionService.insertOrUpdate(body);
        return ResponseEntity.ok(MapSuccess("保存成功！"));
    }

    @PostMapping("/getLearnQuestionInfo")
    public Object getLearnQuestionInfo(String id){
        LearnQuestion learnQuestion = learnQuestionService.selectById(id);
        return ResponseEntity.ok(MapSuccess("查询成功",learnQuestion));
    }
}
