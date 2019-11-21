package com.sky.api.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sky.annotation.LogRecord;
import com.sky.api.AbstractController;
import com.sky.core.model.TreeNode;
import com.sky.model.StockMarketClass;
import com.sky.model.StockPoolSecondClass;
import com.sky.model.StockSectorClass;
import com.sky.vo.StockIndexMonthData_VO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ThinkPad on 2019/10/22.
 */
@RestController
@RequestMapping("/api/stockSector")
public class StockSectorApiController extends AbstractController {

    @LogRecord(name = "getStockSectorClassList" ,description = "查询行业分类信息")
    @PostMapping("/getStockSectorClassList")
    public Object getStockSectorClassList(String parentCode){
        List<StockSectorClass> list = stockSectorClassService.selectList(new EntityWrapper<StockSectorClass>().where("isvalid = 1 and parent_code = {0}" , parentCode));
        return ResponseEntity.ok(MapSuccess("查询成功", list));
    }


    @LogRecord(name = "getStockHotClassList" ,description = "查询热点分类信息")
    @PostMapping("/getStockHotClassList")
    public Object getStockHotClassList(String firstClass ,String secondClass ,String thirdClass){
        EntityWrapper<StockMarketClass> entityWrapper = new EntityWrapper<>();
        if(StringUtils.isNotBlank(firstClass)){
            entityWrapper.where("class_type = '概念板块' and first_class = {0}" , firstClass).groupBy("second_class");
        }else if(StringUtils.isNotBlank(secondClass)){
            entityWrapper.where("class_type = '概念板块' and second_class = {0}" , secondClass).groupBy("third_class");
        }else if(StringUtils.isNotBlank(thirdClass)){
            entityWrapper.where("class_type = '概念板块' and third_class = {0}" , thirdClass).groupBy("class_name");
        }else{
            entityWrapper.where("class_type = '概念板块'").groupBy("first_class");
        }

        List<StockMarketClass> list = stockMarketClassService.selectList(entityWrapper);
        return ResponseEntity.ok(MapSuccess("查询成功", list));
    }


    @LogRecord(name = "getStockDifferentSectorMonthList" ,description = "查询不同行业月数据")
    @PostMapping("/getStockDifferentSectorMonthList")
    public Object getStockDifferentSectorMonthList(String indexNames ,String startDay ,String endDay){
        String[] indexName = indexNames.split(",");
        List<String> indexList = new ArrayList<>();
        for(int i = 0 ; i < indexName.length ; i++){
            indexList.add(indexName[i]);
        }
        List<StockIndexMonthData_VO> voList = indexDealDataService.getStockDifferentSectorMonthList(indexList , startDay , endDay);
        Map<String ,JSONArray> map = new HashMap<>();
        JSONArray titalArr = new JSONArray();
        JSONArray rateList = new JSONArray();
        JSONArray upperList = new JSONArray();
        JSONArray shockList = new JSONArray();
        for(StockIndexMonthData_VO data_vo : voList){
            titalArr.add(data_vo.getIndexName());

            JSONObject rateBar = new JSONObject();
            rateBar.put("name" , data_vo.getIndexName());
            rateBar.put("type" , "bar");
            rateBar.put("itemStyle" ,  JSONObject.parseObject("{normal : {label: {show: true, position: 'top',textStyle: {color: 'black'}}}}"));
            JSONArray rateArr = new JSONArray();
            rateArr.add(data_vo.getOneRiseRate());
            rateArr.add(data_vo.getTowRiseRate());
            rateArr.add(data_vo.getThreeRiseRate());
            rateArr.add(data_vo.getFourRiseRate());
            rateArr.add(data_vo.getFiveRiseRate());
            rateArr.add(data_vo.getSixRiseRate());
            rateArr.add(data_vo.getSevenRiseRate());
            rateArr.add(data_vo.getEightRiseRate());
            rateArr.add(data_vo.getNineRiseRate());
            rateArr.add(data_vo.getTenRiseRate());
            rateArr.add(data_vo.getElevenRiseRate());
            rateArr.add(data_vo.getTwelveRiseRate());
            rateBar.put("data" , rateArr);
            rateList.add(rateBar);

            JSONObject upperBar = new JSONObject();
            upperBar.put("name" , data_vo.getIndexName());
            upperBar.put("type" , "bar");
            upperBar.put("itemStyle" ,  JSONObject.parseObject("{normal : {label: {show: true, position: 'top',textStyle: {color: 'black'}}}}"));
            JSONArray upperArr = new JSONArray();
            upperArr.add(data_vo.getOneUpperAverage());
            upperArr.add(data_vo.getTowUpperAverage());
            upperArr.add(data_vo.getThreeUpperAverage());
            upperArr.add(data_vo.getFourUpperAverage());
            upperArr.add(data_vo.getFiveUpperAverage());
            upperArr.add(data_vo.getSixUpperAverage());
            upperArr.add(data_vo.getSevenUpperAverage());
            upperArr.add(data_vo.getEightUpperAverage());
            upperArr.add(data_vo.getNineUpperAverage());
            upperArr.add(data_vo.getTenUpperAverage());
            upperArr.add(data_vo.getElvenUpperAverage());
            upperArr.add(data_vo.getTwelveUpperAverage());
            upperBar.put("data" , upperArr);
            upperList.add(upperBar);


            JSONObject shockBar = new JSONObject();
            shockBar.put("name" , data_vo.getIndexName());
            shockBar.put("type" , "bar");
            shockBar.put("itemStyle" , JSONObject.parseObject("{normal : {label: {show: true, position: 'top',textStyle: {color: 'black'}}}}"));
            JSONArray shockArr = new JSONArray();
            shockArr.add(data_vo.getOneShockAverage());
            shockArr.add(data_vo.getTowShockAverage());
            shockArr.add(data_vo.getThreeShockAverage());
            shockArr.add(data_vo.getFourShockAverage());
            shockArr.add(data_vo.getFiveShockAverage());
            shockArr.add(data_vo.getSixShockAverage());
            shockArr.add(data_vo.getSevenShockAverage());
            shockArr.add(data_vo.getEightShockAverage());
            shockArr.add(data_vo.getNineShockAverage());
            shockArr.add(data_vo.getTenShockAverage());
            shockArr.add(data_vo.getElvenShockAverage());
            shockArr.add(data_vo.getTwelveShockAverage());
            shockBar.put("data" , shockArr);
            shockList.add(shockBar);

        }
        map.put("titalArr" , titalArr);
        map.put("rateArr" , rateList);
        map.put("upperArr" , upperList);
        map.put("shockArr" , shockList);

        return ResponseEntity.ok(MapSuccess("查询成功",map));
    }


