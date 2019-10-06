package com.sky.api.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.sky.annotation.LogRecord;
import com.sky.api.AbstractController;
import com.sky.model.EconomyNewsInfluence;
import com.sky.model.EconomyNewsStatictis;
import com.sky.model.ForexNewsStatictis;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThinkPad on 2019/5/14.
 */
@RestController
@RequestMapping("/api/economy")
public class EconomyStatisticsApiController extends AbstractController {

    @LogRecord(name = "getEconomyNewsStatisticsList" ,description = "查询财经新闻信息列表")
    @PostMapping("/getEconomyNewsStatisticsList")
    public Object getEconomyNewsStatisticsList(@RequestParam(required = false, defaultValue = PAGE_NUM) Integer page,
                                                @RequestParam(required = false, defaultValue = PAGE_SIZE) Integer size,
                                                String newsTitle ,
                                                String newsType ,
                                                String startDate ,
                                                String endDate ,
                                                String newsTopic ,
                                                String newsHot ,
                                                String newsLevel){
        Page selectedPage = economyNewsStatictisService.getEconomyNewsStatisticsList( page , size ,newsTitle ,newsType ,startDate ,endDate , newsTopic ,newsHot ,newsLevel );
        return PageData(selectedPage);
    }

    @LogRecord(name = "getEconomyNewsStatisticsById" ,description = "根据ID获取新闻基本信息")
    @PostMapping("/getEconomyNewsStatisticsById")
    public Object getEconomyNewsStatisticsById(String id){
        EconomyNewsStatictis companyInfo = economyNewsStatictisService.selectById(id);
        return ResponseEntity.ok(MapSuccess("查询成功",companyInfo));
    }

    @LogRecord(name = "editEconomyNewsStatistics" ,description = "编辑新闻基本信息")
    @PostMapping("/editEconomyNewsStatistics")
    public Object editEconomyNewsStatistics(@RequestBody EconomyNewsStatictis body){
        economyNewsStatictisService.updateById(body);
        return ResponseEntity.ok(MapSuccess("保存成功！"));
    }

    @LogRecord(name = "getForexNewsStatisticsList" ,description = "查询外汇新闻信息列表")
    @PostMapping("/getForexNewsStatisticsList")
    public Object getForexNewsStatisticsList(@RequestParam(required = false, defaultValue = PAGE_NUM) Integer page,
                                               @RequestParam(required = false, defaultValue = PAGE_SIZE) Integer size,
                                               String newsTitle ,
                                               String newsType ,
                                               String startDate ,
                                               String endDate ,
                                               String newsTopic ,
                                               String newsHot){
        Page selectedPage = forexNewsStatictisService.getForexNewsStatisticsList( page , size ,newsTitle ,newsType ,startDate ,endDate , newsTopic ,newsHot );
        return PageData(selectedPage);
    }


    @LogRecord(name = "getForexNewsStatisticsById" ,description = "根据ID获取外汇新闻基本信息")
    @PostMapping("/getForexNewsStatisticsById")
    public Object getForexNewsStatisticsById(String id){
        ForexNewsStatictis forexNew = forexNewsStatictisService.selectById(id);
        return ResponseEntity.ok(MapSuccess("查询成功",forexNew));
    }

    @LogRecord(name = "editForexNewsStatistics" ,description = "编辑新闻基本信息")
    @PostMapping("/editForexNewsStatistics")
    public Object editForexNewsStatistics(@RequestBody ForexNewsStatictis body){
        forexNewsStatictisService.updateById(body);
        return ResponseEntity.ok(MapSuccess("保存成功！"));
    }

}
