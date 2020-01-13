package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.core.utils.SpiderUtils;
import com.sky.mapper.StockMoneyFlowMapper;
import com.sky.model.StockMoneyFlow;
import com.sky.service.StockMoneyFlowService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ThinkPad on 2020/1/13.
 */
@Service
@Transactional
public class StockMoneyFlowServiceImpl extends ServiceImpl<StockMoneyFlowMapper,StockMoneyFlow> implements StockMoneyFlowService {

    @Override
    public void spiderStockMoneyFlow(String stockCode) {
        String mk = "0.";
        if(stockCode.substring(0,1).equals("6")){
            mk = "1.";
        }

        System.out.println("=============================" + stockCode);

        String url = "http://push2his.eastmoney.com/api/qt/stock/fflow/daykline/get?lmt=0&klt=101&secid="+ mk + stockCode +"&fields1=f1,f2,f3,f7&fields2=f51,f52,f53,f54,f55,f56,f57,f58,f59,f60,f61,f62,f63,f64,f65&ut=b2884a393a59ad64002292a3e90d46a5&cb=jQuery18307951788775609181_1578903118106&_=1578903118636";
        String jsonString = SpiderUtils.HttpClientBuilderGet(url);
        String jStr = jsonString.substring(jsonString.indexOf("(")+1 ,jsonString.indexOf(")"));
        JSONArray jsonArray = JSON.parseObject(jStr).getJSONObject("data").getJSONArray("klines");
        List<StockMoneyFlow> existList = selectList(new EntityWrapper<StockMoneyFlow>().where("stock_code = {0}" , stockCode));
        List<StockMoneyFlow> saveList = new ArrayList<>();
        for(int i = 0 ; i < jsonArray.size() ; i ++){
            StockMoneyFlow flow = new StockMoneyFlow();
            flow.setStockCode(stockCode);
            String single = jsonArray.getString(i);
            List<String> list = Arrays.asList(single.split(","));
            for(int j = 0 ; j < list.size() ; j ++){
                switch (j){
                    case 0 :
                        flow.setDealTime(list.get(j));
                        break;
                    case 1 :
                        flow.setMagorMoney(list.get(j));
                        break;
                    case 5 :
                        flow.setSuperMoney(list.get(j));
                        break;
                    case 4 :
                        flow.setBigMoney(list.get(j));
                        break;
                    case 3 :
                        flow.setMiddleMoney(list.get(j));
                        break;
                    case 2 :
                        flow.setSmallMoney(list.get(j));
                        break;
                }
            }
            boolean just = false ;
            for(StockMoneyFlow moneyFlow : existList){
                if(moneyFlow.getDealTime().equals(flow.getDealTime())){
                    just = true ;
                }
            }
            if(!just){
                saveList.add(flow);
            }
        }
        if(saveList.size() > 0){
            insertBatch(saveList);
        }
    }
}
