package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.StockCompanySectorMapper;
import com.sky.model.StockCompanySector;
import com.sky.service.StockCompanySectorService;
import com.sky.vo.CompanySectorVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ThinkPad on 2019/10/14.
 */
@Service
@Transactional
public class StockCompanySectorServiceImpl extends ServiceImpl<StockCompanySectorMapper,StockCompanySector> implements StockCompanySectorService {

    @Override
    public List<CompanySectorVO> getStockCompanySectorList(String stockCode, String stockName, String firstSector, String secondSector, String thirdSecotor, String forthSector ,String hotCode) {
        return baseMapper.getStockCompanySectorList( stockCode, stockName, firstSector, secondSector, thirdSecotor, forthSector ,hotCode);
    }
}
