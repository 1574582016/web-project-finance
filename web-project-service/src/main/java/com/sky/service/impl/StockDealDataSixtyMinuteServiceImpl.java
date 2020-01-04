package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.StockDealDataSixtyMinuteMapper;
import com.sky.model.StockDealDataSixtyMinute;
import com.sky.service.StockDealDataSixtyMinuteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2020/1/4.
 */
@Service
@Transactional
public class StockDealDataSixtyMinuteServiceImpl extends ServiceImpl<StockDealDataSixtyMinuteMapper,StockDealDataSixtyMinute> implements StockDealDataSixtyMinuteService {
}
