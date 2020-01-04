package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.StockDealDataFifteenMinuteMapper;
import com.sky.model.StockDealDataFifteenMinute;
import com.sky.service.StockDealDataFifteenMinuteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2020/1/4.
 */
@Service
@Transactional
public class StockDealDataFifteenMinuteServiceImpl extends ServiceImpl<StockDealDataFifteenMinuteMapper,StockDealDataFifteenMinute> implements StockDealDataFifteenMinuteService {
}
