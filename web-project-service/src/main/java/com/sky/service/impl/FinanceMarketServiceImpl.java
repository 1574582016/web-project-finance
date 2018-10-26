package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.FinanceMarketMapper;
import com.sky.model.FinanceMarket;
import com.sky.service.FinanceMarketService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2018/10/16.
 */
@Service
@Transactional
public class FinanceMarketServiceImpl extends ServiceImpl<FinanceMarketMapper,FinanceMarket> implements FinanceMarketService {
}
