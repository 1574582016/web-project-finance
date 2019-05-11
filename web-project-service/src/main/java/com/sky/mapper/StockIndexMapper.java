package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sky.model.StockIndex;
import com.sky.vo.StockStatisticsEchart_VO;

import java.util.List;

/**
 * Created by ThinkPad on 2019/5/5.
 */
public interface StockIndexMapper extends BaseMapper<StockIndex> {

    List<StockStatisticsEchart_VO> getStockStatisticsByParame();
}
