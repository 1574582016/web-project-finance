package com.sky.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ThinkPad on 2018/10/17.
 */
@Controller
@RequestMapping("/finance")
public class FinanceMarketController {

    @RequestMapping("/financeMarketList")
    public String financeMarketList(){
        return "page/financeMarketList";
    }
}
