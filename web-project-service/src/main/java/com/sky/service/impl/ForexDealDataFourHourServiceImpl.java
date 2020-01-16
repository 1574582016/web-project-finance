package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.ForexDealDataFourHourMapper;
import com.sky.model.ForexDealDataFourHour;
import com.sky.service.ForexDealDataFourHourService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2020/1/16.
 */
@Service
@Transactional
public class ForexDealDataFourHourServiceImpl extends ServiceImpl<ForexDealDataFourHourMapper,ForexDealDataFourHour> implements ForexDealDataFourHourService {
}
