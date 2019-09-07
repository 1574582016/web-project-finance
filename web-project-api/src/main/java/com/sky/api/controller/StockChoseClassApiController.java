package com.sky.api.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.sky.annotation.LogRecord;
import com.sky.api.AbstractController;
import com.sky.model.StockChoseClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ThinkPad on 2019/9/7.
 */
@RestController
@RequestMapping("/api/stockChose")
public class StockChoseClassApiController extends AbstractController {

    @LogRecord(name = "getStockChoseClassList" ,description = "根据父级ID获取选股类型信息")
    @PostMapping("/getStockChoseClassList")
    public Object getStockChoseClassList(String parentCode){
        Wrapper<StockChoseClass> wrapper = new EntityWrapper<>();
        wrapper.where("parent_code = {0}" , parentCode);
        List<StockChoseClass> list = stockChoseClassService.selectList(wrapper);
        return ResponseEntity.ok(MapSuccess("查询成功",list));
    }
}
