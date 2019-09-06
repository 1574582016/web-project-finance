package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.StockCompanyHotPointMapper;
import com.sky.model.StockCompanyHotPoint;
import com.sky.service.StockCompanyHotPointService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2019/9/2.
 */
@Service
@Transactional
public class StockCompanyHotPointServiceImpl extends ServiceImpl<StockCompanyHotPointMapper,StockCompanyHotPoint> implements StockCompanyHotPointService {
}
