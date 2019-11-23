package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.StockHandleDealDataMapper;
import com.sky.model.StockHandleDealData;
import com.sky.service.StockHandleDealDataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ThinkPad on 2019/11/23.
 */
@Service
@Transactional
public class StockHandleDealDataServiceImpl extends ServiceImpl<StockHandleDealDataMapper,StockHandleDealData> implements StockHandleDealDataService {

    @Override
    public List<StockHandleDealData> getGroupDealDataList(String stockCode, String dealPeriod) {
        return baseMapper.getGroupDealDataList(stockCode , dealPeriod);
    }

    @Override
    public int updateHandleClosePrice(String stockCode) {
        return baseMapper.updateHandleClosePrice(stockCode);
    }
}
