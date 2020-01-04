package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.StockDealDataFiveMinuteMapper;
import com.sky.model.StockDealDataFiveMinute;
import com.sky.service.StockDealDataFiveMinuteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2020/1/4.
 */
@Service
@Transactional
public class StockDealDataFiveMinuteServiceImpl extends ServiceImpl<StockDealDataFiveMinuteMapper,StockDealDataFiveMinute> implements StockDealDataFiveMinuteService {
}
