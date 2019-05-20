package com.sky.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ThinkPad on 2019/5/16.
 */
@Controller
@RequestMapping("/invest")
public class InvestController {

    @RequestMapping("/investForexReplayList")
    public String investForexReplayList(){
        return "page/investForexReplayList";
    }

    @RequestMapping("/investForexReplayEdit")
    public String investForexReplayEdit(){
        return "page/investForexReplayEdit";
    }

    @RequestMapping("/investForexReplayView")
    public String investForexReplayView(Integer id , String replayCode ,Model model){
        model.addAttribute("id" , id);
        model.addAttribute("replayCode" , replayCode);
        return "page/investForexReplayView";
    }

    @RequestMapping("/investStockReplayList")
    public String investStockReplayList(){
        return "page/investStockReplayList";
    }

    @RequestMapping("/investForexOperateList")
    public String investForexOperateList(){
        return "page/investForexOperateList";
    }
}
