package com.sky.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.StockCompanySectorMapper;
import com.sky.model.StockCompanySector;
import com.sky.service.StockCompanySectorService;
import com.sky.vo.CompanySectorVO;
import com.sky.vo.CreateCompanyWorld_VO;
import com.sky.vo.MyStockCompanySector_VO;
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
    public List<CompanySectorVO> getStockCompanySectorList(String stockCode, String stockName, String firstSector, String secondSector, String thirdSecotor, String forthSector, String firstHot, String secondHot, String thirdHot, String forthHot) {
        return baseMapper.getStockCompanySectorList( stockCode, stockName, firstSector, secondSector, thirdSecotor, forthSector, firstHot, secondHot, thirdHot, forthHot);
    }

    @Override
    public List<CreateCompanyWorld_VO> getCreateCompanyWorldList(String stockCode, String firstSector, String secondSector, String thirdSecotor, String forthSector) {
        return baseMapper.getCreateCompanyWorldList(stockCode, firstSector, secondSector, thirdSecotor, forthSector);
    }

    @Override
    public Page<CreateCompanyWorld_VO> getStockCompanyPoolList(Integer page, Integer size, String stockCode, String firstSector, String secondSector, String thirdSecotor, String forthSector, String companyLevel) {
        Page<CreateCompanyWorld_VO> pageInfo = new Page<CreateCompanyWorld_VO>(page, size);
        List<CreateCompanyWorld_VO> list = baseMapper.getStockCompanyPoolList( pageInfo, stockCode, firstSector, secondSector, thirdSecotor, forthSector, companyLevel);
        pageInfo.setRecords(list);
        return pageInfo;
    }

    @Override
    public List<MyStockCompanySector_VO> getMyStockCompanySectorList(String firstSector, String sectorTypes) {
        return baseMapper.getMyStockCompanySectorList(firstSector , sectorTypes);
    }
}
