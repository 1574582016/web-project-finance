package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.ForexDealDataFiveMinuteMapper;
import com.sky.model.ForexDealDataFiveMinute;
import com.sky.service.ForexDealDataFiveMinuteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2020/1/16.
 */
@Service
@Transactional
public class ForexDealDataFiveMinuteServiceImpl extends ServiceImpl<ForexDealDataFiveMinuteMapper,ForexDealDataFiveMinute> implements ForexDealDataFiveMinuteService {
}
