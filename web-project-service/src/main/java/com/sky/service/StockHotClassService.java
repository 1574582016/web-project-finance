package com.sky.service;

import com.baomidou.mybatisplus.service.IService;
import com.sky.model.StockHotClass;

import java.util.List;

/**
 * Created by ThinkPad on 2019/10/19.
 */
public interface StockHotClassService extends IService<StockHotClass> {

    List<StockHotClass> spiderStockHotClass(String hotCode ,String hotName);
}
