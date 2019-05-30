package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sky.model.StockCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ThinkPad on 2019/5/27.
 */
public interface StockCodeMapper extends BaseMapper<StockCode> {

    List<StockCode> getEmptyStockProdectList(@Param("stockSector") String stockSector);

    List<StockCode> getEmptyStockAnalyseList(@Param("stockSector") String stockSector);
}
