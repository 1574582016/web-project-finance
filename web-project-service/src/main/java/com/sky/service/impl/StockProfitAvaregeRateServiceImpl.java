package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.StockProfitAvaregeRateMapper;
import com.sky.model.StockProfitAvaregeRate;
import com.sky.service.StockProfitAvaregeRateService;
import com.sky.vo.StockProfitAvaregeRate_VO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ThinkPad on 2019/12/30.
 */
@Service
@Transactional
public class StockProfitAvaregeRateServiceImpl extends ServiceImpl<StockProfitAvaregeRateMapper,StockProfitAvaregeRate> implements StockProfitAvaregeRateService {

    @Override
    public List<StockProfitAvaregeRate_VO> getStockProfitAvaregeRateList(String yearType) {
        return baseMapper.getStockProfitAvaregeRateList(yearType);
    }
}
