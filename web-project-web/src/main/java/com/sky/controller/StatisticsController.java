package com.sky.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sky.model.StockMarketClass;
import com.sky.service.StockMarketClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by ThinkPad on 2019/4/29.
 */
@Controller
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private StockMarketClassService stockMarketClassService ;

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

    @RequestMapping("/sectorCycleStatisticsList")
    public String sectorCycleStatisticsList(Model model){
        List<StockMarketClass> list = stockMarketClassService.selectList(new EntityWrapper<StockMarketClass>().where("class_type = '行业板块'"));
        model.addAttribute("marketList" , list);
        return "page/sectorCycleStatisticsList";
    }

    @RequestMapping("/sectorOrderStatisticsList")
    public String sectorOrderStatisticsList(){
        return "page/sectorOrderStatisticsList";
    }
}
