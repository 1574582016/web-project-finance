package com.sky.service;

import com.baomidou.mybatisplus.service.IService;
import com.sky.model.IndexDealData;
import com.sky.vo.*;

import java.util.List;

/**
 * Created by ThinkPad on 2019/9/20.
 */
public interface IndexDealDataService extends IService<IndexDealData> {

    List<IndexDealData> spiderIndexDealData(Integer periodType , String indexCode , String mk);

    List<IndexDealData> getIndexDealDataList(String indexCode , String dealPeriod , String startDay , String endDay , String month);

    List<IndexStatic_VO> getIndexMonthRateStaticList(String indexCode , String dealPeriod ,String startDay , String endDay);

    List<IndexStatic_VO> getIndexMonthValueStaticList(String indexCode , String dealPeriod ,String startDay , String endDay);

    List<IndexStatic_VO> getIndexWeekRateStaticList(String indexCode , String month ,String startDay , String endDay);

    List<IndexStatic_VO> getIndexWeekValueStaticList(String indexCode , String month ,String startDay , String endDay);

    List<IndexStatic_VO> getIndexDayRateStaticList(String indexCode , String week ,String startDay , String endDay);

    List<IndexStatic_VO> getIndexDayValueStaticList(String indexCode , String week ,String startDay , String endDay);

    List<IndexStatic_VO> getIndexTimeRateStaticList(String indexCode , String dealPeriod ,String startDay , String endDay);


    List<IndexStatic_VO> getIndexTimeValueStaticList(String indexCode , String dealPeriod ,String startDay , String endDay);

    List<CovarDeal_VO> getIndexDealCovarList( String indexCode , String dealPeriod , String startDay ,String endDay );

    List<IndexDealData> getStockIndexDataList(String indexCode ,String dealPeriod ,String startDay ,String endDay);

    StockIndexMonthData_VO getStockIndexMonthList(String indexName ,String startDay ,String endDay);

    List<StockIndexWeekData_VO> getStockIndexWeekList(String indexName ,String startDay ,String endDay);

    List<StockIndexDayData_VO> getStockIndexDayList(String indexName ,String startDay ,String endDay);

    List<StockIndexMonthData_VO> getStockSectorMonthDataList(String sectorName,String startDay,String endDay ,String sectorMonth);


    List<StockSectorCompany_VO> getStockCompanyMonthDataList(String stockCode,String sectorName,String startDay,String endDay ,String sectorMonth);

    List<StockIndexWeekData_VO> getStockSectorWeekList( String dealPeriod , String stockCode ,String sectorName ,String startDay ,String endDay);

}
