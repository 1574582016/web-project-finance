package com.sky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.sky.model.TraditionMarket;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ThinkPad on 2018/10/20.
 */
public interface TraditionMarketMapper extends BaseMapper<TraditionMarket> {

    List<TraditionMarket> getTraditionMarketList(Pagination pagination , @Param("marketName") String marketName , @Param("marketType")  String marketType );
}
