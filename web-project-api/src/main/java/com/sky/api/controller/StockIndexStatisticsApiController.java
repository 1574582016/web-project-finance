package com.sky.api.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.sky.annotation.LogRecord;
import com.sky.api.AbstractController;
import com.sky.vo.IndexConstituent_VO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
