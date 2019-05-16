package com.sky.api.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.sky.annotation.LogRecord;
import com.sky.api.AbstractController;
import com.sky.model.CompanyBaseInformation;
import com.sky.model.EconomyNewsInfluence;
import com.sky.model.EconomyNewsStatictis;
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
                                                String listCode ,
                                                String listName ){
        Page selectedPage = economyNewsStatictisService.getEconomyNewsStatisticsList( page , size);
        return PageData(selectedPage);
    }

    @LogRecord(name = "getEconomyNewsStatisticsById" ,description = "根据ID获取新闻基本信息")
    @PostMapping("/getEconomyNewsStatisticsById")
    public Object getEconomyNewsStatisticsById(String id){
        EconomyNewsStatictis companyInfo = economyNewsStatictisService.selectById(id);
        if(companyInfo != null){
            List<EconomyNewsInfluence> list = economyNewsInfluenceService.selectList(new EntityWrapper<EconomyNewsInfluence>().where("isvalid = 1 and news_code = {0}" , companyInfo.getNewsCode()));
            companyInfo.setInfluencelist(list);
        }
        return ResponseEntity.ok(MapSuccess("查询成功",companyInfo));
    }

    @LogRecord(name = "editEconomyNewsStatistics" ,description = "编辑新闻基本信息")
    @PostMapping("/editEconomyNewsStatistics")
    public Object editEconomyNewsStatistics(@RequestBody EconomyNewsStatictis body){
        if(body.getId() == null){
            String newsCode  = IdWorker.getIdStr();
            body.setNewsCode(newsCode);
        }
        economyNewsStatictisService.insertOrUpdate(body);
        List<EconomyNewsInfluence> list = body.getInfluencelist();
        List<EconomyNewsInfluence> all = economyNewsInfluenceService.selectList(new EntityWrapper<EconomyNewsInfluence>().where("isvalid = 1 and news_code = {0}" , body.getNewsCode()));
        List<EconomyNewsInfluence> delete = new ArrayList<EconomyNewsInfluence>();
        if(all.size() > 0){
            for(EconomyNewsInfluence influence : all){
                boolean just = true ;
                for(EconomyNewsInfluence subInfluence : list){
                    if(influence.getInfluenceCode().equals(subInfluence.getInfluenceCode())){
                        just = false ;
                    }
                }
                if(just){
                    influence.setIsvalid(0);
                    delete.add(influence);
                }
            }
        }
        if(delete.size() > 0){
            economyNewsInfluenceService.updateAllColumnBatchById(delete);
        }
        for(EconomyNewsInfluence influence : list){
            if(influence.getId() == null){
                influence.setInfluenceCode(IdWorker.getIdStr());
                influence.setNewsCode(body.getNewsCode());
            }
        }
        economyNewsInfluenceService.insertOrUpdateBatch(list);
        return ResponseEntity.ok(MapSuccess("保存成功！"));
    }

}
