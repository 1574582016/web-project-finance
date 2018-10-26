package com.sky.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ThinkPad on 2018/10/16.
 */
@Controller
@RequestMapping("/task")
public class LearnPlanController {

    @RequestMapping("/learnTaskList")
    public String learnTaskList(){
        return "page/learnTaskList";
    }
}
