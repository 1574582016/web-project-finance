package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.StockDealDataThirtyMinuteMapper;
import com.sky.model.StockDealDataThirtyMinute;
import com.sky.service.StockDealDataThirtyMinuteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2020/1/4.
 */
@Service
@Transactional
public class StockDealDataThirtyMinuteServiceImpl extends ServiceImpl<StockDealDataThirtyMinuteMapper,StockDealDataThirtyMinute> implements StockDealDataThirtyMinuteService {
}
