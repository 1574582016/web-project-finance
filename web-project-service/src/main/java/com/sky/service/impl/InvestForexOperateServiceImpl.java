package com.sky.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.InvestForexOperateMapper;
import com.sky.model.InvestForexOperate;
import com.sky.service.InvestForexOperateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ThinkPad on 2019/5/20.
 */
@Service
@Transactional
public class InvestForexOperateServiceImpl extends ServiceImpl<InvestForexOperateMapper,InvestForexOperate> implements InvestForexOperateService {

    @Override
    public Page<InvestForexOperate> getInvestForexOperateList(Integer page, Integer size, String investStrategy, String startDate, String endDate, String userId) {
        Page<InvestForexOperate> pageInfo = new Page<InvestForexOperate>(page, size);
        List<InvestForexOperate> list = baseMapper.getInvestForexOperateList( pageInfo , investStrategy, startDate, endDate, userId);
        pageInfo.setRecords(list);
        return pageInfo;
    }
}
