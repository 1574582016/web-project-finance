package com.sky;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sky.core.utils.CommonHttpUtil;
import com.sky.model.SectorDealData;
import com.sky.model.StockMarketClass;
import com.sky.model.StockTigerList;
import com.sky.service.IndexDealDataService;
import com.sky.service.SectorDealDataService;
import com.sky.service.StockMarketClassService;
import com.sky.service.StockTigerListService;
import com.sky.vo.CovarDeal_VO;
import com.sky.vo.CovarStatic_VO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThinkPad on 2019/9/24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test01 {

    @Autowired
    private StockTigerListService stockTigerListService ;

    @Autowired
    private IndexDealDataService indexDealDataService ;

    @Autowired
    private SectorDealDataService sectorDealDataService ;

    @Autowired
    private StockMarketClassService stockMarketClassService ;

    @Test
    public void test(){
        String url = "http://data.eastmoney.com/DataCenter_V3/stock2016/TradeDetail/pagesize=200,page=1,sortRule=-1,sortType=,startDate=2019-09-24,endDate=2019-09-24,gpfw=0,js=var%20data_tab_1.html?rt=26154751";
        String jsStr = CommonHttpUtil.sendPost(url);
//        System.out.println(jsStr);
        jsStr = jsStr.substring(jsStr.indexOf("data_tab_1=") + 11 , jsStr.length());
//        System.out.println(jsStr);
        JSONArray jsonArray = JSON.parseObject(jsStr).getJSONArray("data");
        List<StockTigerList> list = new ArrayList<>();
        for(int i = 0 ; i < jsonArray.size() ; i ++){
           JSONObject jsonObject = jsonArray.getJSONObject(i);
            StockTigerList tigerList = new StockTigerList();
            tigerList.setStockCode(jsonObject.getString("SCode"));
            tigerList.setStockName(jsonObject.getString("SName"));
            tigerList.setPublishTime(jsonObject.getString("Tdate"));
            tigerList.setUpperRange(jsonObject.getBigDecimal("Chgradio"));
            tigerList.setHandRate(jsonObject.getBigDecimal("Dchratio"));
            tigerList.setFocusReason(jsonObject.getString("Ctypedes"));
            tigerList.setBuyMoney(jsonObject.getBigDecimal("Bmoney"));
            tigerList.setSellMoney(jsonObject.getBigDecimal("Smoney"));
            list.add(tigerList);
        }
        stockTigerListService.insertBatch(list);
    }


    @Test
    public void test1(){
        int pointNum = 4;
        List<CovarStatic_VO> staticVoList = new ArrayList<>();
        List<CovarDeal_VO> indexList = indexDealDataService.getIndexDealCovarList("1.000001" ,"1" ,null ,null);
        List<StockMarketClass> marketClassList = stockMarketClassService.selectList(new EntityWrapper<StockMarketClass>().where("class_type = '行业板块'"));
        for(StockMarketClass marketClass : marketClassList){
            String sectorCode = marketClass.getClassCode();
            List<CovarDeal_VO> sectorList = sectorDealDataService.getSectorDealCovarList(sectorCode.substring(0,sectorCode.length()-1) ,"1" ,null ,null);
            if(sectorList.size() > 0){
                BigDecimal count = BigDecimal.ZERO ;
                BigDecimal sectorUpTotal = BigDecimal.ZERO ;
                BigDecimal indexUpTotal = BigDecimal.ZERO ;
                BigDecimal mixUpTotal = BigDecimal.ZERO ;
                BigDecimal sectorQsrTotal = BigDecimal.ZERO ;
                BigDecimal indexQsrTotal = BigDecimal.ZERO ;
                for(CovarDeal_VO setorVo : sectorList){
                    for(CovarDeal_VO indexVo : indexList){
                        if(setorVo.getDealTime().equals(indexVo.getDealTime())){
                            count = count.add(BigDecimal.ONE) ;
                            sectorUpTotal = sectorUpTotal.add(setorVo.getIsUpper());
                            indexUpTotal = indexUpTotal.add(indexVo.getIsUpper());
                            mixUpTotal = mixUpTotal.add(setorVo.getIsUpper().multiply(indexVo.getIsUpper()));
                            sectorQsrTotal = sectorQsrTotal.add(setorVo.getIsUpper().pow(2));
                            indexQsrTotal = indexQsrTotal.add(indexVo.getIsUpper().pow(2));
                        }
                    }
                }
                BigDecimal EXY = mixUpTotal.divide(count , pointNum ,BigDecimal.ROUND_HALF_UP);
                BigDecimal EX_Y = indexUpTotal.multiply(sectorUpTotal).divide(count.multiply(count) , pointNum , BigDecimal.ROUND_HALF_UP);
                BigDecimal COVXY = EXY.subtract(EX_Y);
                BigDecimal DX = (sectorQsrTotal.divide(count , pointNum ,BigDecimal.ROUND_HALF_UP)).subtract((sectorUpTotal.divide(count , pointNum ,BigDecimal.ROUND_HALF_UP)).pow(2));
                BigDecimal DY = (indexQsrTotal.divide(count , pointNum ,BigDecimal.ROUND_HALF_UP)).subtract((indexUpTotal.divide(count , pointNum ,BigDecimal.ROUND_HALF_UP)).pow(2));
                BigDecimal RXY = COVXY.divide(DX.multiply(DY) ,pointNum ,BigDecimal.ROUND_HALF_UP);

                CovarStatic_VO static_vo = new CovarStatic_VO();
                static_vo.setStaticCode(marketClass.getClassCode());
                static_vo.setStaticName(marketClass.getClassName());
                static_vo.setUpperRelevant(RXY);
                staticVoList.add(static_vo);
                System.out.println(static_vo.toString());
            }
        }
        System.out.println(staticVoList.toString());
    }
}
