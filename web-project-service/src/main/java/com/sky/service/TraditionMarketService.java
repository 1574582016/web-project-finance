package com.sky.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.sky.model.TraditionMarket;

/**
 * Created by ThinkPad on 2018/10/20.
 */
public interface TraditionMarketService extends IService<TraditionMarket> {

    Page<TraditionMarket> getTraditionMarketList(Integer page ,Integer size , String marketName , String marketType );
}
