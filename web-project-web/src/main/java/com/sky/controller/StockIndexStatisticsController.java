package com.sky.controller;

import com.sky.core.utils.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * Created by ThinkPad on 2019/10/2.
 */
@Controller
@RequestMapping("/stockIndex")
public class StockIndexStatisticsController {

    @RequestMapping("/stockIndexConstituentList")
    public String companyInformationList(){
        return "page/stockIndexConstituentList";
    }


    @RequestMapping("/stockIndexStatisticList")
    public String stockIndexStatisticList(Model model){
        model.addAttribute("startDay" , DateUtils.formatDate(DateUtils.addMonths(new Date(),-1),"yyyy-MM-dd"));
        model.addAttribute("endDay" , DateUtils.getDate());
        return "page/stockIndexStatisticList";
    }


    @RequestMapping("/stockIndexCycleList")
    public String stockIndexCycleList(Model model){
        return "page/stockIndexCycleList";
    }
}
