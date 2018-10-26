package com.sky.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.TraditionMarketMapper;
import com.sky.model.TraditionMarket;
import com.sky.service.TraditionMarketService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ThinkPad on 2018/10/20.
 */
@Service
@Transactional
public class TraditionMarketServiceImpl extends ServiceImpl<TraditionMarketMapper , TraditionMarket> implements TraditionMarketService{
    @Override
    public Page<TraditionMarket> getTraditionMarketList(Integer page, Integer size, String marketName, String marketType) {
        Page<TraditionMarket> pageInfo = new Page<TraditionMarket>(page, size);
        List<TraditionMarket> list = baseMapper.getTraditionMarketList( pageInfo ,marketName,marketType );
        pageInfo.setRecords(list);
        return pageInfo;
    }
}
