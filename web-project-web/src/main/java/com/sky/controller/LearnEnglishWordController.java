package com.sky.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ThinkPad on 2018/10/11.
 */
@Controller
@RequestMapping("/english")
public class LearnEnglishWordController {

    @RequestMapping("/learnEnglishWordList")
    public String learnEnglishWordList(){
        return "page/learnEnglishWordList";
    }

    @RequestMapping("/learnEnglishClassList")
    public String learnEnglishClassList(){
        return "page/learnEnglishClassList";
    }
}
