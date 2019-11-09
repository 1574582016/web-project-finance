package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.StockChoseStrategyMapper;
import com.sky.model.StockChoseStrategy;
import com.sky.service.StockChoseStrategyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2019/11/9.
 */
@Service
@Transactional
public class StockChoseStrategyServiceImpl extends ServiceImpl<StockChoseStrategyMapper,StockChoseStrategy> implements StockChoseStrategyService {
}
