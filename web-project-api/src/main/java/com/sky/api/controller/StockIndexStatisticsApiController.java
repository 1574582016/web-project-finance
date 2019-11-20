package com.sky.api.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.sky.annotation.LogRecord;
import com.sky.api.AbstractController;
import com.sky.core.utils.DateUtils;
import com.sky.model.FuturesDealData;
import com.sky.model.IndexDealData;
import com.sky.model.StockDealData;
import com.sky.vo.*;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ThinkPad on 2019/10/2.
 */
@RestController
@RequestMapping("/api/stockIndex")
public class StockIndexStatisticsApiController extends AbstractController {

    @LogRecord(name = "getStockIndexConstituentList" ,description = "查询股指成分信息列表")
    @PostMapping("/getStockIndexConstituentList")
    public Object getStockIndexConstituentList(String indexName ){
        List<IndexConstituent_VO> list = stockIndexConstituentService.getStockIndexConstituentList(indexName);
        Map<String ,Object> map = new HashMap<String ,Object>();
        map.put("total",list.size());
        map.put("rows",list);
        return map;
    }

    @LogRecord(name = "getStockIndexDataList" ,description = "查询指标数据")
    @PostMapping("/getStockIndexDataList")
    public Object getStockIndexDataList(String indexCodes , String  dealPeriod,String startDay ,String endDay){
        Map<String,Object> resultMap = new HashedMap();
        List<String> titleArr = new ArrayList<String>();
        List<String> nameArr = new ArrayList<String>();
        List<JSONObject> dataArr = new ArrayList<>();
        String[] codes = indexCodes.split(",");
        for(int i = 0 ; i < codes.length ; i++){
            String indexCode = codes[i];
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("type" , "line");
            jsonObject.put("stack" , indexCode);
            List<BigDecimal> arrayList = new ArrayList<BigDecimal>();
            List<IndexDealData> list = indexDealDataService.getStockIndexDataList(indexCode , dealPeriod , startDay , endDay);
            for(int j = 0 ; j < list.size() ; j++){
                IndexDealData indexDealData = list.get(j);
                if(i == 0){
                    titleArr.add(indexDealData.getDealTime());
                }
                if(j == 0){
                    nameArr.add(indexDealData.getIndexName());
                    jsonObject.put("name" , indexDealData.getIndexName());
                }
                arrayList.add(indexDealData.getClosePrice());
            }
            jsonObject.put("data" ,arrayList);
            dataArr.add(jsonObject);
        }
        resultMap.put("titleArr",titleArr);
        resultMap.put("nameArr",nameArr);
        resultMap.put("dataArr",dataArr);
        return ResponseEntity.ok(MapSuccess("操作成功",resultMap));
    }


    @LogRecord(name = "getStockIndexMonthList" ,description = "查询指标月数据")
    @PostMapping("/getStockIndexMonthList")
    public Object getStockIndexMonthList(String indexName ,String startDay ,String endDay){
        StockIndexMonthData_VO data_vo = indexDealDataService.getStockIndexMonthList(indexName , startDay ,endDay);
        Map<String ,JSONArray> map = new HashMap<>();

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
        map.put("rateArr" , rateArr);

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
        map.put("upperArr" , upperArr);

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
        map.put("shockArr" , shockArr);

        return ResponseEntity.ok(MapSuccess("查询成功",map));
    }


    @LogRecord(name = "getStockIndexWeekList" ,description = "查询指标周数据")
    @PostMapping("/getStockIndexWeekList")
    public Object getStockIndexWeekList(String indexName ,String startDay ,String endDay){
        List<StockIndexWeekData_VO> list = indexDealDataService.getStockIndexWeekList(indexName , startDay ,endDay);
        Map<String ,JSONArray> map = new HashMap<>();
        JSONArray titleArr = new JSONArray();
        JSONArray rateArr = new JSONArray();
        JSONArray upperArr = new JSONArray();
        JSONArray shockArr = new JSONArray();
        for(StockIndexWeekData_VO data_vo : list){
            titleArr.add("第1周/" + data_vo.getPointTime() + "月");
            titleArr.add("第2周/" + data_vo.getPointTime() + "月");
            titleArr.add("第3周/" + data_vo.getPointTime() + "月");
            titleArr.add("第4周/" + data_vo.getPointTime() + "月");
            titleArr.add("第5周/" + data_vo.getPointTime() + "月");
            titleArr.add("第6周/" + data_vo.getPointTime() + "月");

            rateArr.add(data_vo.getOneRiseRate());
            rateArr.add(data_vo.getTowRiseRate());
            rateArr.add(data_vo.getThreeRiseRate());
            rateArr.add(data_vo.getFourRiseRate());
            rateArr.add(data_vo.getFiveRiseRate());
            rateArr.add(data_vo.getSixRiseRate());

            upperArr.add(data_vo.getOneUpperAverage());
            upperArr.add(data_vo.getTowUpperAverage());
            upperArr.add(data_vo.getThreeUpperAverage());
            upperArr.add(data_vo.getFourUpperAverage());
            upperArr.add(data_vo.getFiveUpperAverage());
            upperArr.add(data_vo.getSixUpperAverage());

            shockArr.add(data_vo.getOneShockAverage());
            shockArr.add(data_vo.getTowShockAverage());
            shockArr.add(data_vo.getThreeShockAverage());
            shockArr.add(data_vo.getFourShockAverage());
            shockArr.add(data_vo.getFiveShockAverage());
            shockArr.add(data_vo.getSixShockAverage());
        }
        map.put("titleArr" , titleArr);
        map.put("rateArr" , rateArr);
        map.put("upperArr" , upperArr);
        map.put("shockArr" , shockArr);

        return ResponseEntity.ok(MapSuccess("查询成功",map));
    }


