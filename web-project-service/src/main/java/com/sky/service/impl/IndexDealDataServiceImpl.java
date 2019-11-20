package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.core.utils.CommonHttpUtil;
import com.sky.mapper.IndexDealDataMapper;
import com.sky.model.IndexDealData;
import com.sky.service.IndexDealDataService;
import com.sky.vo.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThinkPad on 2019/9/20.
 */
@Service
@Transactional
public class IndexDealDataServiceImpl extends ServiceImpl<IndexDealDataMapper,IndexDealData> implements IndexDealDataService {

    @Override
    public List<IndexDealData> spiderIndexDealData(Integer periodType, String indexCode , String mk) {
        List<IndexDealData> list = new ArrayList<>();
        List<IndexDealData> newList = new ArrayList<>();
        try {
            String url = "http://push2his.eastmoney.com/api/qt/stock/kline/get?cb=jQuery18306798388937577338_1568939381264&secid="+ indexCode +"&ut=fa5fd1943c7b386f172d6893dbfba10b&fields1=f1%2Cf2%2Cf3%2Cf4%2Cf5&fields2=f51%2Cf52%2Cf53%2Cf54%2Cf55%2Cf56%2Cf57%2Cf58&klt="+ mk +"&fqt=0&beg=19900101&end=20220101&_=1568939431099";
            String jsStr = CommonHttpUtil.sendGet(url);
            jsStr = jsStr.substring(jsStr.indexOf("(") + 1 , jsStr.indexOf(")"));
            JSONObject jsonObject = JSON.parseObject(jsStr);
            JSONObject dataObject = jsonObject.getJSONObject("data");
            String indexName = dataObject.getString("name");
            JSONArray jsonArray = dataObject.getJSONArray("klines");
            for(int i = 0 ; i < jsonArray.size() ; i ++){
                String dataStr = jsonArray.getString(i);
                String[] datas = dataStr.split(",");
                IndexDealData dealData = new IndexDealData();
                dealData.setDealPeriod(periodType);
                dealData.setIndexCode(indexCode);
                dealData.setIndexName(indexName);
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

            List<IndexDealData> dataList = selectList(new EntityWrapper<IndexDealData>().where("index_code = {0} and deal_period = {1}" ,indexCode ,periodType));

            for(IndexDealData dealData : list){
                boolean just = false;
                for(IndexDealData existData : dataList){
                    if(dealData.getDealTime().equals(existData.getDealTime())){
                        just = true ;
                        continue;
                    }
                }
                if(!just){
                    newList.add(dealData);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return newList ;
    }

    @Override
    public List<IndexDealData> getIndexDealDataList(String indexCode, String dealPeriod, String startDay, String endDay, String month) {
        return baseMapper.getIndexDealDataList( indexCode, dealPeriod, startDay, endDay, month);
    }

    @Override
    public List<IndexStatic_VO> getIndexMonthRateStaticList(String indexCode, String dealPeriod, String startDay, String endDay) {
        return baseMapper.getIndexMonthRateStaticList(indexCode ,dealPeriod ,startDay ,endDay);
    }

    @Override
    public List<IndexStatic_VO> getIndexMonthValueStaticList(String indexCode, String dealPeriod, String startDay, String endDay) {
        return baseMapper.getIndexMonthValueStaticList(indexCode ,dealPeriod ,startDay ,endDay);
    }

    @Override
    public List<IndexStatic_VO> getIndexWeekRateStaticList(String indexCode, String month, String startDay, String endDay) {
        return baseMapper.getIndexWeekRateStaticList(indexCode ,month ,startDay ,endDay);
    }

    @Override
    public List<IndexStatic_VO> getIndexWeekValueStaticList(String indexCode, String month, String startDay, String endDay) {
        return baseMapper.getIndexWeekValueStaticList(indexCode ,month ,startDay ,endDay);
    }

    @Override
    public List<IndexStatic_VO> getIndexDayRateStaticList(String indexCode, String week, String startDay, String endDay) {
        return baseMapper.getIndexDayRateStaticList(indexCode ,week ,startDay ,endDay);
    }

    @Override
    public List<IndexStatic_VO> getIndexDayValueStaticList(String indexCode, String week, String startDay, String endDay) {
        return baseMapper.getIndexDayValueStaticList(indexCode ,week ,startDay ,endDay);
    }

    @Override
    public List<IndexStatic_VO> getIndexTimeRateStaticList(String indexCode, String dealPeriod, String startDay, String endDay) {
        return baseMapper.getIndexTimeRateStaticList(indexCode ,dealPeriod ,startDay ,endDay);
    }

    @Override
    public List<IndexStatic_VO> getIndexTimeValueStaticList(String indexCode, String dealPeriod, String startDay, String endDay) {
        return baseMapper.getIndexTimeValueStaticList(indexCode ,dealPeriod ,startDay ,endDay);
    }

    @Override
    public List<CovarDeal_VO> getIndexDealCovarList(String indexCode, String dealPeriod, String startDay, String endDay) {
        return baseMapper.getIndexDealCovarList( indexCode, dealPeriod, startDay, endDay);
    }

    @Override
    public List<IndexDealData> getStockIndexDataList(String indexCode, String dealPeriod, String startDay, String endDay) {
        return baseMapper.getStockIndexDataList( indexCode, dealPeriod, startDay, endDay);
    }

    @Override
    public StockIndexMonthData_VO getStockIndexMonthList(String indexName, String startDay, String endDay) {
        return baseMapper.getStockIndexMonthList(indexName, startDay, endDay);
    }

    @Override
    public List<StockIndexWeekData_VO> getStockIndexWeekList(String indexName, String startDay, String endDay) {
        return baseMapper.getStockIndexWeekList(indexName , startDay , endDay);
    }

    @Override
    public List<StockIndexDayData_VO> getStockIndexDayList(String indexName, String startDay, String endDay) {
        return baseMapper.getStockIndexDayList(indexName , startDay , endDay);
    }

    @Override
    public List<StockIndexMonthData_VO> getStockSectorMonthDataList(String sectorName, String startDay, String endDay, String sectorMonth) {
        return baseMapper.getStockSectorMonthDataList(sectorName , startDay , endDay ,sectorMonth);
    }

    @Override
    public List<StockSectorCompany_VO> getStockCompanyMonthDataList(String stockCode, String sectorName, String startDay, String endDay, String sectorMonth) {
        return baseMapper.getStockCompanyMonthDataList(stockCode ,sectorName , startDay , endDay ,sectorMonth);
    }

    @Override
    public List<StockIndexWeekData_VO> getStockSectorWeekList(String dealPeriod , String stockCode ,String sectorName, String startDay, String endDay) {
        return baseMapper.getStockSectorWeekList( dealPeriod , stockCode ,sectorName , startDay , endDay);
    }
}
