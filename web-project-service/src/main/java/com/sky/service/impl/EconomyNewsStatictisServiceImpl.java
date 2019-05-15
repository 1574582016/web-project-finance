package com.sky.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.EconomyNewsStatictisMapper;
import com.sky.model.EconomyNewsStatictis;
import com.sky.service.EconomyNewsStatictisService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ThinkPad on 2019/5/14.
 */
@Service
@Transactional
public class EconomyNewsStatictisServiceImpl extends ServiceImpl<EconomyNewsStatictisMapper,EconomyNewsStatictis> implements EconomyNewsStatictisService {

    @Override
    public Page<EconomyNewsStatictis> getEconomyNewsStatisticsList(Integer page, Integer size) {
        Page<EconomyNewsStatictis> pageInfo = new Page<EconomyNewsStatictis>(page, size);
        List<EconomyNewsStatictis> list = baseMapper.getEconomyNewsStatisticsList( pageInfo);
        pageInfo.setRecords(list);
        return pageInfo;
    }
}
