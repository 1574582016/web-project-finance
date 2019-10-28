package com.sky.controller;

import com.sky.core.utils.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * Created by ThinkPad on 2019/10/28.
 */
@Controller
@RequestMapping("/macroEconomy")
public class ContryMacroEconomyController {

    @RequestMapping("/usMacroEconomyList")
    public String economyNewsStatisticsList(Model model){
        model.addAttribute("startDay" , DateUtils.formatDate(DateUtils.addYears(new Date(),-3),"yyyy-MM-dd"));
        model.addAttribute("endDay" , DateUtils.getDate());
        return "page/usMacroEconomyList";
    }
}
