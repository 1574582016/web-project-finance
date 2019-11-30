package com.sky.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.core.utils.DateUtils;
import com.sky.mapper.StockRiseRateMapper;
import com.sky.model.StockCompanySector;
import com.sky.model.StockRiseRate;
import com.sky.service.StockCompanySectorService;
import com.sky.service.StockRiseRateService;
import com.sky.vo.PointMonthStock_VO;
import com.sky.vo.StaticSectorNum_VO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ThinkPad on 2019/11/23.
 */
@Service
@Transactional
public class StockRiseRateServiceImpl extends ServiceImpl<StockRiseRateMapper,StockRiseRate> implements StockRiseRateService {

    @Autowired
    private StockCompanySectorService stockCompanySectorService ;

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
                String weekLevel = "";
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

    @Override
    public Page<PointMonthStock_VO> getPointWeekStockList(Integer page, Integer size, String staticDate, String staticMonth, String staticWeek, String staticRate, String staticAmplitude) {
        Page<PointMonthStock_VO> pageInfo = new Page<PointMonthStock_VO>(page, size);
        List<PointMonthStock_VO> list = baseMapper.getPointWeekStockList(pageInfo, staticDate, staticMonth,  staticWeek, staticRate, staticAmplitude);
        if(StringUtils.isNotBlank(staticWeek)){
            int month = Integer.parseInt(staticMonth);
            int week = Integer.parseInt(staticWeek);
            for(PointMonthStock_VO stock_vo : list){
                String weekLevel = "";
                String dayLevel = "";
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

                /*****************************************************************周级***********************************************************************/

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

                /*****************************************************************天级***********************************************************************/

                if(stock_vo.getOneDayRise().compareTo(BigDecimal.valueOf(90)) >= 0){
                    dayLevel += "S" ;
                }else if(stock_vo.getOneDayRise().compareTo(BigDecimal.valueOf(80)) >= 0 && stock_vo.getOneDayRise().compareTo(BigDecimal.valueOf(90)) < 0){
                    dayLevel += "A" ;
                }else if(stock_vo.getOneDayRise().compareTo(BigDecimal.valueOf(70)) >= 0 && stock_vo.getOneDayRise().compareTo(BigDecimal.valueOf(80)) < 0){
                    dayLevel += "B" ;
                }else if(stock_vo.getOneDayRise().compareTo(BigDecimal.valueOf(60)) >= 0 && stock_vo.getOneDayRise().compareTo(BigDecimal.valueOf(70)) < 0){
                    dayLevel += "C" ;
                }else if(stock_vo.getOneDayRise().compareTo(BigDecimal.valueOf(50)) >= 0 && stock_vo.getOneDayRise().compareTo(BigDecimal.valueOf(60)) < 0){
                    dayLevel += "D" ;
                }else{
                    dayLevel += "E" ;
                }

                if(stock_vo.getTowDayRise().compareTo(BigDecimal.valueOf(90)) >= 0){
                    dayLevel += "S" ;
                }else if(stock_vo.getTowDayRise().compareTo(BigDecimal.valueOf(80)) >= 0 && stock_vo.getTowDayRise().compareTo(BigDecimal.valueOf(90)) < 0){
                    dayLevel += "A" ;
                }else if(stock_vo.getTowDayRise().compareTo(BigDecimal.valueOf(70)) >= 0 && stock_vo.getTowDayRise().compareTo(BigDecimal.valueOf(80)) < 0){
                    dayLevel += "B" ;
                }else if(stock_vo.getTowDayRise().compareTo(BigDecimal.valueOf(60)) >= 0 && stock_vo.getTowDayRise().compareTo(BigDecimal.valueOf(70)) < 0){
                    dayLevel += "C" ;
                }else if(stock_vo.getTowDayRise().compareTo(BigDecimal.valueOf(50)) >= 0 && stock_vo.getTowDayRise().compareTo(BigDecimal.valueOf(60)) < 0){
                    dayLevel += "D" ;
                }else{
                    dayLevel += "E" ;
                }

                if(stock_vo.getThreeDayRise().compareTo(BigDecimal.valueOf(90)) >= 0){
                    dayLevel += "S" ;
                }else if(stock_vo.getThreeDayRise().compareTo(BigDecimal.valueOf(80)) >= 0 && stock_vo.getThreeDayRise().compareTo(BigDecimal.valueOf(90)) < 0){
                    dayLevel += "A" ;
                }else if(stock_vo.getThreeDayRise().compareTo(BigDecimal.valueOf(70)) >= 0 && stock_vo.getThreeDayRise().compareTo(BigDecimal.valueOf(80)) < 0){
                    dayLevel += "B" ;
                }else if(stock_vo.getThreeDayRise().compareTo(BigDecimal.valueOf(60)) >= 0 && stock_vo.getThreeDayRise().compareTo(BigDecimal.valueOf(70)) < 0){
                    dayLevel += "C" ;
                }else if(stock_vo.getThreeDayRise().compareTo(BigDecimal.valueOf(50)) >= 0 && stock_vo.getThreeDayRise().compareTo(BigDecimal.valueOf(60)) < 0){
                    dayLevel += "D" ;
                }else{
                    dayLevel += "E" ;
                }

                if(stock_vo.getFourDayRise().compareTo(BigDecimal.valueOf(90)) >= 0){
                    dayLevel += "S" ;
                }else if(stock_vo.getFourDayRise().compareTo(BigDecimal.valueOf(80)) >= 0 && stock_vo.getFourDayRise().compareTo(BigDecimal.valueOf(90)) < 0){
                    dayLevel += "A" ;
                }else if(stock_vo.getFourDayRise().compareTo(BigDecimal.valueOf(70)) >= 0 && stock_vo.getFourDayRise().compareTo(BigDecimal.valueOf(80)) < 0){
                    dayLevel += "B" ;
                }else if(stock_vo.getFourDayRise().compareTo(BigDecimal.valueOf(60)) >= 0 && stock_vo.getFourDayRise().compareTo(BigDecimal.valueOf(70)) < 0){
                    dayLevel += "C" ;
                }else if(stock_vo.getFourDayRise().compareTo(BigDecimal.valueOf(50)) >= 0 && stock_vo.getFourDayRise().compareTo(BigDecimal.valueOf(60)) < 0){
                    dayLevel += "D" ;
                }else{
                    dayLevel += "E" ;
                }

                if(stock_vo.getFiveDayRise().compareTo(BigDecimal.valueOf(90)) >= 0){
                    dayLevel += "S" ;
                }else if(stock_vo.getFiveDayRise().compareTo(BigDecimal.valueOf(80)) >= 0 && stock_vo.getFiveDayRise().compareTo(BigDecimal.valueOf(90)) < 0){
                    dayLevel += "A" ;
                }else if(stock_vo.getFiveDayRise().compareTo(BigDecimal.valueOf(70)) >= 0 && stock_vo.getFiveDayRise().compareTo(BigDecimal.valueOf(80)) < 0){
                    dayLevel += "B" ;
                }else if(stock_vo.getFiveDayRise().compareTo(BigDecimal.valueOf(60)) >= 0 && stock_vo.getFiveDayRise().compareTo(BigDecimal.valueOf(70)) < 0){
                    dayLevel += "C" ;
                }else if(stock_vo.getFiveDayRise().compareTo(BigDecimal.valueOf(50)) >= 0 && stock_vo.getFiveDayRise().compareTo(BigDecimal.valueOf(60)) < 0){
                    dayLevel += "D" ;
                }else{
                    dayLevel += "E" ;
                }
                stock_vo.setDayLevel(dayLevel);
            }
        }

        pageInfo.setRecords(list);
        return pageInfo;
    }


