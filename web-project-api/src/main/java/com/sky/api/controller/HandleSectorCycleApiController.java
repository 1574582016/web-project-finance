package com.sky.api.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sky.annotation.LogRecord;
import com.sky.api.AbstractController;
import com.sky.model.SectorRiseRate;
import com.sky.model.StockRiseRate;
import com.sky.vo.PointMonthStock_VO;
import com.sky.vo.StaticSectorNum_VO;
import com.sky.vo.StockIndexMonthData_VO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @LogRecord(name = "getStaticSectorNum" ,description = "查询每月行业占比")
    @PostMapping("/getStaticSectorNum")
    public Object getStaticSectorNum(String staticDate, String staticMonth, String staticRate, String staticAmplitude, String sectorType, String firstSector, String secondSector, String thirdSecotor){
        firstSector = firstSector.split("-")[0];
        secondSector = secondSector.split("-")[0];
        thirdSecotor = thirdSecotor.split("-")[0];
        List<StaticSectorNum_VO> list = stockRiseRateService.getStaticSectorNum( staticDate, staticMonth, staticRate, staticAmplitude, sectorType, firstSector, secondSector, thirdSecotor);
        JSONArray jsonArray = new JSONArray();
        for(StaticSectorNum_VO num_vo : list){
            JSONObject jsonObject = new JSONObject();
            if(sectorType.equals("1")){
                jsonObject.put("name" , num_vo.getFirstSector() + "-" + num_vo.getSectorNum());
                jsonObject.put("value" , num_vo.getSectorNum());
            }
            if(sectorType.equals("2")){
                jsonObject.put("name" , num_vo.getSecondSector() + "-" + num_vo.getSectorNum());
                jsonObject.put("value" , num_vo.getSectorNum());
            }
            if(sectorType.equals("3")){
                jsonObject.put("name" , num_vo.getThirdSecotor() + "-" + num_vo.getSectorNum());
                jsonObject.put("value" , num_vo.getSectorNum());
            }
            if(sectorType.equals("4")){
                jsonObject.put("name" , num_vo.getForthSector() + "-" + num_vo.getSectorNum());
                jsonObject.put("value" , num_vo.getSectorNum());
            }
            jsonArray.add(jsonObject);
        }

        Map<String ,JSONArray> map = new HashMap<>();
        map.put("sectorNum" , jsonArray);
        return ResponseEntity.ok(MapSuccess("查询成功",map));
    }

    @LogRecord(name = "getPointMonthStockList" ,description = "查询每月增长企业")
    @PostMapping("/getPointMonthStockList")
    public Object getPointMonthStockList(@RequestParam(required = false, defaultValue = PAGE_NUM) Integer page,
                                         @RequestParam(required = false, defaultValue = PAGE_SIZE) Integer size,
                                         String staticDate,
                                         String staticMonth,
                                         String staticRate,
                                         String staticAmplitude,
                                         String firstSector,
                                         String secondSector,
                                         String thirdSecotor,
                                         String forthSector){
        firstSector = firstSector.split("-")[0];
        secondSector = secondSector.split("-")[0];
        thirdSecotor = thirdSecotor.split("-")[0];
        forthSector = forthSector.split("-")[0];
        Page selectedPage = stockRiseRateService.getPointMonthStockList(page , size , staticDate, staticMonth, staticRate, staticAmplitude , firstSector, secondSector, thirdSecotor, forthSector );
        return PageData(selectedPage);
    }


    @LogRecord(name = "getPointWeekStockList" ,description = "查询每周增长企业")
    @PostMapping("/getPointWeekStockList")
    public Object getPointWeekStockList(@RequestParam(required = false, defaultValue = PAGE_NUM) Integer page,
                                         @RequestParam(required = false, defaultValue = PAGE_SIZE) Integer size,
                                         String staticDate,
                                         String staticMonth,
                                         String staticWeek,
                                         String staticRate,
                                         String staticAmplitude){
        Page selectedPage = stockRiseRateService.getPointWeekStockList(page , size , staticDate, staticMonth, staticWeek , staticRate, staticAmplitude );
        return PageData(selectedPage);
    }

    @LogRecord(name = "getPointStockMonthList" ,description = "查询企业月增长分布")
    @PostMapping("/getPointStockMonthList")
    public Object getPointStockMonthList(String stockCode , String staticDate){
        StockRiseRate riseRate = stockRiseRateService.selectOne(new EntityWrapper<StockRiseRate>().where("stock_code = {0} and start_time = {1} and deal_period = 3" , stockCode ,staticDate));
        Map<String ,JSONArray> map = new HashMap<>();
        JSONArray rateArr = new JSONArray();
        JSONArray upperArr = new JSONArray();
        JSONArray shockArr = new JSONArray();
        rateArr.add(riseRate.getOneRise());
        rateArr.add(riseRate.getTowRise());
        rateArr.add(riseRate.getThreeRise());
        rateArr.add(riseRate.getFourRise());
        rateArr.add(riseRate.getFiveRise());
        rateArr.add(riseRate.getSixRise());
        rateArr.add(riseRate.getSevenRise());
        rateArr.add(riseRate.getEightRise());
        rateArr.add(riseRate.getNineRise());
        rateArr.add(riseRate.getTenRise());
        rateArr.add(riseRate.getElevenRise());
        rateArr.add(riseRate.getTwelveRise());

        upperArr.add(riseRate.getOneAmplitude());
        upperArr.add(riseRate.getTowAmplitude());
        upperArr.add(riseRate.getThreeAmplitude());
        upperArr.add(riseRate.getFourAmplitude());
        upperArr.add(riseRate.getFiveAmplitude());
        upperArr.add(riseRate.getSixAmplitude());
        upperArr.add(riseRate.getSevenAmplitude());
        upperArr.add(riseRate.getEightAmplitude());
        upperArr.add(riseRate.getNineAmplitude());
        upperArr.add(riseRate.getTenAmplitude());
        upperArr.add(riseRate.getElevenAmplitude());
        upperArr.add(riseRate.getTwelveAmplitude());

        shockArr.add(riseRate.getOneShock());
        shockArr.add(riseRate.getTowShock());
        shockArr.add(riseRate.getThreeShock());
        shockArr.add(riseRate.getFourShock());
        shockArr.add(riseRate.getFiveShock());
        shockArr.add(riseRate.getSixShock());
        shockArr.add(riseRate.getSevenShock());
        shockArr.add(riseRate.getEightShock());
        shockArr.add(riseRate.getNineShock());
        shockArr.add(riseRate.getTenShock());
        shockArr.add(riseRate.getElevenShock());
        shockArr.add(riseRate.getTwelveShock());
        map.put("rateArr" , rateArr);
        map.put("upperArr" , upperArr);
        map.put("shockArr" , shockArr);
        return ResponseEntity.ok(MapSuccess("查询成功",map));
    }


    @LogRecord(name = "getPointStockWeekList" ,description = "查询企业周增长分布")
    @PostMapping("/getPointStockWeekList")
    public Object getPointStockWeekList(String stockCode , String staticDate){
        List<StockRiseRate> list = stockRiseRateService.selectList(new EntityWrapper<StockRiseRate>().where("stock_code = {0} and start_time = {1} and deal_period = 2" , stockCode ,staticDate));
        Map<String ,JSONArray> map = new HashMap<>();
        JSONArray titleArr = new JSONArray();
        JSONArray rateArr = new JSONArray();
        JSONArray upperArr = new JSONArray();
        JSONArray shockArr = new JSONArray();
        for(StockRiseRate riseRate : list){
            titleArr.add("第1周/" + riseRate.getPointMonth() + "月");
            titleArr.add("第2周/" + riseRate.getPointMonth() + "月");
            titleArr.add("第3周/" + riseRate.getPointMonth() + "月");
            titleArr.add("第4周/" + riseRate.getPointMonth() + "月");
            titleArr.add("第5周/" + riseRate.getPointMonth() + "月");

            rateArr.add(riseRate.getOneRise());
            rateArr.add(riseRate.getTowRise());
            rateArr.add(riseRate.getThreeRise());
            rateArr.add(riseRate.getFourRise());
            rateArr.add(riseRate.getFiveRise());

            upperArr.add(riseRate.getOneAmplitude());
            upperArr.add(riseRate.getTowAmplitude());
            upperArr.add(riseRate.getThreeAmplitude());
            upperArr.add(riseRate.getFourAmplitude());
            upperArr.add(riseRate.getFiveAmplitude());

            shockArr.add(riseRate.getOneShock());
            shockArr.add(riseRate.getTowShock());
            shockArr.add(riseRate.getThreeShock());
            shockArr.add(riseRate.getFourShock());
            shockArr.add(riseRate.getFiveShock());
        }
        map.put("titleArr" , titleArr);
        map.put("rateArr" , rateArr);
        map.put("upperArr" , upperArr);
        map.put("shockArr" , shockArr);
        return ResponseEntity.ok(MapSuccess("查询成功",map));
    }

    @LogRecord(name = "getPointStockDayList" ,description = "查询企业天增长分布")
    @PostMapping("/getPointStockDayList")
    public Object getPointStockDayList(String stockCode , String staticDate){
        List<StockRiseRate> list = stockRiseRateService.selectList(new EntityWrapper<StockRiseRate>().where("stock_code = {0} and start_time = {1} and deal_period = 1" , stockCode ,staticDate));
        Map<String ,JSONArray> map = new HashMap<>();
        JSONArray titleArr = new JSONArray();
        JSONArray rateArr = new JSONArray();
        JSONArray upperArr = new JSONArray();
        JSONArray shockArr = new JSONArray();
        for(StockRiseRate riseRate : list){
            titleArr.add("第1天/第" + riseRate.getPointWeek() + "周/"  + riseRate.getPointMonth() + "月");
            titleArr.add("第2天/第" + riseRate.getPointWeek() + "周/" + riseRate.getPointMonth() + "月");
            titleArr.add("第3天/第" + riseRate.getPointWeek() + "周/" + riseRate.getPointMonth() + "月");
            titleArr.add("第4天/第" + riseRate.getPointWeek() + "周/" + riseRate.getPointMonth() + "月");
            titleArr.add("第5天/第" + riseRate.getPointWeek() + "周/" + riseRate.getPointMonth() + "月");

            rateArr.add(riseRate.getOneRise());
            rateArr.add(riseRate.getTowRise());
            rateArr.add(riseRate.getThreeRise());
            rateArr.add(riseRate.getFourRise());
            rateArr.add(riseRate.getFiveRise());

            upperArr.add(riseRate.getOneAmplitude());
            upperArr.add(riseRate.getTowAmplitude());
            upperArr.add(riseRate.getThreeAmplitude());
            upperArr.add(riseRate.getFourAmplitude());
            upperArr.add(riseRate.getFiveAmplitude());

            shockArr.add(riseRate.getOneShock());
            shockArr.add(riseRate.getTowShock());
            shockArr.add(riseRate.getThreeShock());
            shockArr.add(riseRate.getFourShock());
            shockArr.add(riseRate.getFiveShock());
        }
        map.put("titleArr" , titleArr);
        map.put("rateArr" , rateArr);
        map.put("upperArr" , upperArr);
        map.put("shockArr" , shockArr);
        return ResponseEntity.ok(MapSuccess("查询成功",map));
    }

    @LogRecord(name = "createMonthDealReport" ,description = "创建月度交易报告")
    @PostMapping("/createMonthDealReport")
    public Object createMonthDealReport(Integer staticMonth , String staticDate){

        return ResponseEntity.ok(MapSuccess("查询成功"));
    }
}
