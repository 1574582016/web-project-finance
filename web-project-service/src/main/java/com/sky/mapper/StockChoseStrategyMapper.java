package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sky.model.StockChoseStrategy;
import com.sky.vo.StockStrategy_VO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ThinkPad on 2019/11/9.
 */
public interface StockChoseStrategyMapper extends BaseMapper<StockChoseStrategy> {

    List<StockStrategy_VO> getStockChoseStrategyList(Page page ,
                                                     @Param("isUpper") String isUpper ,
                                                     @Param("isTop") String isTop ,
                                                     @Param("isMiddle") String isMiddle ,
                                                     @Param("isBottom") String isBottom ,
                                                     @Param("minClosePrice") String minClosePrice ,
                                                     @Param("maxClosePrice") String maxClosePrice ,
                                                     @Param("minStandarPrice") String minStandarPrice ,
                                                     @Param("maxStandarPrice") String maxStandarPrice ,
                                                     @Param("forthSector") String forthSector  ,
                                                     @Param("startDay") String startDay ,
                                                     @Param("endDay") String endDay);
}
