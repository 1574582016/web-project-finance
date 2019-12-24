package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.StockInvestorProductMapper;
import com.sky.model.StockInvestorProduct;
import com.sky.service.StockInvestorProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2019/12/24.
 */
@Service
@Transactional
public class StockInvestorProductServiceImpl extends ServiceImpl<StockInvestorProductMapper,StockInvestorProduct> implements StockInvestorProductService {
}
