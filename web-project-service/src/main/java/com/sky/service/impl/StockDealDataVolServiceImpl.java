package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.StockDealDataVolMapper;
import com.sky.model.StockDealDataVol;
import com.sky.service.StockDealDataVolService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2020/3/24.
 */
@Service
@Transactional
public class StockDealDataVolServiceImpl extends ServiceImpl<StockDealDataVolMapper,StockDealDataVol> implements StockDealDataVolService {
}
