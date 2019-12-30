package com.sky.service;

import com.baomidou.mybatisplus.service.IService;
import com.sky.model.StockProfitAvaregeRate;
import com.sky.vo.StockProfitAvaregeRate_VO;

import java.util.List;

/**
 * Created by ThinkPad on 2019/12/30.
 */
public interface StockProfitAvaregeRateService extends IService<StockProfitAvaregeRate> {

    List<StockProfitAvaregeRate_VO> getStockProfitAvaregeRateList(String yearType);
}
