package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.core.utils.CommonHttpUtil;
import com.sky.mapper.HotDealDataMapper;
import com.sky.model.HotDealData;
import com.sky.service.HotDealDataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThinkPad on 2019/10/19.
 */
@Service
@Transactional
public class HotDealDataServiceImpl extends ServiceImpl<HotDealDataMapper,HotDealData> implements HotDealDataService {

    @Override
    public List<HotDealData> spiderHotDealData(Integer periodType, String sectorCode) {
        List<HotDealData> list = new ArrayList<>();
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
            String url = "http://push2his.eastmoney.com/api/qt/stock/kline/get?cb=jQuery172017860905934673_1571461831130&secid=90."+ sectorCode +"&ut=fa5fd1943c7b386f172d6893dbfba10b&fields1=f1%2Cf2%2Cf3%2Cf4%2Cf5&fields2=f51%2Cf52%2Cf53%2Cf54%2Cf55%2Cf56%2Cf57%2Cf58&klt="+ klt +"&fqt=0&beg=19900101&end=20220101&_=1571461898076";
            String jsStr = CommonHttpUtil.sendGet(url);
            jsStr = jsStr.substring(jsStr.indexOf("(") + 1 , jsStr.indexOf(")"));
            JSONObject jsonObject = JSON.parseObject(jsStr);
            JSONObject dataObject = jsonObject.getJSONObject("data");
            JSONArray jsonArray = dataObject.getJSONArray("klines");
            for(int i = 0 ; i < jsonArray.size() ; i ++){
                String dataStr = jsonArray.getString(i);
                String[] datas = dataStr.split(",");
                HotDealData dealData = new HotDealData();
                dealData.setDealPeriod(periodType);
                dealData.setHotCode(sectorCode);
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
//                HotDealData sectorDealData = selectOne(new EntityWrapper<HotDealData>().where("sector_code = {0} and deal_period = {1} and deal_time = {2}" ,sectorCode ,periodType ,dealData.getDealTime()));
//                if(sectorDealData == null){
                    list.add(dealData);
//                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list ;
    }
}
