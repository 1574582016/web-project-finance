package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sky.model.StockDealData;
import com.sky.model.StockRiseRate;
import com.sky.vo.FestivalStatic_VO;
import com.sky.vo.StockDealDateRank_VO;
import com.sky.vo.StockOrderStatic_VO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ThinkPad on 2019/9/16.
 */
public interface StockDealDataMapper extends BaseMapper<StockDealData> {

    List<StockDealData> getPointDayScopeList(@Param("stockCode") String stockCode , @Param("pointDay") String pointDay , @Param("days") String days);

    List<StockOrderStatic_VO> getStockOrderStaticList(@Param("sectorName") String sectorName ,@Param("orderType")  String orderType ,@Param("startDay")  String startDay ,@Param("endDay") String endDay);


    List<FestivalStatic_VO> getStockFestivalStaticList(@Param("sectorName") String sectorName ,@Param("startDay")  String startDay ,@Param("endDay")  String endDay ,@Param("startTime")  String startTime ,@Param("endTime")  String endTime);

    List<StockDealDateRank_VO> getStockDealDateByRank(@Param("stockCode") String stockCode , @Param("dealPeriod") String dealPeriod , @Param("dealTime") String dealTime , @Param("rank") String rank );


    List<StockDealData> getStockDealDataList(@Param("stockCode") String stockCode ,@Param("pointMonth") String pointMonth);

    List<StockRiseRate> getCalculateHandleDealDayList(@Param("stockCode") String stockCode ,
                                                            @Param("dealPeriod") String dealPeriod ,
                                                            @Param("startTime") String startTime ,
                                                            @Param("endTime") String endTime);
}