    @Override
    public void createMonthDealReport(Integer staticMonth , String staticDate) {
        EntityWrapper<StockRiseRate> entityWrapper = new EntityWrapper<>();
        switch (staticMonth){
            case 1 : entityWrapper.where("point_month = 1 and one_rise >= 80").orderBy("one_rise desc , one_amplitude desc"); break;
            case 2 : entityWrapper.where("point_month = 2 and tow_rise >= 80").orderBy("tow_rise desc , tow_amplitude desc"); break;
            case 3 : entityWrapper.where("point_month = 3 and three_rise >= 80").orderBy("three_rise desc , three_amplitude desc"); break;
            case 4 : entityWrapper.where("point_month = 4 and four_rise >= 80").orderBy("four_rise desc , four_amplitude desc"); break;
            case 5 : entityWrapper.where("point_month = 5 and five_rise >= 80").orderBy("five_rise desc , five_amplitude desc"); break;
            case 6 : entityWrapper.where("point_month = 6 and six_rise >= 80").orderBy("six_rise desc , six_amplitude desc"); break;
            case 7 : entityWrapper.where("point_month = 7 and seven_rise >= 80").orderBy("seven_rise desc , seven_amplitude desc"); break;
            case 8 : entityWrapper.where("point_month = 8 and eight_rise >= 80").orderBy("eight_rise desc , eight_amplitude desc"); break;
            case 9 : entityWrapper.where("point_month = 9 and nine_rise >= 80").orderBy("nine_rise desc , nine_amplitude desc"); break;
            case 10 : entityWrapper.where("point_month = 10 and ten_rise >= 80").orderBy("ten_rise desc , ten_amplitude desc"); break;
            case 11 : entityWrapper.where("point_month = 11 and eleven_rise >= 80").orderBy("eleven_rise desc , eleven_amplitude desc"); break;
            case 12 : entityWrapper.where("point_month = 12 and twelve_rise >= 80").orderBy("twelve_rise desc , twelve_amplitude desc"); break;
        }
        List<StockRiseRate> list  = baseMapper.selectList(entityWrapper);
        List<StockRiseRate> longList = new ArrayList<>();
        List<StockRiseRate> middleList = new ArrayList<>();
        List<StockRiseRate> shortList = new ArrayList<>();
        for(StockRiseRate riseRate : list){
            switch (staticMonth){
                case 12 :
                    int just = 1 ;
                    if(riseRate.getOneRise().compareTo(BigDecimal.valueOf(80)) >= 0){
                        just += 1;
                    }
                    if(riseRate.getTowRise().compareTo(BigDecimal.valueOf(80)) >= 0){
                        just += 1;
                    }
                    if(just == 3){
                        StockCompanySector companySector = stockCompanySectorService.selectOne(new EntityWrapper<StockCompanySector>().where("stock_code = {0}" , riseRate.getStockCode()));
                        if(DateUtils.date1CompareDate2(riseRate.getStartTime() , companySector.getPublishTime())){
                            longList.add(riseRate);
                        }else{
                            shortList.add(riseRate);
                        }
                    }

                    if(just == 2){
                        StockCompanySector companySector = stockCompanySectorService.selectOne(new EntityWrapper<StockCompanySector>().where("stock_code = {0}" , riseRate.getStockCode()));
                        if(DateUtils.date1CompareDate2(riseRate.getStartTime() , companySector.getPublishTime())){
                            middleList.add(riseRate);
                        }else {
                            shortList.add(riseRate);
                        }
                    }

                    if(just == 1){
                        shortList.add(riseRate);
                    }
            }
        }
        System.out.println(longList.size());
        System.out.println(middleList.size());
        System.out.println(shortList.size());
    }

