package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sky.model.IndexDealData;
import com.sky.vo.IndexStatic_VO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ThinkPad on 2019/9/20.
 */
public interface IndexDealDataMapper extends BaseMapper<IndexDealData> {

    List<IndexStatic_VO> getIndexMonthRateStaticList(@Param("indexCode") String indexCode ,@Param("dealPeriod") String dealPeriod ,@Param("startDay") String startDay ,@Param("endDay") String endDay);

    List<IndexStatic_VO> getIndexMonthValueStaticList(@Param("indexCode") String indexCode ,@Param("dealPeriod") String dealPeriod ,@Param("startDay") String startDay ,@Param("endDay") String endDay);

    List<IndexStatic_VO> getIndexWeekRateStaticList(@Param("indexCode") String indexCode ,@Param("month") String month ,@Param("startDay") String startDay ,@Param("endDay") String endDay);

    List<IndexStatic_VO> getIndexWeekValueStaticList(@Param("indexCode") String indexCode ,@Param("month") String month ,@Param("startDay") String startDay ,@Param("endDay") String endDay);

}