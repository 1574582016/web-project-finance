package com.sky.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
