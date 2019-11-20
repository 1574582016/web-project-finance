package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sky.model.IndexDealData;
import com.sky.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ThinkPad on 2019/9/20.
 */
public interface IndexDealDataMapper extends BaseMapper<IndexDealData> {

    List<IndexDealData> getIndexDealDataList(@Param("indexCode") String indexCode ,@Param("dealPeriod") String dealPeriod ,@Param("startDay") String startDay ,@Param("endDay") String endDay ,@Param("month") String month);

    List<IndexStatic_VO> getIndexMonthRateStaticList(@Param("indexCode") String indexCode ,@Param("dealPeriod") String dealPeriod ,@Param("startDay") String startDay ,@Param("endDay") String endDay);

    List<IndexStatic_VO> getIndexMonthValueStaticList(@Param("indexCode") String indexCode ,@Param("dealPeriod") String dealPeriod ,@Param("startDay") String startDay ,@Param("endDay") String endDay);

    List<IndexStatic_VO> getIndexWeekRateStaticList(@Param("indexCode") String indexCode ,@Param("month") String month ,@Param("startDay") String startDay ,@Param("endDay") String endDay);

    List<IndexStatic_VO> getIndexWeekValueStaticList(@Param("indexCode") String indexCode ,@Param("month") String month ,@Param("startDay") String startDay ,@Param("endDay") String endDay);

    List<IndexStatic_VO> getIndexDayRateStaticList(@Param("indexCode") String indexCode ,@Param("week") String week ,@Param("startDay") String startDay ,@Param("endDay") String endDay);

    List<IndexStatic_VO> getIndexDayValueStaticList(@Param("indexCode") String indexCode ,@Param("week") String week ,@Param("startDay") String startDay ,@Param("endDay") String endDay);

    List<IndexStatic_VO> getIndexTimeRateStaticList(@Param("indexCode") String indexCode ,@Param("dealPeriod") String dealPeriod ,@Param("startDay") String startDay ,@Param("endDay") String endDay);

    List<IndexStatic_VO> getIndexTimeValueStaticList(@Param("indexCode") String indexCode ,@Param("dealPeriod") String dealPeriod ,@Param("startDay") String startDay ,@Param("endDay") String endDay);

    List<CovarDeal_VO> getIndexDealCovarList(@Param("indexCode") String indexCode , @Param("dealPeriod") String dealPeriod , @Param("startDay") String startDay , @Param("endDay") String endDay );

    List<IndexDealData> getStockIndexDataList(@Param("indexCode") String indexCode ,@Param("dealPeriod") String dealPeriod ,@Param("startDay") String startDay ,@Param("endDay") String endDay);

    StockIndexMonthData_VO getStockIndexMonthList(@Param("indexName") String indexName,@Param("startDay") String startDay,@Param("endDay") String endDay);

    List<StockIndexWeekData_VO> getStockIndexWeekList(@Param("indexName") String indexName,@Param("startDay") String startDay,@Param("endDay") String endDay);

    List<StockIndexDayData_VO> getStockIndexDayList(@Param("indexName") String indexName,@Param("startDay") String startDay,@Param("endDay") String endDay);

    List<StockIndexMonthData_VO> getStockSectorMonthDataList(@Param("firstName") String firstName,@Param("sectorName") String sectorName,@Param("startDay") String startDay,@Param("endDay") String endDay ,@Param("sectorMonth") String sectorMonth);

    List<StockSectorCompany_VO> getStockCompanyMonthDataList(@Param("stockCode") String stockCode,@Param("sectorName") String sectorName,@Param("startDay") String startDay,@Param("endDay") String endDay ,@Param("sectorMonth") String sectorMonth);


    List<StockIndexWeekData_VO> getStockSectorWeekList(@Param("dealPeriod") String dealPeriod ,@Param("stockCode") String stockCode ,@Param("sectorName") String sectorName ,@Param("startDay") String startDay,@Param("endDay") String endDay);

    List<StockIndexMonthData_VO> getStockDifferentSectorMonthList(@Param("indexList") List<String> indexList,@Param("startDay") String startDay,@Param("endDay") String endDay);

}
