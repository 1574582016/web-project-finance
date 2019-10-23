package com.sky.service;

import com.baomidou.mybatisplus.service.IService;
import com.sky.model.StockCompanyProfit;
import com.sky.vo.StockCompanyProfitVO;

import java.util.List;

/**
 * Created by ThinkPad on 2019/10/22.
 */
public interface StockCompanyProfitService extends IService<StockCompanyProfit> {

    boolean spiderStockCompanyProfit(String stockCode ,Integer page);

    List<StockCompanyProfitVO> getCompanyProfitGrowList(String stockCode);
}
