package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.core.utils.CommonHttpUtil;
import com.sky.core.utils.DateUtils;
import com.sky.mapper.FuturesDealDataMapper;
import com.sky.model.FuturesDealData;
import com.sky.service.FuturesDealDataService;
import com.sky.vo.SectorOrderStatic_VO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ThinkPad on 2019/10/21.
 */
@Service
@Transactional
public class FuturesDealDataServiceImpl extends ServiceImpl<FuturesDealDataMapper,FuturesDealData> implements FuturesDealDataService {

    @Override
    public List<FuturesDealData> spiderFuturesDealData(Integer periodType, String futuresCode) {
        List<FuturesDealData> list = new ArrayList<>();
//        int klt = 0 ;
//        switch (periodType){
//            case 1 : klt = 101 ; break;
//            case 2 : klt = 102 ; break;
//            case 3 : klt = 103 ; break;
//
//            case 4 : klt = 60 ;break;
//            case 5 : klt = 30 ;break;
//            case 6 : klt = 15 ;break;
//            case 7 : klt = 5  ;break;
//        }
        try {
            String url = "https://stock2.finance.sina.com.cn/futures/api/jsonp.php/var%20_" + futuresCode + DateUtils.format(new Date(),"yyyy_MM_dd") + "=/InnerFuturesNewService.getDailyKLine?symbol="+ futuresCode +"&_=" + DateUtils.format(new Date(),"yyyy_MM_dd");
            String jsStr = CommonHttpUtil.sendGet(url);
            jsStr = jsStr.substring(jsStr.indexOf("(") + 1 , jsStr.indexOf(")"));
//            JSONObject jsonObject = JSON.parseObject(jsStr);
            System.out.println(jsStr);
            JSONArray jsonArray = JSON.parseArray(jsStr);
            for(int i = 0 ; i < jsonArray.size() ; i ++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                FuturesDealData dealData = new FuturesDealData();
                dealData.setDealPeriod(periodType);
                dealData.setFuturesCode(futuresCode);
                dealData.setDealTime(jsonObject.getString("d"));
                dealData.setOpenPrice(jsonObject.getBigDecimal("o"));
                dealData.setClosePrice(jsonObject.getBigDecimal("c"));
                dealData.setHighPrice(jsonObject.getBigDecimal("h"));
                dealData.setLowPrice(jsonObject.getBigDecimal("l"));
                dealData.setDealCount(jsonObject.getBigDecimal("v"));
                list.add(dealData);
//                FuturesDealData sectorDealData = selectOne(new EntityWrapper<FuturesDealData>().where("sector_code = {0} and deal_period = {1} and deal_time = {2}" ,futuresCode ,periodType ,dealData.getDealTime()));
//                if(sectorDealData == null){
//                    list.add(dealData);
//                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list ;
    }

    @Override
    public List<SectorOrderStatic_VO> getFuturesOrderStaticList(String orderType, String startDay, String endDay) {
        return baseMapper.getFuturesOrderStaticList(orderType, startDay, endDay);
    }

    @Override
    public List<FuturesDealData> getFuturesDealDataList(String futureCode, String startDay, String endDay) {
        return baseMapper.getFuturesDealDataList(futureCode, startDay, endDay);
    }
}