    @LogRecord(name = "getStockSubSectorMonthList" ,description = "查询子行业月数据")
    @PostMapping("/getStockSubSectorMonthList")
    public Object getStockSubSectorMonthList(String indexName ,String startDay ,String endDay){
        List<StockIndexMonthData_VO> voList = indexDealDataService.getStockSectorMonthDataList(indexName , null, startDay, endDay , null);
        Map<String ,JSONArray> map = new HashMap<>();
        JSONArray titalArr = new JSONArray();
        JSONArray rateList = new JSONArray();
        JSONArray upperList = new JSONArray();
        JSONArray shockList = new JSONArray();
        for(StockIndexMonthData_VO data_vo : voList){
            titalArr.add(data_vo.getForthSector());

            JSONObject rateBar = new JSONObject();
            rateBar.put("name" , data_vo.getForthSector());
            rateBar.put("type" , "bar");
            rateBar.put("itemStyle" ,  JSONObject.parseObject("{normal : {label: {show: true, position: 'top',textStyle: {color: 'black'}}}}"));
            JSONArray rateArr = new JSONArray();
            rateArr.add(data_vo.getOneRiseRate());
            rateArr.add(data_vo.getTowRiseRate());
            rateArr.add(data_vo.getThreeRiseRate());
            rateArr.add(data_vo.getFourRiseRate());
            rateArr.add(data_vo.getFiveRiseRate());
            rateArr.add(data_vo.getSixRiseRate());
            rateArr.add(data_vo.getSevenRiseRate());
            rateArr.add(data_vo.getEightRiseRate());
            rateArr.add(data_vo.getNineRiseRate());
            rateArr.add(data_vo.getTenRiseRate());
            rateArr.add(data_vo.getElevenRiseRate());
            rateArr.add(data_vo.getTwelveRiseRate());
            rateBar.put("data" , rateArr);
            rateList.add(rateBar);

            JSONObject upperBar = new JSONObject();
            upperBar.put("name" , data_vo.getForthSector());
            upperBar.put("type" , "bar");
            upperBar.put("itemStyle" ,  JSONObject.parseObject("{normal : {label: {show: true, position: 'top',textStyle: {color: 'black'}}}}"));
            JSONArray upperArr = new JSONArray();
            upperArr.add(data_vo.getOneUpperAverage());
            upperArr.add(data_vo.getTowUpperAverage());
            upperArr.add(data_vo.getThreeUpperAverage());
            upperArr.add(data_vo.getFourUpperAverage());
            upperArr.add(data_vo.getFiveUpperAverage());
            upperArr.add(data_vo.getSixUpperAverage());
            upperArr.add(data_vo.getSevenUpperAverage());
            upperArr.add(data_vo.getEightUpperAverage());
            upperArr.add(data_vo.getNineUpperAverage());
            upperArr.add(data_vo.getTenUpperAverage());
            upperArr.add(data_vo.getElvenUpperAverage());
            upperArr.add(data_vo.getTwelveUpperAverage());
            upperBar.put("data" , upperArr);
            upperList.add(upperBar);


            JSONObject shockBar = new JSONObject();
            shockBar.put("name" , data_vo.getForthSector());
            shockBar.put("type" , "bar");
            shockBar.put("itemStyle" , JSONObject.parseObject("{normal : {label: {show: true, position: 'top',textStyle: {color: 'black'}}}}"));
            JSONArray shockArr = new JSONArray();
            shockArr.add(data_vo.getOneShockAverage());
            shockArr.add(data_vo.getTowShockAverage());
            shockArr.add(data_vo.getThreeShockAverage());
            shockArr.add(data_vo.getFourShockAverage());
            shockArr.add(data_vo.getFiveShockAverage());
            shockArr.add(data_vo.getSixShockAverage());
            shockArr.add(data_vo.getSevenShockAverage());
            shockArr.add(data_vo.getEightShockAverage());
            shockArr.add(data_vo.getNineShockAverage());
            shockArr.add(data_vo.getTenShockAverage());
            shockArr.add(data_vo.getElvenShockAverage());
            shockArr.add(data_vo.getTwelveShockAverage());
            shockBar.put("data" , shockArr);
            shockList.add(shockBar);

        }
        map.put("titalArr" , titalArr);
        map.put("rateArr" , rateList);
        map.put("upperArr" , upperList);
        map.put("shockArr" , shockList);

        return ResponseEntity.ok(MapSuccess("查询成功",map));
    }
}
