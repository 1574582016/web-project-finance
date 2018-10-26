package com.sky.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ThinkPad on 2018/10/20.
 */
@Controller
@RequestMapping("/tradition")
public class TraditionMarketController {

    @RequestMapping("/traditionMarketList")
    public String traditionMarketList(){
        return "page/traditionMarketList";
    }
}
