package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sky.model.ForexDealData;
import com.sky.vo.IndexStatic_VO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ThinkPad on 2019/9/21.
 */
public interface ForexDealDataMapper extends BaseMapper<ForexDealData> {

    List<IndexStatic_VO> getForexMonthRateStaticList(@Param("indexCode") String indexCode , @Param("dealPeriod") String dealPeriod , @Param("startDay") String startDay , @Param("endDay") String endDay);

    List<IndexStatic_VO> getForexMonthValueStaticList(@Param("indexCode") String indexCode ,@Param("dealPeriod") String dealPeriod ,@Param("startDay") String startDay ,@Param("endDay") String endDay);

    List<IndexStatic_VO> getForexWeekRateStaticList(@Param("indexCode") String indexCode ,@Param("month") String month ,@Param("startDay") String startDay ,@Param("endDay") String endDay);

    List<IndexStatic_VO> getForexWeekValueStaticList(@Param("indexCode") String indexCode ,@Param("month") String month ,@Param("startDay") String startDay ,@Param("endDay") String endDay);

}
