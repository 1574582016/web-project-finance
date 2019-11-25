package com.sky.service;

import com.baomidou.mybatisplus.service.IService;
import com.sky.model.StockHandleDealData;
import com.sky.model.StockRiseRate;
import com.sky.vo.HandleDealMonthWeek_VO;

import java.util.List;

/**
 * Created by ThinkPad on 2019/11/23.
 */
public interface StockHandleDealDataService extends IService<StockHandleDealData> {

    List<StockHandleDealData> getGroupDealDataList(String stockCode , String dealPeriod);

    int updateHandleClosePrice(String stockCode);

    List<StockRiseRate> getCalculateHandleDealMonthWeekList(String stockCode , String dealPeriod , String startTime , String endTime);
}
