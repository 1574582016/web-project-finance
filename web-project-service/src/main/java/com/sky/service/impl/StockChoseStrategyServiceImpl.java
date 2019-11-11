package com.sky.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.StockChoseStrategyMapper;
import com.sky.model.StockChoseStrategy;
import com.sky.service.StockChoseStrategyService;
import com.sky.vo.StockStrategy_VO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ThinkPad on 2019/11/9.
 */
@Service
@Transactional
public class StockChoseStrategyServiceImpl extends ServiceImpl<StockChoseStrategyMapper,StockChoseStrategy> implements StockChoseStrategyService {

    @Override
    public Page<StockStrategy_VO> getStockChoseStrategyList(Integer page, Integer size, String isUpper , String isTop, String isMiddle, String isBottom, String minClosePrice, String maxClosePrice, String minStandarPrice, String maxStandarPrice, String forthSector , String startDay , String endDay) {
        Page<StockStrategy_VO> pageInfo = new Page<StockStrategy_VO>(page, size);
        List<StockStrategy_VO> list = baseMapper.getStockChoseStrategyList( pageInfo,isUpper , isTop, isMiddle, isBottom, minClosePrice, maxClosePrice, minStandarPrice, maxStandarPrice, forthSector , startDay , endDay);
        pageInfo.setRecords(list);
        return pageInfo;
    }
}
