package com.sky.service;

import com.baomidou.mybatisplus.service.IService;
import com.sky.model.StockMarketClass;

/**
 * Created by ThinkPad on 2019/5/29.
 */
public interface StockMarketClassService extends IService<StockMarketClass> {

    public void spiderStockMarketClass();
}
