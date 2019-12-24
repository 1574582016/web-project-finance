package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.StockInvestorInfoMapper;
import com.sky.model.StockInvestorInfo;
import com.sky.service.StockInvestorInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2019/12/24.
 */
@Service
@Transactional
public class StockInvestorInfoServiceImpl extends ServiceImpl<StockInvestorInfoMapper,StockInvestorInfo> implements StockInvestorInfoService {
}
