package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.core.utils.CommonHttpUtil;
import com.sky.core.utils.SpiderUtils;
import com.sky.mapper.SectorDealDataMapper;
import com.sky.model.SectorDealData;
import com.sky.model.StockDealData;
import com.sky.model.StockMarketClass;
import com.sky.service.IndexDealDataService;
import com.sky.service.SectorDealDataService;
import com.sky.service.StockMarketClassService;
import com.sky.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThinkPad on 2019/9/16.
 */
@Service
@Transactional
public class SectorDealDataServiceImpl extends ServiceImpl<SectorDealDataMapper,SectorDealData> implements SectorDealDataService {


    @Autowired
    private IndexDealDataService indexDealDataService ;

    @Autowired
    private SectorDealDataService sectorDealDataService ;

    @Autowired
    private StockMarketClassService stockMarketClassService ;

    @Override
    public List<SectorDealData> spiderSectorDealData(Integer periodType, String sectorCode) {
        List<SectorDealData> list = new ArrayList<>();
        int klt = 0 ;
        switch (periodType){
            case 1 : klt = 101 ; break;
            case 2 : klt = 102 ; break;
            case 3 : klt = 103 ; break;

            case 4 : klt = 60 ;break;
            case 5 : klt = 30 ;break;
            case 6 : klt = 15 ;break;
            case 7 : klt = 5  ;break;
        }
        try {
            String url = "http://push2his.eastmoney.com/api/qt/stock/kline/get?cb=jQuery172014973714601512267_1568627497999&secid=90."+ sectorCode +"&ut=fa5fd1943c7b386f172d6893dbfba10b&fields1=f1%2Cf2%2Cf3%2Cf4%2Cf5&fields2=f51%2Cf52%2Cf53%2Cf54%2Cf55%2Cf56%2Cf57%2Cf58&klt="+ klt +"&fqt=0&beg=19900101&END=20220101&_=1568627502199";
            String jsStr = CommonHttpUtil.sendGet(url);
            jsStr = jsStr.substring(jsStr.indexOf("(") + 1 , jsStr.indexOf(")"));
            JSONObject jsonObject = JSON.parseObject(jsStr);
            JSONObject dataObject = jsonObject.getJSONObject("data");
            JSONArray jsonArray = dataObject.getJSONArray("klines");
            for(int i = 0 ; i < jsonArray.size() ; i ++){
                String dataStr = jsonArray.getString(i);
                String[] datas = dataStr.split(",");
                SectorDealData dealData = new SectorDealData();
                dealData.setDealPeriod(periodType);
                dealData.setSectorCode(sectorCode);
                for(int x = 0 ; x < datas.length ; x++){
                    switch (x){
                        case 0 : dealData.setDealTime(datas[x]); break;
                        case 1 : dealData.setOpenPrice(new BigDecimal(datas[x])); break;
                        case 2 : dealData.setClosePrice(new BigDecimal (datas[x])); break;
                        case 3 : dealData.setHighPrice(new BigDecimal (datas[x])); break;
                        case 4 : dealData.setLowPrice(new BigDecimal (datas[x])); break;
                        case 5 : dealData.setDealCount(new BigDecimal (datas[x])); break;
                        case 6 : dealData.setDealMoney(new BigDecimal (datas[x])); break;
                        case 7 : dealData.setHandRate(new BigDecimal (datas[x])); break;
                    }
                }
                SectorDealData sectorDealData = selectOne(new EntityWrapper<SectorDealData>().where("sector_code = {0} and deal_period = {1} and deal_time = {2}" ,sectorCode ,periodType ,dealData.getDealTime()));
                if(sectorDealData == null){
                    list.add(dealData);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list ;
    }


    @Override
    public List<CovarDeal_VO> getSectorDealCovarList(String sectorCode, String dealPeriod, String startDay, String endDay) {
        return baseMapper.getSectorDealCovarList(sectorCode, dealPeriod, startDay, endDay);
    }


    @Override
    public List<CovarStatic_VO> caculateCovarIndexAndSector(String indexCode, String dealPeriod, String startDay, String endDay) {
        int pointNum = 4;
        List<CovarStatic_VO> staticVoList = new ArrayList<>();
        List<CovarDeal_VO> indexList = indexDealDataService.getIndexDealCovarList(indexCode ,dealPeriod ,startDay ,endDay);
        List<StockMarketClass> marketClassList = stockMarketClassService.selectList(new EntityWrapper<StockMarketClass>().where("class_type = '行业板块'"));
        for(StockMarketClass marketClass : marketClassList){
            String sectorCode = marketClass.getClassCode();
            List<CovarDeal_VO> sectorList = sectorDealDataService.getSectorDealCovarList(sectorCode.substring(0,sectorCode.length()-1) ,dealPeriod ,startDay ,endDay);
            if(sectorList.size() > 0){
                BigDecimal count = BigDecimal.ZERO ;
                BigDecimal sectorUpTotal = BigDecimal.ZERO ;
                BigDecimal indexUpTotal = BigDecimal.ZERO ;
                BigDecimal mixUpTotal = BigDecimal.ZERO ;
                BigDecimal sectorQsrTotal = BigDecimal.ZERO ;
                BigDecimal indexQsrTotal = BigDecimal.ZERO ;
                for(CovarDeal_VO setorVo : sectorList){
                    for(CovarDeal_VO indexVo : indexList){
                        if(setorVo.getDealTime().equals(indexVo.getDealTime())){
                            count = count.add(BigDecimal.ONE) ;
                            sectorUpTotal = sectorUpTotal.add(setorVo.getIsUpper());
                            indexUpTotal = indexUpTotal.add(indexVo.getIsUpper());
                            mixUpTotal = mixUpTotal.add(setorVo.getIsUpper().multiply(indexVo.getIsUpper()));
                            sectorQsrTotal = sectorQsrTotal.add(setorVo.getIsUpper().pow(2));
                            indexQsrTotal = indexQsrTotal.add(indexVo.getIsUpper().pow(2));
                        }
                    }
                }
                BigDecimal EXY = mixUpTotal.divide(count , pointNum ,BigDecimal.ROUND_HALF_UP);
                BigDecimal EX_Y = indexUpTotal.multiply(sectorUpTotal).divide(count.multiply(count) , pointNum , BigDecimal.ROUND_HALF_UP);
                BigDecimal COVXY = EXY.subtract(EX_Y);
                BigDecimal DX = (sectorQsrTotal.divide(count , pointNum ,BigDecimal.ROUND_HALF_UP)).subtract((sectorUpTotal.divide(count , pointNum ,BigDecimal.ROUND_HALF_UP)).pow(2));
                BigDecimal DY = (indexQsrTotal.divide(count , pointNum ,BigDecimal.ROUND_HALF_UP)).subtract((indexUpTotal.divide(count , pointNum ,BigDecimal.ROUND_HALF_UP)).pow(2));
                BigDecimal RXY = COVXY.divide(DX.multiply(DY) ,pointNum ,BigDecimal.ROUND_HALF_UP);

                CovarStatic_VO static_vo = new CovarStatic_VO();
                static_vo.setStaticCode(marketClass.getClassCode());
                static_vo.setStaticName(marketClass.getClassName());
                static_vo.setUpperRelevant(RXY);
                staticVoList.add(static_vo);
                System.out.println(static_vo.toString());
            }
        }
        return staticVoList;
    }

    @Override
    public List<IndexStatic_VO> getSectorMonthRateStaticList(String sectorCode, String dealPeriod, String startDay, String endDay) {
        return baseMapper.getSectorMonthRateStaticList( sectorCode, dealPeriod, startDay, endDay);
    }

    @Override
    public List<IndexStatic_VO> getSectorMonthValueStaticList(String sectorCode, String dealPeriod, String startDay, String endDay) {
        return baseMapper.getSectorMonthValueStaticList( sectorCode, dealPeriod, startDay, endDay);
    }

    @Override
    public List<IndexStatic_VO> getSectorWeekRateStaticList(String sectorCode, String month, String startDay, String endDay) {
        return baseMapper.getSectorWeekRateStaticList( sectorCode, month, startDay, endDay);
    }

    @Override
    public List<IndexStatic_VO> getSectorWeekValueStaticList(String sectorCode, String month, String startDay, String endDay) {
        return baseMapper.getSectorWeekValueStaticList( sectorCode, month, startDay, endDay);
    }

    @Override
    public List<IndexStatic_VO> getSectorDayRateStaticList(String sectorCode, String week, String startDay, String endDay) {
        return baseMapper.getSectorDayRateStaticList( sectorCode, week, startDay, endDay);
    }

    @Override
    public List<IndexStatic_VO> getSectorDayValueStaticList(String sectorCode, String week, String startDay, String endDay) {
        return baseMapper.getSectorDayValueStaticList( sectorCode, week, startDay, endDay);
    }

    @Override
    public List<SectorOrderStatic_VO> getSectorOrderStaticList(String orderType, String startDay, String endDay) {
        return baseMapper.getSectorOrderStaticList(orderType, startDay, endDay);
    }

    @Override
    public List<FestivalStatic_VO> getSectorFestivalStaticList(String sectorCode, String startDay, String endDay, String startTime, String endTime) {
        return baseMapper.getSectorFestivalStaticList( sectorCode, startDay, endDay, startTime, endTime);
    }

    @Override
    public List<HotSectorStaticVO> getHotSectorStatisticList(String sectorCode, String orderRegain, String startDay, String endDay, String sectorCodes) {
        return baseMapper.getHotSectorStatisticList( sectorCode,  orderRegain, startDay, endDay, sectorCodes);
    }
}
