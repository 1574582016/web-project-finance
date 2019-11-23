package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.StockRiseRateMapper;
import com.sky.model.StockRiseRate;
import com.sky.service.StockRiseRateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2019/11/23.
 */
@Service
@Transactional
public class StockRiseRateServiceImpl extends ServiceImpl<StockRiseRateMapper,StockRiseRate> implements StockRiseRateService {
}
