package com.sky.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ThinkPad on 2019/11/26.
 */
@Controller
@RequestMapping("/handleCycle")
public class HandleSectorCycleController {

    @RequestMapping("/handleSectorCycleList")
    public String handleSectorCycleList(Model model){
        return "page/handleSectorCycleList";
    }
}
