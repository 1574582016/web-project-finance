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

import java.math.BigDecimal;
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

    @Override
    public Page<PointMonthStock_VO> getPointWeekStockList(Integer page, Integer size, String staticDate, String staticMonth, String staticWeek, String staticRate, String staticAmplitude) {
        Page<PointMonthStock_VO> pageInfo = new Page<PointMonthStock_VO>(page, size);
        List<PointMonthStock_VO> list = baseMapper.getPointWeekStockList(pageInfo, staticDate, staticMonth,  staticWeek, staticRate, staticAmplitude);
        if(StringUtils.isNotBlank(staticWeek)){
            int month = Integer.parseInt(staticMonth);
            int week = Integer.parseInt(staticWeek);
            for(PointMonthStock_VO stock_vo : list){
                String weekLevel = "";
                switch (week){
                    case 1 :
                        stock_vo.setCommonRise(stock_vo.getOneWeekRise());
                        stock_vo.setCommonAmplitude(stock_vo.getOneWeekAmplitude());
                        stock_vo.setCommonShock(stock_vo.getOneWeekShock());
                        break;
                    case 2 :
                        stock_vo.setCommonRise(stock_vo.getTowWeekRise());
                        stock_vo.setCommonAmplitude(stock_vo.getTowWeekAmplitude());
                        stock_vo.setCommonShock(stock_vo.getTowWeekShock());
                        break;
                    case 3 :
                        stock_vo.setCommonRise(stock_vo.getThreeWeekRise());
                        stock_vo.setCommonAmplitude(stock_vo.getThreeWeekAmplitude());
                        stock_vo.setCommonShock(stock_vo.getThreeWeekShock());
                        break;
                    case 4 :
                        stock_vo.setCommonRise(stock_vo.getFourWeekRise());
                        stock_vo.setCommonAmplitude(stock_vo.getFourWeekAmplitude());
                        stock_vo.setCommonShock(stock_vo.getFourWeekShock());
                        break;
                    case 5 :
                        stock_vo.setCommonRise(stock_vo.getFiveWeekRise());
                        stock_vo.setCommonAmplitude(stock_vo.getFiveWeekAmplitude());
                        stock_vo.setCommonShock(stock_vo.getFiveWeekShock());
                        break;
                }

                switch (month){
                    case 1 :
                        stock_vo.setPointWeekRise(stock_vo.getOneRise());
                        stock_vo.setPointWeekAmplitude(stock_vo.getOneAmplitude());
                        stock_vo.setPointWeekShock(stock_vo.getOneShock());
                        if(stock_vo.getOneRise().compareTo(new BigDecimal(staticRate)) >= 0 && stock_vo.getOneAmplitude().compareTo(new BigDecimal(staticAmplitude)) >= 0){
                            stock_vo.setIsPool(1);
                        }else {
                            stock_vo.setIsPool(0);
                        }
                        break;
                    case 2 :
                        stock_vo.setPointWeekRise(stock_vo.getTowRise());
                        stock_vo.setPointWeekAmplitude(stock_vo.getTowAmplitude());
                        stock_vo.setPointWeekShock(stock_vo.getTowShock());
                        if(stock_vo.getTowRise().compareTo(new BigDecimal(staticRate)) >= 0 && stock_vo.getTowAmplitude().compareTo(new BigDecimal(staticAmplitude)) >= 0){
                            stock_vo.setIsPool(1);
                        }else {
                            stock_vo.setIsPool(0);
                        }
                        break;
                    case 3 :
                        stock_vo.setPointWeekRise(stock_vo.getThreeRise());
                        stock_vo.setPointWeekAmplitude(stock_vo.getThreeAmplitude());
                        stock_vo.setPointWeekShock(stock_vo.getThreeShock());
                        if(stock_vo.getThreeRise().compareTo(new BigDecimal(staticRate)) >= 0 && stock_vo.getThreeAmplitude().compareTo(new BigDecimal(staticAmplitude)) >= 0){
                            stock_vo.setIsPool(1);
                        }else {
                            stock_vo.setIsPool(0);
                        }
                        break;
                    case 4 :
                        stock_vo.setPointWeekRise(stock_vo.getFourRise());
                        stock_vo.setPointWeekAmplitude(stock_vo.getFourAmplitude());
                        stock_vo.setPointWeekShock(stock_vo.getFourShock());
                        if(stock_vo.getFourRise().compareTo(new BigDecimal(staticRate)) >= 0 && stock_vo.getFourAmplitude().compareTo(new BigDecimal(staticAmplitude)) >= 0){
                            stock_vo.setIsPool(1);
                        }else {
                            stock_vo.setIsPool(0);
                        }
                        break;
                    case 5 :
                        stock_vo.setPointWeekRise(stock_vo.getFiveRise());
                        stock_vo.setPointWeekAmplitude(stock_vo.getFiveAmplitude());
                        stock_vo.setPointWeekShock(stock_vo.getFiveShock());
                        if(stock_vo.getFiveRise().compareTo(new BigDecimal(staticRate)) >= 0 && stock_vo.getFiveAmplitude().compareTo(new BigDecimal(staticAmplitude)) >= 0){
                            stock_vo.setIsPool(1);
                        }else {
                            stock_vo.setIsPool(0);
                        }
                        break;
                    case 6 :
                        stock_vo.setPointWeekRise(stock_vo.getSixRise());
                        stock_vo.setPointWeekAmplitude(stock_vo.getSixAmplitude());
                        stock_vo.setPointWeekShock(stock_vo.getSixShock());
                        if(stock_vo.getSixRise().compareTo(new BigDecimal(staticRate)) >= 0 && stock_vo.getSixAmplitude().compareTo(new BigDecimal(staticAmplitude)) >= 0){
                            stock_vo.setIsPool(1);
                        }else {
                            stock_vo.setIsPool(1);
                        }
                        break;
                    case 7 :
                        stock_vo.setPointWeekRise(stock_vo.getSevenRise());
                        stock_vo.setPointWeekAmplitude(stock_vo.getSevenAmplitude());
                        stock_vo.setPointWeekShock(stock_vo.getSevenShock());
                        if(stock_vo.getSevenRise().compareTo(new BigDecimal(staticRate)) >= 0 && stock_vo.getSevenAmplitude().compareTo(new BigDecimal(staticAmplitude)) >= 0){
                            stock_vo.setIsPool(1);
                        }else {
                            stock_vo.setIsPool(0);
                        }
                        break;
                    case 8 :
                        stock_vo.setPointWeekRise(stock_vo.getEightRise());
                        stock_vo.setPointWeekAmplitude(stock_vo.getEightAmplitude());
                        stock_vo.setPointWeekShock(stock_vo.getEightShock());
                        if(stock_vo.getEightRise().compareTo(new BigDecimal(staticRate)) >= 0 && stock_vo.getEightAmplitude().compareTo(new BigDecimal(staticAmplitude)) >= 0){
                            stock_vo.setIsPool(1);
                        }else {
                            stock_vo.setIsPool(0);
                        }
                        break;
                    case 9 :
                        stock_vo.setPointWeekRise(stock_vo.getNineRise());
                        stock_vo.setPointWeekAmplitude(stock_vo.getNineAmplitude());
                        stock_vo.setPointWeekShock(stock_vo.getNineShock());
                        if(stock_vo.getNineRise().compareTo(new BigDecimal(staticRate)) >= 0 && stock_vo.getNineAmplitude().compareTo(new BigDecimal(staticAmplitude)) >= 0){
                            stock_vo.setIsPool(1);
                        }else {
                            stock_vo.setIsPool(0);
                        }
                        break;
                    case 10 :
                        stock_vo.setPointWeekRise(stock_vo.getTenRise());
                        stock_vo.setPointWeekAmplitude(stock_vo.getTenAmplitude());
                        stock_vo.setPointWeekShock(stock_vo.getTenShock());
                        if(stock_vo.getTenRise().compareTo(new BigDecimal(staticRate)) >= 0 && stock_vo.getTenAmplitude().compareTo(new BigDecimal(staticAmplitude)) >= 0){
                            stock_vo.setIsPool(1);
                        }else {
                            stock_vo.setIsPool(0);
                        }
                        break;
                    case 11 :
                        stock_vo.setPointWeekRise(stock_vo.getElevenRise());
                        stock_vo.setPointWeekAmplitude(stock_vo.getElevenAmplitude());
                        stock_vo.setPointWeekShock(stock_vo.getElevenShock());
                        if(stock_vo.getElevenRise().compareTo(new BigDecimal(staticRate)) >= 0 && stock_vo.getElevenAmplitude().compareTo(new BigDecimal(staticAmplitude)) >= 0){
                            stock_vo.setIsPool(1);
                        }else {
                            stock_vo.setIsPool(0);
                        }
                        break;
                    case 12 :
                        stock_vo.setPointWeekRise(stock_vo.getTwelveRise());
                        stock_vo.setPointWeekAmplitude(stock_vo.getTwelveAmplitude());
                        stock_vo.setPointWeekShock(stock_vo.getTwelveShock());
                        if(stock_vo.getTwelveRise().compareTo(new BigDecimal(staticRate)) >= 0 && stock_vo.getTwelveAmplitude().compareTo(new BigDecimal(staticAmplitude)) >= 0){
                            stock_vo.setIsPool(1);
                        }else {
                            stock_vo.setIsPool(0);
                        }
                        break;
                }

                if(stock_vo.getOneWeekRise().compareTo(BigDecimal.valueOf(90)) >= 0){
                    weekLevel += "S" ;
                }else if(stock_vo.getOneWeekRise().compareTo(BigDecimal.valueOf(80)) >= 0 && stock_vo.getOneWeekRise().compareTo(BigDecimal.valueOf(90)) < 0){
                    weekLevel += "A" ;
                }else if(stock_vo.getOneWeekRise().compareTo(BigDecimal.valueOf(70)) >= 0 && stock_vo.getOneWeekRise().compareTo(BigDecimal.valueOf(80)) < 0){
                    weekLevel += "B" ;
                }else if(stock_vo.getOneWeekRise().compareTo(BigDecimal.valueOf(60)) >= 0 && stock_vo.getOneWeekRise().compareTo(BigDecimal.valueOf(70)) < 0){
                    weekLevel += "C" ;
                }else if(stock_vo.getOneWeekRise().compareTo(BigDecimal.valueOf(50)) >= 0 && stock_vo.getOneWeekRise().compareTo(BigDecimal.valueOf(60)) < 0){
                    weekLevel += "D" ;
                }else{
                    weekLevel += "E" ;
                }

                if(stock_vo.getTowWeekRise().compareTo(BigDecimal.valueOf(90)) >= 0){
                    weekLevel += "S" ;
                }else if(stock_vo.getTowWeekRise().compareTo(BigDecimal.valueOf(80)) >= 0 && stock_vo.getTowWeekRise().compareTo(BigDecimal.valueOf(90)) < 0){
                    weekLevel += "A" ;
                }else if(stock_vo.getTowWeekRise().compareTo(BigDecimal.valueOf(70)) >= 0 && stock_vo.getTowWeekRise().compareTo(BigDecimal.valueOf(80)) < 0){
                    weekLevel += "B" ;
                }else if(stock_vo.getTowWeekRise().compareTo(BigDecimal.valueOf(60)) >= 0 && stock_vo.getTowWeekRise().compareTo(BigDecimal.valueOf(70)) < 0){
                    weekLevel += "C" ;
                }else if(stock_vo.getTowWeekRise().compareTo(BigDecimal.valueOf(50)) >= 0 && stock_vo.getTowWeekRise().compareTo(BigDecimal.valueOf(60)) < 0){
                    weekLevel += "D" ;
                }else{
                    weekLevel += "E" ;
                }

                if(stock_vo.getThreeWeekRise().compareTo(BigDecimal.valueOf(90)) >= 0){
                    weekLevel += "S" ;
                }else if(stock_vo.getThreeWeekRise().compareTo(BigDecimal.valueOf(80)) >= 0 && stock_vo.getThreeWeekRise().compareTo(BigDecimal.valueOf(90)) < 0){
                    weekLevel += "A" ;
                }else if(stock_vo.getThreeWeekRise().compareTo(BigDecimal.valueOf(70)) >= 0 && stock_vo.getThreeWeekRise().compareTo(BigDecimal.valueOf(80)) < 0){
                    weekLevel += "B" ;
                }else if(stock_vo.getThreeWeekRise().compareTo(BigDecimal.valueOf(60)) >= 0 && stock_vo.getThreeWeekRise().compareTo(BigDecimal.valueOf(70)) < 0){
                    weekLevel += "C" ;
                }else if(stock_vo.getThreeWeekRise().compareTo(BigDecimal.valueOf(50)) >= 0 && stock_vo.getThreeWeekRise().compareTo(BigDecimal.valueOf(60)) < 0){
                    weekLevel += "D" ;
                }else{
                    weekLevel += "E" ;
                }

                if(stock_vo.getFourWeekRise().compareTo(BigDecimal.valueOf(90)) >= 0){
                    weekLevel += "S" ;
                }else if(stock_vo.getFourWeekRise().compareTo(BigDecimal.valueOf(80)) >= 0 && stock_vo.getFourWeekRise().compareTo(BigDecimal.valueOf(90)) < 0){
                    weekLevel += "A" ;
                }else if(stock_vo.getFourWeekRise().compareTo(BigDecimal.valueOf(70)) >= 0 && stock_vo.getFourWeekRise().compareTo(BigDecimal.valueOf(80)) < 0){
                    weekLevel += "B" ;
                }else if(stock_vo.getFourWeekRise().compareTo(BigDecimal.valueOf(60)) >= 0 && stock_vo.getFourWeekRise().compareTo(BigDecimal.valueOf(70)) < 0){
                    weekLevel += "C" ;
                }else if(stock_vo.getFourWeekRise().compareTo(BigDecimal.valueOf(50)) >= 0 && stock_vo.getFourWeekRise().compareTo(BigDecimal.valueOf(60)) < 0){
                    weekLevel += "D" ;
                }else{
                    weekLevel += "E" ;
                }

                if(stock_vo.getFiveWeekRise().compareTo(BigDecimal.valueOf(90)) >= 0){
                    weekLevel += "S" ;
                }else if(stock_vo.getFiveWeekRise().compareTo(BigDecimal.valueOf(80)) >= 0 && stock_vo.getFiveWeekRise().compareTo(BigDecimal.valueOf(90)) < 0){
                    weekLevel += "A" ;
                }else if(stock_vo.getFiveWeekRise().compareTo(BigDecimal.valueOf(70)) >= 0 && stock_vo.getFiveWeekRise().compareTo(BigDecimal.valueOf(80)) < 0){
                    weekLevel += "B" ;
                }else if(stock_vo.getFiveWeekRise().compareTo(BigDecimal.valueOf(60)) >= 0 && stock_vo.getFiveWeekRise().compareTo(BigDecimal.valueOf(70)) < 0){
                    weekLevel += "C" ;
                }else if(stock_vo.getFiveWeekRise().compareTo(BigDecimal.valueOf(50)) >= 0 && stock_vo.getFiveWeekRise().compareTo(BigDecimal.valueOf(60)) < 0){
                    weekLevel += "D" ;
                }else{
                    weekLevel += "E" ;
                }
                stock_vo.setWeekLevel(weekLevel);
            }
        }

        pageInfo.setRecords(list);
        return pageInfo;
    }
}
