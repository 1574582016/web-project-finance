package com.sky.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.StockRiseRateMapper;
import com.sky.model.StockRiseRate;
import com.sky.service.StockRiseRateService;
import com.sky.vo.PointMonthStock_VO;
import com.sky.vo.StaticSectorNum_VO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ThinkPad on 2019/11/23.
 */
@Service
@Transactional
public class StockRiseRateServiceImpl extends ServiceImpl<StockRiseRateMapper,StockRiseRate> implements StockRiseRateService {

    @Override
    public List<StaticSectorNum_VO> getStaticSectorNum(String staticDate, String staticMonth, String staticRate, String staticAmplitude, String sectorType, String firstSector, String secondSector, String thirdSecotor) {
        return baseMapper.getStaticSectorNum( staticDate, staticMonth, staticRate, staticAmplitude, sectorType, firstSector, secondSector, thirdSecotor);
    }

    @Override
    public Page<PointMonthStock_VO> getPointMonthStockList(Integer page, Integer size, String staticDate, String staticMonth, String staticRate, String staticAmplitude, String firstSector, String secondSector, String thirdSecotor, String forthSector ) {
        Page<PointMonthStock_VO> pageInfo = new Page<PointMonthStock_VO>(page, size);
        List<PointMonthStock_VO> list = baseMapper.getPointMonthStockList(pageInfo, staticDate, staticMonth, staticRate, staticAmplitude , firstSector, secondSector, thirdSecotor, forthSector );
        if(StringUtils.isNotBlank(staticMonth)){
            int month = Integer.parseInt(staticMonth);
            for(PointMonthStock_VO stock_vo : list){
                switch (month){
                    case 1 :
                        stock_vo.setCommonRise(stock_vo.getOneRise());
                        stock_vo.setCommonAmplitude(stock_vo.getOneAmplitude());
                        stock_vo.setCommonShock(stock_vo.getOneShock());
                        break;
                    case 2 :
                        stock_vo.setCommonRise(stock_vo.getTowRise());
                        stock_vo.setCommonAmplitude(stock_vo.getTowAmplitude());
                        stock_vo.setCommonShock(stock_vo.getTowShock());
                        break;
                    case 3 :
                        stock_vo.setCommonRise(stock_vo.getThreeRise());
                        stock_vo.setCommonAmplitude(stock_vo.getThreeAmplitude());
                        stock_vo.setCommonShock(stock_vo.getThreeShock());
                        break;
                    case 4 :
                        stock_vo.setCommonRise(stock_vo.getFourRise());
                        stock_vo.setCommonAmplitude(stock_vo.getFourAmplitude());
                        stock_vo.setCommonShock(stock_vo.getFourShock());
                        break;
                    case 5 :
                        stock_vo.setCommonRise(stock_vo.getFiveRise());
                        stock_vo.setCommonAmplitude(stock_vo.getFiveAmplitude());
                        stock_vo.setCommonShock(stock_vo.getFiveShock());
                        break;
                    case 6 :
                        stock_vo.setCommonRise(stock_vo.getSixRise());
                        stock_vo.setCommonAmplitude(stock_vo.getSixAmplitude());
                        stock_vo.setCommonShock(stock_vo.getSixShock());
                        break;
                    case 7 :
                        stock_vo.setCommonRise(stock_vo.getSevenRise());
                        stock_vo.setCommonAmplitude(stock_vo.getSevenAmplitude());
                        stock_vo.setCommonShock(stock_vo.getSevenShock());
                        break;
                    case 8 :
                        stock_vo.setCommonRise(stock_vo.getEightRise());
                        stock_vo.setCommonAmplitude(stock_vo.getEightAmplitude());
                        stock_vo.setCommonShock(stock_vo.getEightShock());
                        break;
                    case 9 :
                        stock_vo.setCommonRise(stock_vo.getNineRise());
                        stock_vo.setCommonAmplitude(stock_vo.getNineAmplitude());
                        stock_vo.setCommonShock(stock_vo.getNineShock());
                        break;
                    case 10 :
                        stock_vo.setCommonRise(stock_vo.getTenRise());
                        stock_vo.setCommonAmplitude(stock_vo.getTenAmplitude());
                        stock_vo.setCommonShock(stock_vo.getTenShock());
                        break;
                    case 11 :
                        stock_vo.setCommonRise(stock_vo.getElevenRise());
                        stock_vo.setCommonAmplitude(stock_vo.getElevenAmplitude());
                        stock_vo.setCommonShock(stock_vo.getElevenShock());
                        break;
                    case 12 :
                        stock_vo.setCommonRise(stock_vo.getTwelveRise());
                        stock_vo.setCommonAmplitude(stock_vo.getTwelveAmplitude());
                        stock_vo.setCommonShock(stock_vo.getTwelveShock());
                        break;
                }
            }
        }
        pageInfo.setRecords(list);
        return pageInfo;
    }
}
