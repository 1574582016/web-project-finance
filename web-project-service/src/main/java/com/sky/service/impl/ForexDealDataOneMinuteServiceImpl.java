package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.ForexDealDataOneMinuteMapper;
import com.sky.model.ForexDealDataOneMinute;
import com.sky.service.ForexDealDataOneMinuteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2020/1/15.
 */
@Service
@Transactional
public class ForexDealDataOneMinuteServiceImpl extends ServiceImpl<ForexDealDataOneMinuteMapper,ForexDealDataOneMinute> implements ForexDealDataOneMinuteService {
}
