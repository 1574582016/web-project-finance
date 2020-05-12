package com.sky.service;

import com.baomidou.mybatisplus.service.IService;
import com.sky.model.StockCompanyBusinessProfit;

import java.util.List;

/**
 * Created by ThinkPad on 2020/5/12.
 */
public interface StockCompanyBusinessProfitService extends IService<StockCompanyBusinessProfit> {

    boolean spiderStockCompanyBusinessProfit(String stockCode);

    List<StockCompanyBusinessProfit> getStockCompaneyProfitList(String stockCode);
}
