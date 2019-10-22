package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.StockCompanyProfitMapper;
import com.sky.model.StockCompanyProfit;
import com.sky.service.StockCompanyProfitService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2019/10/22.
 */
@Service
@Transactional
public class StockCompanyProfitServiceImpl extends ServiceImpl<StockCompanyProfitMapper,StockCompanyProfit> implements StockCompanyProfitService {
}
