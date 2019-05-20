package com.sky.api.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.sky.annotation.LogRecord;
import com.sky.api.AbstractController;
import com.sky.core.consts.SystemConst;
import com.sky.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

/**
 * Created by ThinkPad on 2019/5/16.
 */
@RestController
@RequestMapping("/api/invest")
public class InvestApiController extends AbstractController {

    @LogRecord(name = "getInvestForexReplayList" ,description = "查询外汇复盘信息列表")
    @PostMapping("/getInvestForexReplayList")
    public Object getInvestForexReplayList(@RequestParam(required = false, defaultValue = PAGE_NUM) Integer page,
                                           @RequestParam(required = false, defaultValue = PAGE_SIZE) Integer size,
                                           String investStrategy ,
                                           String startDate ,
                                           String endDate ){
        SystemUser systemUser = (SystemUser)getSession().getAttribute(SystemConst.SYSTEMUSER);
        Page selectedPage = investForexReplayService.getInvestForexReplayList( page , size , investStrategy, startDate, endDate , systemUser.getUserName());
        return PageData(selectedPage);
    }

    @LogRecord(name = "editInvestForexReplay" ,description = "编辑外汇复盘信息")
    @PostMapping("/editInvestForexReplay")
    public Object editInvestForexReplay(@RequestBody InvestForexReplay body){
        System.out.println(body.toString());
        String replayCode  = IdWorker.getIdStr();
        body.setReplayCode(replayCode);
        List<InvestForexReplayDetail> list = body.getDetailList();
        BigDecimal successNum = BigDecimal.ZERO;
        BigDecimal failNum = BigDecimal.ZERO;
        BigDecimal flatNum = BigDecimal.ZERO;
        BigDecimal successPoint = BigDecimal.ZERO;
        BigDecimal failPoint = BigDecimal.ZERO;
        for(InvestForexReplayDetail detail : list){
            detail.setReplayCode(replayCode);
           if(detail.getGainPoint().compareTo(BigDecimal.ZERO)>0){
               successNum = successNum.add(BigDecimal.ONE);
               successPoint = successPoint.add(detail.getGainPoint());
           }
            if(detail.getGainPoint().compareTo(BigDecimal.ZERO)==0){
                flatNum = flatNum.add(BigDecimal.ONE);
            }
            if(detail.getGainPoint().compareTo(BigDecimal.ZERO)<0){
                failNum = failNum.add(BigDecimal.ONE);
                failPoint = failPoint.add(detail.getGainPoint());
            }
        }
        int subNum = list.size() ;
        MathContext mc = new MathContext(4, RoundingMode.HALF_DOWN);
        body.setSeccussRate(successNum.divide(new BigDecimal(subNum),mc).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP));
        body.setEarnPoint(successPoint);
        body.setFailRate(failNum.divide(new BigDecimal(subNum),mc).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP));
        body.setLosePoint(failPoint);
        body.setFlatRate(flatNum);
        boolean just = investForexReplayService.insertOrUpdate(body);
        if(just){
            investForexReplayDetailService.insertBatch(list);
        }
        return ResponseEntity.ok(MapSuccess("保存成功！"));
    }

    @LogRecord(name = "getInvestForexReplay" ,description = "根据ID获取复盘基本信息")
    @PostMapping("/getInvestForexReplay")
    public Object getInvestForexReplay(String id){
        InvestForexReplay body = investForexReplayService.selectById(id);
        if(body != null){
            List<InvestForexReplayDetail> list = investForexReplayDetailService.selectList(new EntityWrapper<InvestForexReplayDetail>().where("isvalid = 1 and replay_code = {0}" , body.getReplayCode()));
            body.setDetailList(list);
        }
        return ResponseEntity.ok(MapSuccess("查询成功",body));
    }

    @LogRecord(name = "getInvestForexOperateList" ,description = "查询外汇操盘信息列表")
    @PostMapping("/getInvestForexOperateList")
    public Object getInvestForexOperateList(@RequestParam(required = false, defaultValue = PAGE_NUM) Integer page,
                                           @RequestParam(required = false, defaultValue = PAGE_SIZE) Integer size,
                                           String investStrategy ,
                                           String startDate ,
                                           String endDate ){
        SystemUser systemUser = (SystemUser)getSession().getAttribute(SystemConst.SYSTEMUSER);
        Page selectedPage = investForexOperateService.getInvestForexOperateList( page , size , investStrategy, startDate, endDate , systemUser.getUserName());
        return PageData(selectedPage);
    }

    @LogRecord(name = "editInvestForexOperate" ,description = "编辑外汇复盘信息")
    @PostMapping("/editInvestForexOperate")
    public Object editInvestForexOperate(@RequestBody InvestForexOperate body){
        String replayCode  = IdWorker.getIdStr();
        body.setOperateCode(replayCode);
        investForexOperateService.insert(body);
        return ResponseEntity.ok(MapSuccess("保存成功！"));
    }
}
