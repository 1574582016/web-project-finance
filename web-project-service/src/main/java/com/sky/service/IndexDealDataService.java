package com.sky.service;

import com.baomidou.mybatisplus.service.IService;
import com.sky.model.IndexDealData;
import com.sky.vo.IndexStatic_VO;

import java.util.List;

/**
 * Created by ThinkPad on 2019/9/20.
 */
public interface IndexDealDataService extends IService<IndexDealData> {

    List<IndexDealData> spiderIndexDealData(Integer periodType , String indexCode , String mk);

    List<IndexStatic_VO> getIndexMonthRateStaticList(String indexCode , String dealPeriod ,String startDay , String endDay);

    List<IndexStatic_VO> getIndexMonthValueStaticList(String indexCode , String dealPeriod ,String startDay , String endDay);

    List<IndexStatic_VO> getIndexWeekRateStaticList(String indexCode , String month ,String startDay , String endDay);

    List<IndexStatic_VO> getIndexWeekValueStaticList(String indexCode , String month ,String startDay , String endDay);
}
