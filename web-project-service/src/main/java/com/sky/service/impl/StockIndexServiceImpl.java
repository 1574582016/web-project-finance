package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.core.utils.SpiderUtils;
import com.sky.mapper.StockIndexMapper;
import com.sky.model.StockIndex;
import com.sky.service.StockIndexService;
import com.sky.vo.StockStatisticsEchart_VO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThinkPad on 2019/5/5.
 */
@Service
@Transactional
public class StockIndexServiceImpl extends ServiceImpl<StockIndexMapper,StockIndex> implements StockIndexService {

    @Override
    public void processStockIndex(String url ,Integer indexType , Integer timeType) {
        String jsonString = SpiderUtils.HttpClientBuilderGet(url);
        String jStr = jsonString.substring(jsonString.indexOf("(")+1 ,jsonString.indexOf(")"));
        JSONObject jsonObject = JSON.parseObject(jStr);
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        List<StockIndex> list = new ArrayList<StockIndex>();
        for(int i = 0 ; i < jsonArray.size() ; i ++){
           String dataString = jsonArray.getString(i);
            String[] items = dataString.split(",");
            StockIndex stockIndex = new StockIndex();
            stockIndex.setIndexType(indexType);
            stockIndex.setTimeType(timeType);
            for(int j = 0 ; j < items.length ; j ++){
                switch (j){
                    case 0 :
                        stockIndex.setDataTime(items[0]);
                        break;
                    case 1 :
                        stockIndex.setStartPoint(items[1]);
                        break;
                    case 2 :
                        stockIndex.setEndPoint(items[2]);
                        break;
                    case 3 :
                        stockIndex.setHighPoint(items[3]);
                        break;
                    case 4 :
                        stockIndex.setLowPoint(items[4]);
                        break;
                    case 5 :
                        stockIndex.setDealNumber(items[5]);
                        break;
                    case 6 :
                        stockIndex.setDealMoney(items[6]);
                        break;
                    case 7 :
                        stockIndex.setAmplitude(items[7]);
                        break;
                }
            }
            list.add(stockIndex);
        }
//        System.out.println(list.toString());
        insertBatch(list);
    }

    @Override
    public List<StockStatisticsEchart_VO> getStockStatisticsByParame() {
        return baseMapper.getStockStatisticsByParame();
    }
}
