package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.ForexDealDataFifteenMinuteMapper;
import com.sky.model.ForexDealDataFifteenMinute;
import com.sky.service.ForexDealDataFifteenMinuteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2020/1/16.
 */
@Service
@Transactional
public class ForexDealDataFifteenMinuteServiceImpl extends ServiceImpl<ForexDealDataFifteenMinuteMapper,ForexDealDataFifteenMinute> implements ForexDealDataFifteenMinuteService {
}
