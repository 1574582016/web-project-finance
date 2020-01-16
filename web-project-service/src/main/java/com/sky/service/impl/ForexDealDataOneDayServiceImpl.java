package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.ForexDealDataOneDayMapper;
import com.sky.model.ForexDealDataOneDay;
import com.sky.service.ForexDealDataOneDayService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ThinkPad on 2020/1/16.
 */
@Service
@Transactional
public class ForexDealDataOneDayServiceImpl extends ServiceImpl<ForexDealDataOneDayMapper,ForexDealDataOneDay> implements ForexDealDataOneDayService {
}
