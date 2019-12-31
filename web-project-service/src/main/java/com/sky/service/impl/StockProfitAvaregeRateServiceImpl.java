package com.sky.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
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
    public Page<StockProfitAvaregeRate_VO> getStockProfitAvaregeRateList(Integer page, Integer size,
                                                                         String stockCode ,
                                                                         String stockName ,
                                                                         String firstSector ,
                                                                         String secondSector ,
                                                                         String thirdSecotor ,
                                                                         String forthSector ,
                                                                         String yearType) {
        Page<StockProfitAvaregeRate_VO> pageInfo = new Page<StockProfitAvaregeRate_VO>(page, size);
        List<StockProfitAvaregeRate_VO> list = baseMapper.getStockProfitAvaregeRateList( pageInfo, stockCode, stockName , firstSector, secondSector, thirdSecotor, forthSector, yearType);
        pageInfo.setRecords(list);
        return pageInfo;
    }
}
