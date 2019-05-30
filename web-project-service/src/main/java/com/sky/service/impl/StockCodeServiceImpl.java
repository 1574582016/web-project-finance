package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.core.utils.SpiderUtils;
import com.sky.mapper.StockCodeMapper;
import com.sky.model.StockCode;
import com.sky.service.StockCodeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThinkPad on 2019/5/27.
 */
@Service
@Transactional
public class StockCodeServiceImpl extends ServiceImpl<StockCodeMapper,StockCode> implements StockCodeService {

    @Override
    public void spiderStockCode(String url ,String sector) {
        String jsonString = SpiderUtils.HttpClientBuilderGet(url);
        String jStr = jsonString.substring(jsonString.indexOf("%5B")+3 ,jsonString.indexOf("%5D"));
        String[] str = jStr.split("\",");
        List<StockCode> list = new ArrayList<StockCode>();
        for(String info : str){
            String[] detail = info.split(",");
            StockCode stockCode = new StockCode();
            stockCode.setStockSector(sector);
            for(int i = 0 ; i < detail.length ; i ++){
              if(i == 1){
                  String code = detail[i];
                  stockCode.setStockCode(code);
                  String stockMarket =code.substring(0,1);
                  if(stockMarket.equals("6") || stockMarket.equals("9")){
                      stockCode.setStockMarket("sh");
                  }else{
                      stockCode.setStockMarket("sz");
                  }
              }
                if(i == 2){
                    String stockName = detail[i];
                    stockCode.setStockName(stockName);
                }
            }
            list.add(stockCode);
        }
        insertBatch(list);
    }

    @Override
    public List<StockCode> getEmptyStockProdectList(@Param("stockSector") String stockSector) {
        return baseMapper.getEmptyStockProdectList(stockSector);
    }

    @Override
    public List<StockCode> getEmptyStockAnalyseList(@Param("stockSector") String stockSector) {
        return baseMapper.getEmptyStockAnalyseList(stockSector);
    }
}
