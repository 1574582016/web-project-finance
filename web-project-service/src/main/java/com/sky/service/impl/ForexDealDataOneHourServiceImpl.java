package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.ForexDealDataOneHourMapper;
import com.sky.model.ForexDealDataOneHour;
import com.sky.service.ForexDealDataOneHourService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2020/1/16.
 */
@Service
@Transactional
public class ForexDealDataOneHourServiceImpl extends ServiceImpl<ForexDealDataOneHourMapper,ForexDealDataOneHour> implements ForexDealDataOneHourService {
}
