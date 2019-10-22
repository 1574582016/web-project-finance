package com.sky.api.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sky.annotation.LogRecord;
import com.sky.api.AbstractController;
import com.sky.core.model.TreeNode;
import com.sky.model.StockPoolSecondClass;
import com.sky.model.StockSectorClass;
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
}
