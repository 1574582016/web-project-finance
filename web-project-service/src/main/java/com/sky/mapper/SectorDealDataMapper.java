package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sky.model.SectorDealData;
import com.sky.model.StockDealData;
import com.sky.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ThinkPad on 2019/9/16.
 */
public interface SectorDealDataMapper extends BaseMapper<SectorDealData> {

    List<CovarDeal_VO> getSectorDealCovarList(@Param("sectorCode") String sectorCode ,@Param("dealPeriod") String dealPeriod ,@Param("startDay") String startDay ,@Param("endDay") String endDay );

    List<IndexStatic_VO> getSectorMonthRateStaticList(@Param("sectorCode") String sectorCode , @Param("dealPeriod") String dealPeriod , @Param("startDay") String startDay , @Param("endDay") String endDay);

    List<IndexStatic_VO> getSectorMonthValueStaticList(@Param("sectorCode") String sectorCode ,@Param("dealPeriod") String dealPeriod ,@Param("startDay") String startDay ,@Param("endDay") String endDay);

    List<IndexStatic_VO> getSectorWeekRateStaticList(@Param("sectorCode") String sectorCode ,@Param("month") String month ,@Param("startDay") String startDay ,@Param("endDay") String endDay);

    List<IndexStatic_VO> getSectorWeekValueStaticList(@Param("sectorCode") String sectorCode ,@Param("month") String month ,@Param("startDay") String startDay ,@Param("endDay") String endDay);

    List<IndexStatic_VO> getSectorDayRateStaticList(@Param("sectorCode") String sectorCode ,@Param("week") String week ,@Param("startDay") String startDay ,@Param("endDay") String endDay);

    List<IndexStatic_VO> getSectorDayValueStaticList(@Param("sectorCode") String sectorCode ,@Param("week") String week ,@Param("startDay") String startDay ,@Param("endDay") String endDay);

    List<SectorOrderStatic_VO> getSectorOrderStaticList(@Param("orderType") String orderType ,@Param("startDay") String startDay ,@Param("endDay") String endDay);

    List<FestivalStatic_VO> getSectorFestivalStaticList(@Param("sectorCode") String sectorCode ,@Param("startDay") String startDay ,@Param("endDay") String endDay ,@Param("startTime") String startTime ,@Param("endTime") String endTime);

    List<HotSectorStaticVO> getHotSectorStatisticList(@Param("sectorCode") String sectorCode , @Param("orderRegain") String orderRegain , @Param("startDay") String startDay , @Param("endDay") String endDay , @Param("sectorCodes") String sectorCodes);
}
