package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.core.utils.CommonHttpUtil;
import com.sky.core.utils.SpiderUtils;
import com.sky.mapper.SectorDealDataMapper;
import com.sky.model.SectorDealData;
import com.sky.model.StockDealData;
import com.sky.service.SectorDealDataService;
import org.apache.commons.lang3.StringUtils;
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

    @Override
    public List<SectorDealData> spiderSectorDealData(Integer periodType, String sectorCode) {
        List<SectorDealData> list = new ArrayList<>();
        try {
            String url = "http://push2his.eastmoney.com/api/qt/stock/kline/get?cb=jQuery172014973714601512267_1568627497999&secid=90."+ sectorCode +"&ut=fa5fd1943c7b386f172d6893dbfba10b&fields1=f1%2Cf2%2Cf3%2Cf4%2Cf5&fields2=f51%2Cf52%2Cf53%2Cf54%2Cf55%2Cf56%2Cf57%2Cf58&klt=101&fqt=0&beg=19900101&END=20220101&_=1568627502199";
            String jsStr = CommonHttpUtil.sendGet(url);
            jsStr = jsStr.substring(jsStr.indexOf("(") + 1 , jsStr.indexOf(")"));
            JSONObject jsonObject = JSON.parseObject(jsStr);
            JSONObject dataObject = jsonObject.getJSONObject("data");
            JSONArray jsonArray = dataObject.getJSONArray("klines");
            for(int i = 0 ; i < jsonArray.size() ; i ++){
                String dataStr = jsonArray.getString(i);
                String[] datas = dataStr.split(",");
                SectorDealData dealData = new SectorDealData();
                dealData.setDealPeriod(1);
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
                list.add(dealData);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list ;
    }
}
