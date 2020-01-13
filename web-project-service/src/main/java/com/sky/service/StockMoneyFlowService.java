package com.sky.service;

import com.baomidou.mybatisplus.service.IService;
import com.sky.model.StockMoneyFlow;

import java.util.List;

/**
 * Created by ThinkPad on 2020/1/13.
 */
public interface StockMoneyFlowService extends IService<StockMoneyFlow> {

    void spiderStockMoneyFlow(String stockCode);
}
