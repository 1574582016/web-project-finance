package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.core.utils.CommonHttpUtil;
import com.sky.core.utils.StringUtils;
import com.sky.mapper.StockDealDataVolMapper;
import com.sky.model.StockDealData;
import com.sky.model.StockDealDataVol;
import com.sky.service.StockDealDataVolService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThinkPad on 2020/3/24.
 */
@Service
@Transactional
public class StockDealDataVolServiceImpl extends ServiceImpl<StockDealDataVolMapper,StockDealDataVol> implements StockDealDataVolService {

    @Override
    public List<StockDealDataVol> spiderStockDealDataVol(String stockCode ,String dealDay){
        String mk = "1";
        String mkj = "1";
        if(!stockCode.substring(0,1).equals("6")){
            mk = "2";
            mkj = "0";
        }
        List<StockDealDataVol> list = new ArrayList<>();
        for(int page = 0 ; page < 100 ; page ++){
            String url = "http://push2ex.eastmoney.com/getStockFenShi?pagesize=144&ut=7eea3edcaed734bea9cbfc24409ed989&dpt=wzfscj&cb=jQuery1123004134199993740406_1589900699195&pageindex="+ page +"&id="+ stockCode + mk +"&sort=1&ft=1&code="+ stockCode +"&market="+ mkj +"&_=" + radomNum();
            String jsStr = CommonHttpUtil.sendGet(url);
            jsStr = jsStr.substring(jsStr.indexOf("(") + 1 , jsStr.indexOf(")"));
            JSONObject jsonObject = JSON.parseObject(jsStr);
            String dataString = jsonObject.getString("data");
            System.out.println("================" + dataString);
            if(dataString == null || dataString.equals("null")){
                break;
            }
            JSONObject dataObject = jsonObject.getJSONObject("data");
            JSONArray jsonArray = dataObject.getJSONArray("data");
            if(jsonArray.size() == 0){
                break;
            }

            for(int i = 0 ; i < jsonArray.size() ; i ++){
                JSONObject volJSON = jsonArray.getJSONObject(i);
                BigDecimal price = volJSON.getBigDecimal("p").divide(BigDecimal.valueOf(1000) , 2 ,BigDecimal.ROUND_HALF_UP);
                int type = volJSON.getInteger("bs");
                String time = volJSON.getString("t");
                BigDecimal vol = volJSON.getBigDecimal("v");

                String miao = time.substring(time.length() - 2 , time.length());
                String fen = time.substring(time.length() - 4 , time.length() - 2);
                String shi = time.substring(0 , time.length() - 4);
                if(shi.equals("9")){
                    time = "09:" + fen + ":" + miao ;
                }else{
                    time = shi + ":" + fen + ":" + miao ;
                }

                StockDealDataVol dataVol = new StockDealDataVol();
                dataVol.setStockCode(stockCode);
                dataVol.setDealTime(dealDay + " " + time);
                dataVol.setDealType(type);
                dataVol.setDealPrice(price);
                dataVol.setDealCount(vol);
                list.add(dataVol);
            }
        }

        List<StockDealDataVol> existList = selectList(new EntityWrapper<StockDealDataVol>().where("stock_code = {0} and deal_time regexp {1}" , stockCode , dealDay));
        if(existList.size() == 0){
            return list ;
        }

        List<StockDealDataVol> newList = new ArrayList<>();
        for(StockDealDataVol dataVol : list){
            boolean just = false;
            for(StockDealDataVol dealDataVol : existList){
                if( dataVol.getStockCode().equals(dealDataVol.getStockCode()) &&
                    dataVol.getDealTime().equals(dealDataVol.getDealTime()) &&
                    dataVol.getDealType().intValue() == dealDataVol.getDealType().intValue() &&
                    dataVol.getDealPrice().compareTo(dealDataVol.getDealPrice()) == 0 &&
                    dataVol.getDealCount().compareTo(dealDataVol.getDealCount()) == 0
                ){
                    just = true;
                    continue;
                }
            }
            if(!just){
                newList.add(dataVol);
            }
        }

        return newList;
    }

    private String radomNum(){
        long max=100000000000000l,min=1;
        long randomNum = System.currentTimeMillis();
        long ran3 = (long) (randomNum%(max-min)+min);
        return ran3+"";
    }
}
