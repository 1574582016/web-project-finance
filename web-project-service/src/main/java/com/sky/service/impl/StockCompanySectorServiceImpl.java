package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.StockCompanySectorMapper;
import com.sky.model.StockCompanySector;
import com.sky.service.StockCompanySectorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2019/10/14.
 */
@Service
@Transactional
public class StockCompanySectorServiceImpl extends ServiceImpl<StockCompanySectorMapper,StockCompanySector> implements StockCompanySectorService {
}
