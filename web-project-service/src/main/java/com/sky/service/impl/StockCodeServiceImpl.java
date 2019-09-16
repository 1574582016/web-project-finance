package com.sky.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
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
    public void spiderStockCode(String classCode ,String sector) {
        String url = "http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?cb=jQuery112402481894141903367_1559101505431&type=CT&token=4f1862fc3b5e77c150a2b985b12db0fd&sty=FCOIATC&js=(%7Bdata%3A%5B(x)%5D%2CrecordsFiltered%3A(tot)%7D)&cmd=C."+ classCode +"&st=(ChangePercent)&sr=-1&p=1&ps=20000&_=1559101505641";
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
            StockCode isExist = selectOne(new EntityWrapper<StockCode>().where("stock_code = {0}" , stockCode.getStockCode()).where("isvalid = 1"));
            if(isExist == null){
                list.add(stockCode);
            }
        }
        if(list.size() > 0){
            insertBatch(list);
        }
    }

    @Override
    public List<StockCode> getEmptyStockProdectList(@Param("stockSector") String stockSector) {
        return baseMapper.getEmptyStockProdectList(stockSector);
    }

    @Override
    public List<StockCode> getEmptyStockAnalyseList(@Param("stockSector") String stockSector) {
        return baseMapper.getEmptyStockAnalyseList(stockSector);
    }

    @Override
    public List<StockCode> getEmptyStockCompanyList(@Param("stockSector") String stockSector) {
        return baseMapper.getEmptyStockCompanyList(stockSector);
    }
}
