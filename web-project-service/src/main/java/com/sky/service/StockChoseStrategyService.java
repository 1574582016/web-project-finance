package com.sky.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.sky.model.StockChoseStrategy;
import com.sky.vo.StockStrategy_VO;

import java.util.List;

/**
 * Created by ThinkPad on 2019/11/9.
 */
public interface StockChoseStrategyService extends IService<StockChoseStrategy> {

    Page<StockStrategy_VO> getStockChoseStrategyList(Integer page ,
                                                     Integer size ,
                                                     String isUpper ,
                                                     String isTop ,
                                                     String isMiddle ,
                                                     String isBottom ,
                                                     String minClosePrice ,
                                                     String maxClosePrice ,
                                                     String minStandarPrice ,
                                                     String maxStandarPrice ,
                                                     String forthSector  ,
                                                     String startDay ,
                                                     String endDay);
}
