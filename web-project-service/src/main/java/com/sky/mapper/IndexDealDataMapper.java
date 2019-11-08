package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sky.model.IndexDealData;
import com.sky.vo.CovarDeal_VO;
import com.sky.vo.IndexStatic_VO;
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

}
