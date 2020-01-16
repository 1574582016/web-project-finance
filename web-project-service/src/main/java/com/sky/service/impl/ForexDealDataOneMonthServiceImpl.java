package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.ForexDealDataOneMonthMapper;
import com.sky.model.ForexDealDataOneMonth;
import com.sky.service.ForexDealDataOneMonthService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2020/1/16.
 */
@Service
@Transactional
public class ForexDealDataOneMonthServiceImpl extends ServiceImpl<ForexDealDataOneMonthMapper,ForexDealDataOneMonth> implements ForexDealDataOneMonthService {
}
