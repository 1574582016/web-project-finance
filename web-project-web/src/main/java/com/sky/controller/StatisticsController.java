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

    @RequestMapping("/messagePriceStaticList")
    public String messagePriceStaticList(){
        return "page/messagePriceStaticList";
    }

    @RequestMapping("/forexCycleStatisticsList")
    public String forexCycleStatisticsList(){
        return "page/forexCycleStatisticsList";
    }

    @RequestMapping("/stockTimeStatisticsList")
    public String stockTimeStatisticsList(){
        return "page/stockTimeStatisticsList";
    }

    @RequestMapping("/sectorCovarStatisticsList")
    public String sectorCovarStatisticsList(){
        return "page/sectorCovarStatisticsList";
    }
}
