package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sky.model.StockHandleDealData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ThinkPad on 2019/11/23.
 */
public interface StockHandleDealDataMapper extends BaseMapper<StockHandleDealData> {

    List<StockHandleDealData> getGroupDealDataList(@Param("stockCode") String stockCode ,@Param("dealPeriod") String dealPeriod);

    int updateHandleClosePrice(@Param("stockCode") String stockCode);
}
