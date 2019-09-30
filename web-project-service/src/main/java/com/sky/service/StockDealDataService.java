package com.sky.service;

import com.baomidou.mybatisplus.service.IService;
import com.sky.model.StockDealData;
import com.sky.vo.FestivalStatic_VO;
import com.sky.vo.StockOrderStatic_VO;

import java.util.List;

/**
 * Created by ThinkPad on 2019/9/16.
 */
public interface StockDealDataService extends IService<StockDealData> {

    List<StockDealData> spiderStockDealData(Integer periodType , String skuCode ,String mk);

    void batchList(List<StockDealData> dataList);

    List<StockDealData> getPointDayScopeList( String stockCode , String pointDay , String days);

    List<StockOrderStatic_VO> getStockOrderStaticList(String sectorName ,String orderType ,String startDay , String endDay);

    List<FestivalStatic_VO> getStockFestivalStaticList(String sectorName , String startDay , String endDay , String startTime , String endTime);
}
