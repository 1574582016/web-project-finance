package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.ForexDealDataMapper;
import com.sky.model.ForexDealData;
import com.sky.service.ForexDealDataService;
import com.sky.vo.IndexStatic_VO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ThinkPad on 2019/9/21.
 */
@Service
@Transactional
public class ForexDealDataServiceImpl extends ServiceImpl<ForexDealDataMapper,ForexDealData> implements ForexDealDataService {

    @Override
    public List<IndexStatic_VO> getForexMonthRateStaticList(String indexCode, String dealPeriod, String startDay, String endDay) {
        return baseMapper.getForexMonthRateStaticList( indexCode, dealPeriod, startDay, endDay);
    }

    @Override
    public List<IndexStatic_VO> getForexMonthValueStaticList(String indexCode, String dealPeriod, String startDay, String endDay) {
        return baseMapper.getForexMonthValueStaticList( indexCode, dealPeriod, startDay, endDay);
    }

    @Override
    public List<IndexStatic_VO> getForexWeekRateStaticList(String indexCode, String month, String startDay, String endDay) {
        return baseMapper.getForexWeekRateStaticList( indexCode, month, startDay, endDay);
    }

    @Override
    public List<IndexStatic_VO> getForexWeekValueStaticList(String indexCode, String month, String startDay, String endDay) {
        return baseMapper.getForexWeekValueStaticList( indexCode, month, startDay, endDay);
    }
}
