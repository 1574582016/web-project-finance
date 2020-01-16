package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.ForexDealDataOneWeekMapper;
import com.sky.model.ForexDealDataOneWeek;
import com.sky.service.ForexDealDataOneWeekService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2020/1/16.
 */
@Service
@Transactional
public class ForexDealDataOneWeekServiceImpl extends ServiceImpl<ForexDealDataOneWeekMapper,ForexDealDataOneWeek> implements ForexDealDataOneWeekService {
}
