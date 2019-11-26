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

    @LogRecord(name = "getSectorCycleMonthList" ,description = "查询自定指标月数据")
    @PostMapping("/getSectorCycleMonthList")
    public Object getSectorCycleMonthList(String sectorName ,String startDay ,String sectorLevel){
        List<SectorRiseRate> list = sectorRiseRateService.getSectorCycleMonthList(sectorName , startDay , sectorLevel);
        SectorRiseRate data_vo = list.get(0);
        Map<String ,JSONArray> map = new HashMap<>();

        JSONArray rateArr = new JSONArray();
        rateArr.add(data_vo.getOneRise());
        rateArr.add(data_vo.getTowRise());
        rateArr.add(data_vo.getThreeRise());
        rateArr.add(data_vo.getFourRise());
        rateArr.add(data_vo.getFiveRise());
        rateArr.add(data_vo.getSixRise());
        rateArr.add(data_vo.getSevenRise());
        rateArr.add(data_vo.getEightRise());
        rateArr.add(data_vo.getNineRise());
        rateArr.add(data_vo.getTenRise());
        rateArr.add(data_vo.getElevenRise());
        rateArr.add(data_vo.getTwelveRise());
        map.put("rateArr" , rateArr);

        JSONArray upperArr = new JSONArray();
        upperArr.add(data_vo.getOneAmplitude());
        upperArr.add(data_vo.getTowAmplitude());
        upperArr.add(data_vo.getTenAmplitude());
        upperArr.add(data_vo.getFourAmplitude());
        upperArr.add(data_vo.getFiveAmplitude());
        upperArr.add(data_vo.getSixAmplitude());
        upperArr.add(data_vo.getSevenAmplitude());
        upperArr.add(data_vo.getEightAmplitude());
        upperArr.add(data_vo.getNineAmplitude());
        upperArr.add(data_vo.getTenAmplitude());
        upperArr.add(data_vo.getElevenAmplitude());
        upperArr.add(data_vo.getTwelveAmplitude());
        map.put("upperArr" , upperArr);

        JSONArray shockArr = new JSONArray();
        shockArr.add(data_vo.getOneShock());
        shockArr.add(data_vo.getTowShock());
        shockArr.add(data_vo.getThreeShock());
        shockArr.add(data_vo.getFourShock());
        shockArr.add(data_vo.getFiveShock());
        shockArr.add(data_vo.getSixShock());
        shockArr.add(data_vo.getSevenShock());
        shockArr.add(data_vo.getEightShock());
        shockArr.add(data_vo.getNineShock());
        shockArr.add(data_vo.getTenShock());
        shockArr.add(data_vo.getElevenShock());
        shockArr.add(data_vo.getTwelveShock());
        map.put("shockArr" , shockArr);

        return ResponseEntity.ok(MapSuccess("查询成功",map));
    }

}
