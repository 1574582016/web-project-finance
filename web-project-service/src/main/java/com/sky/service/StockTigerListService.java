package com.sky.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.sky.model.StockTigerList;
import com.sky.vo.StockTigerList_VO;

/**
 * Created by ThinkPad on 2019/9/24.
 */
public interface StockTigerListService extends IService<StockTigerList> {

    Page<StockTigerList_VO> getStockTigerList(Integer page , Integer size , String stockCode , String stockName , String startDay , String endDay);
}
