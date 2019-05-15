package com.sky.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ThinkPad on 2019/4/29.
 */
@Controller
@RequestMapping("/statistics")
public class StatisticsController {

    @RequestMapping("/stockCycleStatisticsList")
    public String stockCycleStatisticsList(){
        return "page/stockCycleStatisticsList";
    }
}
