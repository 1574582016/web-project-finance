package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.FinanceMarketStockMapper;
import com.sky.model.FinanceMarketStock;
import com.sky.service.FinanceMarketStockService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2020/2/18.
 */
@Service
@Transactional
public class FinanceMarketStockServiceImpl extends ServiceImpl<FinanceMarketStockMapper,FinanceMarketStock> implements FinanceMarketStockService {
}
