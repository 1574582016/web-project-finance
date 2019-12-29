package com.sky.service;

import com.baomidou.mybatisplus.service.IService;
import com.sky.model.StockProfitIncreaseRate;

/**
 * Created by Administrator on 2019/12/28/028.
 */
public interface StockProfitIncreaseRateService extends IService<StockProfitIncreaseRate> {

    public void caculateStockProfitIncreaseRate(String stockCode);
}
