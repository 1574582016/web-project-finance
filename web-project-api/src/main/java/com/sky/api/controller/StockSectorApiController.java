package com.sky.api.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sky.annotation.LogRecord;
import com.sky.api.AbstractController;
import com.sky.core.model.TreeNode;
import com.sky.model.StockMarketClass;
import com.sky.model.StockPoolSecondClass;
import com.sky.model.StockSectorClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
}
