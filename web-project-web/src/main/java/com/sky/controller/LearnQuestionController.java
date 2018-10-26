package com.sky.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ThinkPad on 2018/10/18.
 */
@Controller
@RequestMapping("/question")
public class LearnQuestionController {

    @RequestMapping("/learnQuestionList")
    public String learnQuestionList(){
        return "page/learnQuestionList";
    }
}
