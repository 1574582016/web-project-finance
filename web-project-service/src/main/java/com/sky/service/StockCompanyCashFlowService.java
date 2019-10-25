package com.sky.service;

import com.baomidou.mybatisplus.service.IService;
import com.sky.model.StockCompanyCashFlow;

/**
 * Created by ThinkPad on 2019/10/25.
 */
public interface StockCompanyCashFlowService extends IService<StockCompanyCashFlow> {

    boolean spiderStockCompanyCashFlow(String stockCode ,Integer page);
}
