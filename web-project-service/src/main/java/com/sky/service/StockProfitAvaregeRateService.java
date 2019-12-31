package com.sky.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.sky.model.StockProfitAvaregeRate;
import com.sky.vo.StockProfitAvaregeRate_VO;

import java.util.List;

/**
 * Created by ThinkPad on 2019/12/30.
 */
public interface StockProfitAvaregeRateService extends IService<StockProfitAvaregeRate> {

    Page<StockProfitAvaregeRate_VO> getStockProfitAvaregeRateList(Integer page, Integer size,
                                                                  String stockCode ,
                                                                  String stockName ,
                                                                  String firstSector ,
                                                                  String secondSector ,
                                                                  String thirdSecotor ,
                                                                  String forthSector ,
                                                                  String yearType);
}
