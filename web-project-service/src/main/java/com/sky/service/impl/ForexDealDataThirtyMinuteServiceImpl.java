package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.ForexDealDataThirtyMinuteMapper;
import com.sky.model.ForexDealDataThirtyMinute;
import com.sky.service.ForexDealDataThirtyMinuteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2020/1/16.
 */
@Service
@Transactional
public class ForexDealDataThirtyMinuteServiceImpl extends ServiceImpl<ForexDealDataThirtyMinuteMapper,ForexDealDataThirtyMinute> implements ForexDealDataThirtyMinuteService {
}