    @LogRecord(name = "getStockIndexDayList" ,description = "查询指标天数据")
    @PostMapping("/getStockIndexDayList")
    public Object getStockIndexDayList(String indexName ,String startDay ,String endDay){
        List<StockIndexDayData_VO> list = indexDealDataService.getStockIndexDayList(indexName , startDay ,endDay);
        Map<String ,JSONArray> map = new HashMap<>();
        JSONArray titleArr = new JSONArray();
        JSONArray rateArr = new JSONArray();
        JSONArray upperArr = new JSONArray();
        JSONArray shockArr = new JSONArray();
        for(StockIndexDayData_VO data_vo : list){
            titleArr.add("第1天/" + data_vo.getPointWeek() + "周/" + data_vo.getPointMonth() + "月");
            titleArr.add("第2天/" + data_vo.getPointWeek() + "周/" + data_vo.getPointMonth() + "月");
            titleArr.add("第3天/" + data_vo.getPointWeek() + "周/" + data_vo.getPointMonth() + "月");
            titleArr.add("第4天/" + data_vo.getPointWeek() + "周/" + data_vo.getPointMonth() + "月");
            titleArr.add("第5天/" + data_vo.getPointWeek() + "周/" + data_vo.getPointMonth() + "月");


            rateArr.add(data_vo.getOneRiseRate());
            rateArr.add(data_vo.getTowRiseRate());
            rateArr.add(data_vo.getThreeRiseRate());
            rateArr.add(data_vo.getFourRiseRate());
            rateArr.add(data_vo.getFiveRiseRate());

            upperArr.add(data_vo.getOneUpperAverage());
            upperArr.add(data_vo.getTowUpperAverage());
            upperArr.add(data_vo.getThreeUpperAverage());
            upperArr.add(data_vo.getFourUpperAverage());
            upperArr.add(data_vo.getFiveUpperAverage());

            shockArr.add(data_vo.getOneShockAverage());
            shockArr.add(data_vo.getTowShockAverage());
            shockArr.add(data_vo.getThreeShockAverage());
            shockArr.add(data_vo.getFourShockAverage());
            shockArr.add(data_vo.getFiveShockAverage());
        }
        map.put("titleArr" , titleArr);
        map.put("rateArr" , rateArr);
        map.put("upperArr" , upperArr);
        map.put("shockArr" , shockArr);

        return ResponseEntity.ok(MapSuccess("查询成功",map));
    }



    @LogRecord(name = "getStockSectorMonthDataList" ,description = "查询行业月排行")
    @PostMapping("/getStockSectorMonthDataList")
    public Object getStockSectorMonthDataList(String sectorName,String startDay,String endDay ,String sectorMonth){
        List<StockIndexMonthData_VO> list = indexDealDataService.getStockSectorMonthDataList(null , sectorName, startDay, endDay , sectorMonth);
        Map<String ,Object> map = new HashMap<String ,Object>();
        map.put("total",list.size());
        map.put("rows",list);
        return map;
    }


    @LogRecord(name = "getStockSectorMonthList" ,description = "查询行业月数据")
    @PostMapping("/getStockSectorMonthList")
    public Object getStockSectorMonthList(String sectorName ,String startDay ,String endDay){
        List<StockIndexMonthData_VO> list = indexDealDataService.getStockSectorMonthDataList(null , sectorName, startDay, endDay , null);
        Map<String ,JSONArray> map = new HashMap<>();
        JSONArray rateArr = new JSONArray();
        JSONArray upperArr = new JSONArray();
        JSONArray shockArr = new JSONArray();
        if(list.size() > 0){
            StockIndexMonthData_VO data_vo = list.get(0);
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
        }
        map.put("rateArr" , rateArr);
        map.put("upperArr" , upperArr);
        map.put("shockArr" , shockArr);

        return ResponseEntity.ok(MapSuccess("查询成功",map));
    }

