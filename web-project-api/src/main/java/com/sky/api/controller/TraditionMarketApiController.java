package com.sky.api.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.sky.annotation.LogRecord;
import com.sky.api.AbstractController;
import com.sky.model.TraditionMarket;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ThinkPad on 2018/10/20.
 */
@RestController
@RequestMapping("/api/tradition")
public class TraditionMarketApiController extends AbstractController {

    @LogRecord(name = "getTraditionMarketList" ,description = "查询理财市场列表")
    @PostMapping("/getTraditionMarketList")
    public Object getTraditionMarketList(@RequestParam(required = false, defaultValue = PAGE_NUM) Integer page,
                                          @RequestParam(required = false, defaultValue = PAGE_SIZE) Integer size,
                                          String marketName ,
                                          String marketType ){
        Page selectedPage = traditionMarketService.getTraditionMarketList(page , size , marketName , marketType );
        return PageData(selectedPage);
    }

    @LogRecord(name = "editTraditionMarket" ,description = "编辑理财市场信息")
    @PostMapping("/editTraditionMarket")
    public Object editTraditionMarket(@RequestBody TraditionMarket body){
        if(body.getId() == null){
            body.setMarketCode(IdWorker.getIdStr());
        }
        traditionMarketService.insertOrUpdate(body);
        return ResponseEntity.ok(MapSuccess("保存成功"));
    }

    @LogRecord(name = "getTraditionMarketInfo" ,description = "根据ID查询理财市场信息")
    @PostMapping("/getTraditionMarketInfo")
    public Object getTraditionMarketInfo(String id){
        return ResponseEntity.ok(MapSuccess("保查询成功" , traditionMarketService.selectById(id)));
    }
}
