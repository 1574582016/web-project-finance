package com.sky.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ThinkPad on 2019/9/7.
 */
@Controller
@RequestMapping("/stockChose")
public class StockChoseClassController {

    @RequestMapping("/stockChoseClassList")
    public String stockChoseClassList(){
        return "page/stockChoseClassList";
    }
}
