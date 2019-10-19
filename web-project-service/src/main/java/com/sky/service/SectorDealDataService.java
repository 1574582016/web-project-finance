package com.sky.service;

import com.baomidou.mybatisplus.service.IService;
import com.sky.model.SectorDealData;
import com.sky.vo.*;

import java.util.List;

/**
 * Created by ThinkPad on 2019/9/16.
 */
public interface SectorDealDataService extends IService<SectorDealData> {

    List<SectorDealData> spiderSectorDealData(Integer periodType , String sectorCode);

    List<CovarDeal_VO> getSectorDealCovarList(String sectorCode , String dealPeriod , String startDay ,String endDay );


    List<CovarStatic_VO> caculateCovarIndexAndSector(String indexCode , String dealPeriod , String startDay ,String endDay );

    List<IndexStatic_VO> getSectorMonthRateStaticList(String sectorCode , String dealPeriod , String startDay , String endDay);

    List<IndexStatic_VO> getSectorMonthValueStaticList(String sectorCode ,String dealPeriod ,String startDay ,String endDay);

    List<IndexStatic_VO> getSectorWeekRateStaticList(String sectorCode , String month ,String startDay , String endDay);

    List<IndexStatic_VO> getSectorWeekValueStaticList(String sectorCode , String month ,String startDay , String endDay);

    List<IndexStatic_VO> getSectorDayRateStaticList(String sectorCode , String week ,String startDay , String endDay);

    List<IndexStatic_VO> getSectorDayValueStaticList(String sectorCode , String week ,String startDay , String endDay);

    List<SectorOrderStatic_VO> getSectorOrderStaticList(String orderType ,String startDay , String endDay);

    List<FestivalStatic_VO> getSectorFestivalStaticList( String sectorCode , String startDay ,String endDay ,String startTime ,String endTime);

    List<HotSectorStaticVO> getHotSectorStatisticList(String sectorCode ,String orderRegain ,String startDay , String endDay ,String sectorCodes);
}
