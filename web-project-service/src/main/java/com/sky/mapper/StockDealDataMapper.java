package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sky.model.StockDealData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ThinkPad on 2019/9/16.
 */
public interface StockDealDataMapper extends BaseMapper<StockDealData> {

    List<StockDealData> getPointDayScopeList(@Param("stockCode") String stockCode , @Param("pointDay") String pointDay , @Param("days") String days);
}
