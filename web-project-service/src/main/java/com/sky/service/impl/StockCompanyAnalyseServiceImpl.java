package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.core.utils.SpiderUtils;
import com.sky.mapper.StockCompanyAnalyseMapper;
import com.sky.model.StockCompanyAnalyse;
import com.sky.model.StockCompanyProduct;
import com.sky.service.StockCompanyAnalyseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThinkPad on 2019/5/27.
 */
@Service
@Transactional
public class StockCompanyAnalyseServiceImpl extends ServiceImpl<StockCompanyAnalyseMapper,StockCompanyAnalyse> implements StockCompanyAnalyseService {

    @Override
    public void spiderStockCompanyAnalyse(String market, String skuCode) {
        String analyseUrl = "http://f10.eastmoney.com/CoreConception/CoreConceptionAjax?code=" + market + skuCode;

        String subjectString = SpiderUtils.HttpClientBuilderGet(analyseUrl);
        JSONObject subjectObject = JSON.parseObject(subjectString);
        JSONArray subjectArray = subjectObject.getJSONArray("hxtc");
        List<StockCompanyAnalyse> list = new ArrayList<StockCompanyAnalyse>();
        for(int i = 2 ; i < subjectArray.size() ; i ++){
            JSONObject analyseObject = subjectArray.getJSONObject(i);
            StockCompanyAnalyse stockCompanyAnalyse = new StockCompanyAnalyse();
            stockCompanyAnalyse.setStockCode(skuCode);
            stockCompanyAnalyse.setOperatePoint(analyseObject.getString("gjc"));
            stockCompanyAnalyse.setPointAnalyse(analyseObject.getString("ydnr"));
            list.add(stockCompanyAnalyse);
        }
        if(list.size()>0){
            insertBatch(list);
        }
    }
}
