package com.sky.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ThinkPad on 2018/10/15.
 */
@Controller
@RequestMapping("/param")
public class SystemParamController {

    @RequestMapping("/systemParamList")
    public String systemParamList(){
        return "page/systemParamList";
    }
}
