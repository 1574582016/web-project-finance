package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.core.utils.CommonHttpUtil;
import com.sky.mapper.StockHotClassMapper;
import com.sky.model.StockHotClass;
import com.sky.service.StockHotClassService;
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
public class StockHotClassServiceImpl extends ServiceImpl<StockHotClassMapper,StockHotClass> implements StockHotClassService {

    @Override
    public List<StockHotClass> spiderStockHotClass(String hotCode, String hotName) {
        List<StockHotClass> list = new ArrayList<>();
        try {
            String url = "http://80.push2.eastmoney.com/api/qt/clist/get?cb=jQuery112406129369365414847_1571470922642&pn=1&pz=1000&po=1&np=1&ut=bd1d9ddb04089700cf9c27f6f7426281&fltt=2&invt=2&fid=f3&fs=b:"+ hotCode +"&fields=f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f12,f13,f14,f15,f16,f17,f18,f20,f21,f23,f24,f25,f22,f11,f62,f128,f136,f115,f152&_=1571470922653";
            String jsStr = CommonHttpUtil.sendGet(url);
            jsStr = jsStr.substring(jsStr.indexOf("(") + 1 , jsStr.indexOf(")"));
            JSONObject jsonObject = JSON.parseObject(jsStr);
            JSONObject dataObject = jsonObject.getJSONObject("data");
            JSONArray jsonArray = dataObject.getJSONArray("diff");
            for(int i = 0 ; i < jsonArray.size() ; i ++){
                JSONObject singleObject = jsonArray.getJSONObject(i);
                StockHotClass dealData = new StockHotClass();
                dealData.setHotCode(hotCode);
                dealData.setHotName(hotName);
                dealData.setStockCode(singleObject.getString("f12"));
                dealData.setStockName(singleObject.getString("f14"));
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
