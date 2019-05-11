package com.sky.service;

import com.baomidou.mybatisplus.service.IService;
import com.sky.model.StockIndex;
import com.sky.vo.StockStatisticsEchart_VO;

import java.util.List;

/**
 * Created by ThinkPad on 2019/5/5.
 */
public interface StockIndexService extends IService<StockIndex> {

    public void processStockIndex(String url ,Integer indexType , Integer timeType);

    List<StockStatisticsEchart_VO> getStockStatisticsByParame();
}
