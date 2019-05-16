package com.sky.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.InvestForexReplayMapper;
import com.sky.model.InvestForexReplay;
import com.sky.service.InvestForexReplayService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ThinkPad on 2019/5/16.
 */
@Service
@Transactional
public class InvestForexReplayServiceImpl extends ServiceImpl<InvestForexReplayMapper,InvestForexReplay> implements InvestForexReplayService {

    @Override
    public Page<InvestForexReplay> getInvestForexReplayList(Integer page, Integer size, String investStrategy, String startDate, String endDate, String userId) {
        Page<InvestForexReplay> pageInfo = new Page<InvestForexReplay>(page, size);
        List<InvestForexReplay> list = baseMapper.getInvestForexReplayList( pageInfo , investStrategy, startDate, endDate, userId);
        pageInfo.setRecords(list);
        return pageInfo;
    }
}
