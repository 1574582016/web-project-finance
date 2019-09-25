package com.sky.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.StockTigerListMapper;
import com.sky.model.StockTigerList;
import com.sky.service.StockTigerListService;
import com.sky.vo.StockTigerList_VO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by ThinkPad on 2019/9/24.
 */
@Service
@Transactional
public class StockTigerListServiceImpl extends ServiceImpl<StockTigerListMapper,StockTigerList> implements StockTigerListService {

    @Override
    public Page<StockTigerList_VO> getStockTigerList(Integer page, Integer size, String stockCode, String stockName, String startDay, String endDay) {
        Page<StockTigerList_VO> pageInfo = new Page<StockTigerList_VO>(page, size);
        List<StockTigerList_VO> list = baseMapper.getStockTigerList( pageInfo, stockCode, stockName, startDay, endDay);
        for(StockTigerList_VO tigerList_vo : list){
            tigerList_vo.setPurBuyMoney(tigerList_vo.getBuyMoney().subtract(tigerList_vo.getSellMoney()).divide(BigDecimal.valueOf(10000) , 2 ,BigDecimal.ROUND_HALF_UP));
            tigerList_vo.setBuyMoney(tigerList_vo.getBuyMoney().divide(BigDecimal.valueOf(10000) , 2 ,BigDecimal.ROUND_HALF_UP));
            tigerList_vo.setSellMoney(tigerList_vo.getSellMoney().divide(BigDecimal.valueOf(10000) , 2 ,BigDecimal.ROUND_HALF_UP));
        }
        pageInfo.setRecords(list);
        return pageInfo;
    }
}
