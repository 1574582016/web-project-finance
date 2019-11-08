package com.sky.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.sky.annotation.LogRecord;
import com.sky.api.AbstractController;
import com.sky.model.IndexDealData;
import com.sky.vo.IndexConstituent_VO;
import com.sky.vo.MacroEconomy_VO;
import org.apache.commons.collections.map.HashedMap;
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
}