    @LogRecord(name = "getStockSectorWeekList" ,description = "查询行业周数据")
    @PostMapping("/getStockSectorWeekList")
    public Object getStockSectorWeekList(String dealPeriod , String stockCode ,String sectorName ,String startDay ,String endDay){
        List<StockIndexWeekData_VO> list = indexDealDataService.getStockSectorWeekList( dealPeriod , stockCode ,sectorName , startDay ,endDay);
        Map<String ,JSONArray> map = new HashMap<>();
        JSONArray titleArr = new JSONArray();
        JSONArray rateArr = new JSONArray();
        JSONArray upperArr = new JSONArray();
        JSONArray shockArr = new JSONArray();
        for(StockIndexWeekData_VO data_vo : list){
            titleArr.add("第1周/" + data_vo.getPointTime() + "月");
            titleArr.add("第2周/" + data_vo.getPointTime() + "月");
            titleArr.add("第3周/" + data_vo.getPointTime() + "月");
            titleArr.add("第4周/" + data_vo.getPointTime() + "月");
            titleArr.add("第5周/" + data_vo.getPointTime() + "月");
            titleArr.add("第6周/" + data_vo.getPointTime() + "月");

            rateArr.add(data_vo.getOneRiseRate());
            rateArr.add(data_vo.getTowRiseRate());
            rateArr.add(data_vo.getThreeRiseRate());
            rateArr.add(data_vo.getFourRiseRate());
            rateArr.add(data_vo.getFiveRiseRate());
            rateArr.add(data_vo.getSixRiseRate());

            upperArr.add(data_vo.getOneUpperAverage());
            upperArr.add(data_vo.getTowUpperAverage());
            upperArr.add(data_vo.getThreeUpperAverage());
            upperArr.add(data_vo.getFourUpperAverage());
            upperArr.add(data_vo.getFiveUpperAverage());
            upperArr.add(data_vo.getSixUpperAverage());

            shockArr.add(data_vo.getOneShockAverage());
            shockArr.add(data_vo.getTowShockAverage());
            shockArr.add(data_vo.getThreeShockAverage());
            shockArr.add(data_vo.getFourShockAverage());
            shockArr.add(data_vo.getFiveShockAverage());
            shockArr.add(data_vo.getSixShockAverage());
        }
        map.put("titleArr" , titleArr);
        map.put("rateArr" , rateArr);
        map.put("upperArr" , upperArr);
        map.put("shockArr" , shockArr);

        return ResponseEntity.ok(MapSuccess("查询成功",map));
    }

    @LogRecord(name = "getStockCompanyMonthDataList" ,description = "查询企业月数据列表")
    @PostMapping("/getStockCompanyMonthDataList")
    public Object getStockCompanyMonthDataList(String stockCode,String sectorName,String startDay,String endDay ,String sectorMonth){
        List<StockSectorCompany_VO> list = indexDealDataService.getStockCompanyMonthDataList(stockCode , sectorName, startDay, endDay , sectorMonth);
        Map<String ,Object> map = new HashMap<String ,Object>();
        map.put("total",list.size());
        map.put("rows",list);
        return map;
    }


    @LogRecord(name = "getStockCompanyMonthList" ,description = "查询企业月数据")
    @PostMapping("/getStockCompanyMonthList")
    public Object getStockCompanyMonthList(String stockCode,String sectorName,String startDay,String endDay){
        List<StockSectorCompany_VO> list = indexDealDataService.getStockCompanyMonthDataList(stockCode , sectorName, startDay, endDay , null);
        Map<String ,JSONArray> map = new HashMap<>();
        JSONArray rateArr = new JSONArray();
        JSONArray upperArr = new JSONArray();
        JSONArray shockArr = new JSONArray();
        if(list.size() > 0){
            StockSectorCompany_VO data_vo = list.get(0);
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
        }
        map.put("rateArr" , rateArr);
        map.put("upperArr" , upperArr);
        map.put("shockArr" , shockArr);

        return ResponseEntity.ok(MapSuccess("查询成功",map));
    }



    @LogRecord(name = "getStockDealDataList" ,description = "查询企业指定月份数据")
    @PostMapping("/getStockDealDataList")
    public Object getStockDealDataList(String stockCode,Integer month,String startDay,String endDay){
        List<List<String[]>> returnList = new ArrayList<>();
        if(StringUtils.isNotBlank(startDay)){
            String year = startDay.substring(0,4);
            String now = DateUtils.getYear();
            int num = Integer.parseInt(now) - Integer.parseInt(year);
            if(StringUtils.isNotBlank(endDay)){
                String year2 = endDay.substring(0,4);
                num = Integer.parseInt(year2) - Integer.parseInt(year);
            }
            for(int i = 0 ; i < num ; i++){
                int poinYear = Integer.parseInt(year) + i ;
                String pointMonth = "";
                if(month < 10){
                    pointMonth = poinYear + "-0" + month + "-01";
                }else{
                    pointMonth = poinYear + "-" + month + "-01";
                }

                List<StockDealData> list = stockDealDataService.getStockDealDataList(stockCode ,pointMonth);
                List<String[]> subList = new ArrayList<>();
                for(StockDealData dealData : list){
                    JSONArray jsonArray = new JSONArray();
                    String[] arr = {dealData.getDealTime() ,dealData.getOpenPrice().toString() , dealData.getClosePrice().toString() ,dealData.getLowPrice().toString() ,dealData.getHighPrice().toString()};
                    subList.add(arr);
                }

                returnList.add(subList);
            }
        }

        return ResponseEntity.ok(MapSuccess("查询成功",returnList));
    }
}
