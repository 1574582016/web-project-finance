package com.sky.service;

import com.baomidou.mybatisplus.service.IService;
import com.sky.model.ForexDealData;
import com.sky.model.ForexDealDataOneMinute;
import com.sky.vo.IndexStatic_VO;

import java.util.List;

/**
 * Created by ThinkPad on 2019/9/21.
 */
public interface ForexDealDataService extends IService<ForexDealData> {

    List<IndexStatic_VO> getForexMonthRateStaticList(String indexCode , String dealPeriod , String startDay , String endDay);

    List<IndexStatic_VO> getForexMonthValueStaticList(String indexCode , String dealPeriod ,String startDay , String endDay);

    List<IndexStatic_VO> getForexWeekRateStaticList(String indexCode , String month ,String startDay , String endDay);

    List<IndexStatic_VO> getForexWeekValueStaticList(String indexCode , String month ,String startDay , String endDay);


    List<ForexDealDataOneMinute> analysisForexDataFile(String fileName);
}
