package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.core.utils.MathUtil;
import com.sky.core.utils.SpiderUtils;
import com.sky.mapper.StockDealDataMapper;
import com.sky.model.SectorDealData;
import com.sky.model.StockDealData;
import com.sky.service.StockDealDataService;
import com.sky.vo.FestivalStatic_VO;
import com.sky.vo.StockDealDateRank_VO;
import com.sky.vo.StockOrderStatic_VO;
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
public class StockDealDataServiceImpl extends ServiceImpl<StockDealDataMapper,StockDealData> implements StockDealDataService {

    private static String type2 = "2" ;

    @Override
    public List<StockDealData> spiderStockDealData(Integer periodType , String skuCode  ,String mk) {
        List<StockDealData> list = new ArrayList<>();
        List<StockDealData> newList = new ArrayList<>();
        try {
            String type = "k";
            switch (periodType){
                case 1 : type = "k" ; break;
                case 2 : type = "wk" ; break;
                case 3 : type = "mk" ; break;

                case 4 : type = "m5k" ; break;
                case 5 : type = "m15k" ; break;
                case 6 : type = "m30k" ; break;
                case 7 : type = "m60k" ; break;
            }



            String url = "http://pdfm.eastmoney.com/EM_UBG_PDTI_Fast/api/js?rtntype=5&token=4f1862fc3b5e77c150a2b985b12db0fd&cb=jQuery183017615742790226108_1568593087961&id=" + skuCode + mk + "&type=" + type + "&authorityType=&_=1568593108420";
            System.out.println(url);
            String jsStr = SpiderUtils.HttpClientBuilderGet(url);
            jsStr = jsStr.substring(jsStr.indexOf("(") + 1 , jsStr.indexOf(")"));
            JSONObject jsonObject = JSON.parseObject(jsStr);
            String stockCode = jsonObject.getString("code");
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for(int i = 0 ; i < jsonArray.size() ; i++){
                String dataString = jsonArray.getString(i);
                String[] datas = dataString.split(",");
                StockDealData dealData = new StockDealData();
                dealData.setDealPeriod(periodType);
                dealData.setStockCode(stockCode);
                for(int x = 0 ; x < datas.length ; x ++){
                    switch (x){
                        case 0 : dealData.setDealTime(datas[x]); break;
                        case 1 : dealData.setOpenPrice(new BigDecimal(datas[x])); break;
                        case 2 : dealData.setClosePrice(new BigDecimal (datas[x])); break;
                        case 3 : dealData.setHighPrice(new BigDecimal (datas[x])); break;
                        case 4 : dealData.setLowPrice(new BigDecimal (datas[x])); break;
                        case 5 : dealData.setDealCount(new BigDecimal (datas[x])); break;
                        case 6 : dealData.setDealMoney(datas[x]); break;
                        case 7 : dealData.setAmplitude(datas[x]); break;
                        case 8 : dealData.setHandRate(new BigDecimal (datas[x])); break;
                    }
                }
                list.add(dealData);
            }

            List<StockDealData> dataList = selectList(new EntityWrapper<StockDealData>().where("stock_code = {0} and deal_period = {1}" ,skuCode ,periodType ));
            for(StockDealData dealData : list){
                boolean just = false;
                for(StockDealData existData : dataList){
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


    public void batchList(List<StockDealData> dataList){
        //分批处理
        if(null!=dataList&&dataList.size()>0){
            int pointsDataLimit = 400;//限制条数
            Integer size = dataList.size();
            //判断是否有必要分批
            if(pointsDataLimit<size){
                int part = size/pointsDataLimit;//分批数
                System.out.println("共有 ： "+size+"条，！"+" 分为 ："+part+"批");
                for (int i = 0; i < part; i++) {
                    List<StockDealData> listPage = dataList.subList(0, pointsDataLimit);
                    insertBatch(listPage);
                     //剔除
                     dataList.subList(0, pointsDataLimit).clear();
                }
                if(!dataList.isEmpty()){
                     System.out.println(dataList);//表示最后剩下的数据
                }
            }else{
                insertBatch(dataList);
                System.out.println(dataList);
            }
        }else{
            System.out.println("没有数据!!!");
        }
    }

    @Override
    public List<StockDealData> getPointDayScopeList(String stockCode, String pointDay, String days) {
        return baseMapper.getPointDayScopeList(stockCode ,pointDay ,days);
    }

    @Override
    public List<StockOrderStatic_VO> getStockOrderStaticList(String sectorName, String orderType, String startDay, String endDay) {
        return baseMapper.getStockOrderStaticList( sectorName, orderType, startDay, endDay);
    }

    @Override
    public List<FestivalStatic_VO> getStockFestivalStaticList(String sectorName, String startDay, String endDay, String startTime, String endTime) {
        return baseMapper.getStockFestivalStaticList( sectorName, startDay, endDay, startTime, endTime);
    }


    @Override
    public StockDealDateRank_VO caculateBoll(String stockCode ,String pointDay , String selectDay) {
        List<StockDealDateRank_VO> list = baseMapper.getStockDealDateByRank(stockCode , "1" , pointDay ,selectDay);
        for(StockDealDateRank_VO rank_vo : list){
            BigDecimal averageMoney = BigDecimal.ZERO;
            BigDecimal totalMoney = BigDecimal.ZERO;
            List<StockDealDateRank_VO> subList = baseMapper.getStockDealDateByRank(stockCode , "1" , rank_vo.getDealTime() , "20");
            for(StockDealDateRank_VO rankVo : subList){
                totalMoney = totalMoney.add(rankVo.getClosePrice());
            }
            averageMoney = totalMoney.divide(BigDecimal.valueOf(20) ,2 ,BigDecimal.ROUND_HALF_UP);

            BigDecimal totalSpace = BigDecimal.ZERO ;
            for(StockDealDateRank_VO rankVo : subList){
                totalSpace = totalSpace.add((rankVo.getClosePrice().subtract(averageMoney)).pow(2));
            }
            BigDecimal averageSpace = totalSpace.divide(BigDecimal.valueOf(20) ,4 ,BigDecimal.ROUND_HALF_UP);
            BigDecimal standarMoney = MathUtil.sqrt(averageSpace);

            rank_vo.setAveragePrice(averageMoney);
            rank_vo.setStandarPrice(standarMoney.multiply(BigDecimal.valueOf(2)).setScale(2,BigDecimal.ROUND_HALF_UP));

            rank_vo.setTopPrice(rank_vo.getAveragePrice().add(rank_vo.getStandarPrice()));
            rank_vo.setBottomPrice(rank_vo.getAveragePrice().subtract(rank_vo.getStandarPrice()));

            rank_vo.setTopDistance(rank_vo.getTopPrice().subtract(rank_vo.getClosePrice()));
            rank_vo.setBottomDistance(rank_vo.getClosePrice().subtract(rank_vo.getBottomPrice()));
            rank_vo.setMiddleDistance(rank_vo.getClosePrice().subtract(rank_vo.getAveragePrice()));
        }
        StockDealDateRank_VO rank_vo = caculateAverageBoll(list);
        return rank_vo;
    }

    private StockDealDateRank_VO caculateAverageBoll(List<StockDealDateRank_VO> list){
        StockDealDateRank_VO rankVo = null;
        BigDecimal avarageSpace = BigDecimal.ZERO;
        BigDecimal avarageStock = BigDecimal.ZERO;
        BigDecimal averagePrice = BigDecimal.ZERO;
        BigDecimal closePrice = BigDecimal.ZERO;
        BigDecimal num = BigDecimal.ZERO;
        for(StockDealDateRank_VO rank_vo : list){
            if(rankVo == null){
                rankVo = new StockDealDateRank_VO();
                rankVo.setStockCode(rank_vo.getStockCode());
                rankVo.setDealTime(rank_vo.getDealTime());
                rankVo.setOpenPrice(rank_vo.getOpenPrice());
                rankVo.setHighPrice(rank_vo.getHighPrice());
                rankVo.setLowPrice(rank_vo.getLowPrice());
                rankVo.setClosePrice(rank_vo.getClosePrice());
                rankVo.setTopPrice(rank_vo.getTopPrice());
                rankVo.setBottomPrice(rank_vo.getBottomPrice());
                rankVo.setTopDistance(rank_vo.getTopDistance());
                rankVo.setBottomDistance(rank_vo.getBottomDistance());
                rankVo.setMiddleDistance(rank_vo.getMiddleDistance());
                closePrice = rank_vo.getClosePrice();

                BigDecimal topRate = rank_vo.getTopDistance().multiply(BigDecimal.valueOf(100)).divide(rank_vo.getStandarPrice() , 2 ,BigDecimal.ROUND_HALF_UP);

                BigDecimal middleRate = rank_vo.getMiddleDistance().abs().multiply(BigDecimal.valueOf(100)).divide(rank_vo.getStandarPrice() , 2 ,BigDecimal.ROUND_HALF_UP);

                BigDecimal bottomRate = rank_vo.getBottomDistance().multiply(BigDecimal.valueOf(100)).divide(rank_vo.getStandarPrice() , 2 ,BigDecimal.ROUND_HALF_UP);

                if(topRate.compareTo(BigDecimal.valueOf(10)) < 0){
                    rankVo.setIsTop(BigDecimal.ONE);
                }else{
                    rankVo.setIsTop(BigDecimal.ZERO);
                }

                if(middleRate.compareTo(BigDecimal.valueOf(10)) < 0){
                    rankVo.setIsMiddle(BigDecimal.ONE);
                }else{
                    rankVo.setIsMiddle(BigDecimal.ZERO);
                }

                if(bottomRate.compareTo(BigDecimal.valueOf(10)) < 0){
                    rankVo.setIsBottom(BigDecimal.ONE);
                }else{
                    rankVo.setIsBottom(BigDecimal.ZERO);
                }

            }else{
                if(closePrice.compareTo(rank_vo.getClosePrice()) >= 0 ){
                    num = num.add(BigDecimal.ONE);//上升
                }
                closePrice = rank_vo.getClosePrice();
            }
            avarageStock = avarageStock.add((rank_vo.getClosePrice().subtract(rank_vo.getOpenPrice())).abs());
            avarageSpace = avarageSpace.add(rank_vo.getStandarPrice());
            averagePrice = averagePrice.add(rank_vo.getAveragePrice());

        }
        BigDecimal upRate = num.divide((BigDecimal.valueOf(list.size()).subtract(BigDecimal.ONE)) ,2 ,BigDecimal.ROUND_HALF_UP);
        if(upRate.compareTo(BigDecimal.valueOf(0.5)) > 0){
            rankVo.setIsUpper(BigDecimal.ONE);
        }else{
            rankVo.setIsUpper(BigDecimal.ZERO);
        }

        rankVo.setAveragePrice(averagePrice.divide(BigDecimal.valueOf(list.size()) ,2 ,BigDecimal.ROUND_HALF_UP));
        rankVo.setStandarPrice(avarageSpace.divide(BigDecimal.valueOf(list.size()) ,2 ,BigDecimal.ROUND_HALF_UP));
        rankVo.setAverageStock(avarageStock.multiply(BigDecimal.valueOf(2)).divide(BigDecimal.valueOf(list.size()) ,2 ,BigDecimal.ROUND_HALF_UP));
        return rankVo;
    }
}
