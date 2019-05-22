package com.sky.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ThinkPad on 2019/5/14.
 */
@Controller
@RequestMapping("/economy")
public class EconomyStatisticsController {

    @RequestMapping("/economyNewsStatisticsList")
    public String economyNewsStatisticsList(){
        return "page/economyNewsStatisticsList";
    }

    @RequestMapping("/economyNewsStatisticsEdit")
    public String economyNewsStatisticsEdit(String id , Model model){
        model.addAttribute("id" ,id);
        return "page/economyNewsStatisticsEdit";
    }

    @RequestMapping("/economyNewsStatisticsView")
    public String economyNewsStatisticsView(String id , String newsCode , Model model){
        model.addAttribute("id" , id);
        model.addAttribute("newsCode" , newsCode);
        return "page/economyNewsStatisticsView";
    }

    @RequestMapping("/economyMarketStatisticsList")
    public String economyMarketStatisticsList(){
        return "page/economyMarketStatisticsList";
    }
}
