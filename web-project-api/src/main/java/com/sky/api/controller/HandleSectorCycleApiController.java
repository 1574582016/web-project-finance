package com.sky.api.controller;

import com.alibaba.fastjson.JSONArray;
import com.sky.annotation.LogRecord;
import com.sky.api.AbstractController;
import com.sky.model.SectorRiseRate;
import com.sky.vo.StockIndexMonthData_VO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ThinkPad on 2019/11/26.
 */
@RestController
@RequestMapping("/api/handleCycle")
public class HandleSectorCycleApiController extends AbstractController {

    @LogRecord(name = "getSectorCycleList" ,description = "查询自定指标数据")
    @PostMapping("/getSectorCycleList")
    public Object getSectorCycleList(String startDay, String sectorLevel, String dealPeriod, String firstSector, String secondSector, String thirdSecotor, String forthSector){
        List<SectorRiseRate> list = sectorRiseRateService.getSectorCycleList( startDay, sectorLevel, dealPeriod, firstSector, secondSector, thirdSecotor, forthSector);
        Map<String ,JSONArray> map = new HashMap<>();
        JSONArray titleArr = new JSONArray();
        JSONArray rateArr = new JSONArray();
        JSONArray upperArr = new JSONArray();
        JSONArray shockArr = new JSONArray();
        for(SectorRiseRate data_vo : list){
            if(dealPeriod.equals("3")){
                titleArr.add("1月");
                titleArr.add("2月");
                titleArr.add("3月");
                titleArr.add("4月");
                titleArr.add("5月");
                titleArr.add("6月");
                titleArr.add("7月");
                titleArr.add("8月");
                titleArr.add("9月");
                titleArr.add("10月");
                titleArr.add("11月");
                titleArr.add("12月");
            }
            if(dealPeriod.equals("2")){
                titleArr.add( "第1周/" +  data_vo.getPointMonth() + "月");
                titleArr.add( "第2周/" +  data_vo.getPointMonth() + "月");
                titleArr.add( "第3周/" +  data_vo.getPointMonth() + "月");
                titleArr.add( "第4周/" +  data_vo.getPointMonth() + "月");
                titleArr.add( "第5周/" +  data_vo.getPointMonth() + "月");
            }
            if(dealPeriod.equals("1")){
                titleArr.add( "第1天/第" +  data_vo.getPointWeek() + "周/" +  data_vo.getPointMonth() + "月");
                titleArr.add( "第2天/第" +  data_vo.getPointWeek() + "周/" +  data_vo.getPointMonth() + "月");
                titleArr.add( "第3天/第" +  data_vo.getPointWeek() + "周/" +  data_vo.getPointMonth() + "月");
                titleArr.add( "第4天/第" +  data_vo.getPointWeek() + "周/" +  data_vo.getPointMonth() + "月");
                titleArr.add( "第5天/第" +  data_vo.getPointWeek() + "周/" +  data_vo.getPointMonth() + "月");
            }
            rateArr.add(data_vo.getOneRise());
            rateArr.add(data_vo.getTowRise());
            rateArr.add(data_vo.getThreeRise());
            rateArr.add(data_vo.getFourRise());
            rateArr.add(data_vo.getFiveRise());
            if(dealPeriod.equals("3")){
                rateArr.add(data_vo.getSixRise());
                rateArr.add(data_vo.getSevenRise());
                rateArr.add(data_vo.getEightRise());
                rateArr.add(data_vo.getNineRise());
                rateArr.add(data_vo.getTenRise());
                rateArr.add(data_vo.getElevenRise());
                rateArr.add(data_vo.getTwelveRise());
            }

            upperArr.add(data_vo.getOneAmplitude());
            upperArr.add(data_vo.getTowAmplitude());
            upperArr.add(data_vo.getTenAmplitude());
            upperArr.add(data_vo.getFourAmplitude());
            upperArr.add(data_vo.getFiveAmplitude());
            if(dealPeriod.equals("3")){
                upperArr.add(data_vo.getSixAmplitude());
                upperArr.add(data_vo.getSevenAmplitude());
                upperArr.add(data_vo.getEightAmplitude());
                upperArr.add(data_vo.getNineAmplitude());
                upperArr.add(data_vo.getTenAmplitude());
                upperArr.add(data_vo.getElevenAmplitude());
                upperArr.add(data_vo.getTwelveAmplitude());
            }

            shockArr.add(data_vo.getOneShock());
            shockArr.add(data_vo.getTowShock());
            shockArr.add(data_vo.getThreeShock());
            shockArr.add(data_vo.getFourShock());
            shockArr.add(data_vo.getFiveShock());
            if(dealPeriod.equals("3")){
                shockArr.add(data_vo.getSixShock());
                shockArr.add(data_vo.getSevenShock());
                shockArr.add(data_vo.getEightShock());
                shockArr.add(data_vo.getNineShock());
                shockArr.add(data_vo.getTenShock());
                shockArr.add(data_vo.getElevenShock());
                shockArr.add(data_vo.getTwelveShock());
            }
        }
        map.put("titleArr" , titleArr);
        map.put("rateArr" , rateArr);
        map.put("upperArr" , upperArr);
        map.put("shockArr" , shockArr);

        return ResponseEntity.ok(MapSuccess("查询成功",map));
    }

}