    @Override
    public void createWeekDealReport(Integer staticMonth, String staticDate) {
        List<StockRiseRate> list  = baseMapper.selectList(new EntityWrapper<StockRiseRate>().where("point_month = {0} AND deal_period = 2 AND ((one_rise >= 80 AND one_amplitude >=1) OR (tow_rise >= 80 AND tow_amplitude >= 1) OR (three_rise >= 80 AND three_amplitude >= 1) OR (four_rise >= 80 AND four_amplitude >= 1) OR (five_rise >= 80 AND five_amplitude >= 1))" , staticMonth ));
        List<StockRiseRate> longList = new ArrayList<>();
        List<StockRiseRate> middleList = new ArrayList<>();
        List<StockRiseRate> shortList = new ArrayList<>();
        for(StockRiseRate riseRate : list){
            int just = 0 ;
            if(riseRate.getOneRise().compareTo(BigDecimal.valueOf(80)) >= 0){
                just += 1 ;
            }
            if(riseRate.getTowRise().compareTo(BigDecimal.valueOf(80)) >= 0){
                just += 1 ;
            }
            if(riseRate.getThreeRise().compareTo(BigDecimal.valueOf(80)) >= 0){
                just += 1 ;
            }
            if(riseRate.getFourRise().compareTo(BigDecimal.valueOf(80)) >= 0){
                just += 1 ;
            }
            if(riseRate.getFiveRise().compareTo(BigDecimal.valueOf(80)) >= 0){
                just += 1 ;
            }
            if(just >= 3){
                longList.add(riseRate);
            }
            if(just == 2){
                middleList.add(riseRate);
            }
            if(just == 1){
                shortList.add(riseRate);
            }
        }
        System.out.println(longList.size());
        System.out.println(middleList.size());
        System.out.println(shortList.size());
        System.out.println(splitWeekData(shortList).toString());
    }

    private Map<String ,List<StockRiseRate>> splitWeekData(List<StockRiseRate> list){
        List<StockRiseRate> oneList = new ArrayList<>();
        List<StockRiseRate> towList = new ArrayList<>();
        List<StockRiseRate> threeList = new ArrayList<>();
        List<StockRiseRate> fourList = new ArrayList<>();
        List<StockRiseRate> fiveList = new ArrayList<>();
        for(StockRiseRate riseRate : list){
            if(riseRate.getOneRise().compareTo(BigDecimal.valueOf(80)) >= 0){
                oneList.add(riseRate);
            }
            if(riseRate.getTowRise().compareTo(BigDecimal.valueOf(80)) >= 0){
                towList.add(riseRate);
            }
            if(riseRate.getThreeRise().compareTo(BigDecimal.valueOf(80)) >= 0){
                threeList.add(riseRate);
            }
            if(riseRate.getFourRise().compareTo(BigDecimal.valueOf(80)) >= 0){
                fourList.add(riseRate);
            }
            if(riseRate.getFiveRise().compareTo(BigDecimal.valueOf(80)) >= 0){
                fiveList.add(riseRate);
            }
        }
        Map<String ,List<StockRiseRate>> map = new HashMap<>();
        map.put("oneWeek" , oneList);
        map.put("towWeek" , towList);
        map.put("threeWeek" , threeList);
        map.put("fourWeek" , fourList);
        map.put("fiveWeek" , fiveList);
        return map;
    }
}
