package com.sky.api.controller;

import com.sky.annotation.LogRecord;
import com.sky.api.AbstractController;
import com.sky.core.utils.MathUtil;
import com.sky.model.IndexDealData;
import com.sky.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by ThinkPad on 2019/5/5.
 */
@RestController
@RequestMapping("/api/statistics")
public class StatisticsApiController extends AbstractController {

    @LogRecord(name = "getIndexMonthData" ,description = "查询股票信息统计数据")
    @PostMapping("/getIndexMonthData")
    public Object getIndexMonthData(String indexCode , String dealPeriod ,String startDay , String endDay){
        List<IndexStatic_VO> list = indexDealDataService.getIndexMonthRateStaticList( indexCode , dealPeriod , startDay , endDay);

        List<BigDecimal> upperArr = new ArrayList<>();
        List<BigDecimal> downArr = new ArrayList<>();
        for(IndexStatic_VO static_vo : list){
            upperArr.add(static_vo.getIncreaseRate());
            downArr.add(static_vo.getDecreaseRate());
        }

        List<IndexStatic_VO> averList = indexDealDataService.getIndexMonthValueStaticList( indexCode , dealPeriod , startDay , endDay);

        List<BigDecimal> changeArr = new ArrayList<>();
        List<BigDecimal> shockArr = new ArrayList<>();

//        List<BigDecimal> openStandArr = new ArrayList<>();
//        List<BigDecimal> closeStandArr = new ArrayList<>();
//        List<BigDecimal> highStandArr = new ArrayList<>();
//        List<BigDecimal> lowStandArr = new ArrayList<>();
        for(IndexStatic_VO static_vo : averList){
//            List<IndexDealData> dataList = indexDealDataService.getIndexDealDataList( indexCode , dealPeriod , startDay , endDay , static_vo.getPointTime());
//            BigDecimal openStand = caculateStandardDeviation(dataList , static_vo.getChangeAverage() ,2).setScale(2,BigDecimal.ROUND_HALF_UP);
//            BigDecimal closeStand = caculateStandardDeviation(dataList , static_vo.getCloseAveragePrice() ,2).setScale(2,BigDecimal.ROUND_HALF_UP);
//            BigDecimal highStand = caculateStandardDeviation(dataList , static_vo.getHighAveragePrice() ,3).setScale(2,BigDecimal.ROUND_HALF_UP);
//            BigDecimal lowStand = caculateStandardDeviation(dataList , static_vo.getShockAverage() ,4).setScale(2,BigDecimal.ROUND_HALF_UP);
//            openStandArr.add(openStand);
//            closeStandArr.add(closeStand);
//            highStandArr.add(highStand);
//            lowStandArr.add(lowStand);

            if(static_vo.getChangeAverage().compareTo(BigDecimal.ZERO) < 0){
                changeArr.add(static_vo.getChangeAverage().multiply(BigDecimal.valueOf(-1)));
            }else{
                changeArr.add(static_vo.getChangeAverage());
            }

            if(static_vo.getShockAverage().compareTo(BigDecimal.ZERO) < 0){
                shockArr.add(static_vo.getShockAverage().multiply(BigDecimal.valueOf(-1)));
            }else{
                shockArr.add(static_vo.getShockAverage());
            }
        }

        Map<String,Object> map = new HashMap<String ,Object>();
        map.put("upperArr",upperArr.toArray());
        map.put("downArr",downArr.toArray());
        map.put("changeArr",changeArr.toArray());
        map.put("shockArr",shockArr.toArray());

//        map.put("openStandArr",openStandArr.toArray());
//        map.put("closeStandArr",closeStandArr.toArray());
//        map.put("highStandArr",highStandArr.toArray());
//        map.put("lowStandArr",lowStandArr.toArray());
        return ResponseEntity.ok(MapSuccess("查询成功！",map));
    }

    private BigDecimal caculateStandardDeviation(List<IndexDealData> dataList ,BigDecimal means ,Integer type){
        BigDecimal standardDeviation = BigDecimal.ZERO;
        if(means.compareTo(BigDecimal.ZERO) < 0){
            means = means.multiply(BigDecimal.valueOf(-1));
        }
        BigDecimal total = BigDecimal.ZERO ;
        for(IndexDealData dealData : dataList){
            if(type == 1){
                total = total.add(dealData.getOpenPrice().subtract(means).pow(2));
            }
            if(type == 2){
                total = total.add(dealData.getClosePrice().subtract(means).pow(2));
            }
            if(type == 3){
                total = total.add(dealData.getHighPrice().subtract(means).pow(2));
            }
            if(type == 4){
                total = total.add(dealData.getLowPrice().subtract(means).pow(2));
            }

        }
//        standardDeviation = MathUtil.sqrt(total.divide(BigDecimal.valueOf(dataList.size()) ,5 ,BigDecimal.ROUND_HALF_UP));
        standardDeviation = BigDecimal.valueOf(Math.sqrt(total.divide(BigDecimal.valueOf(dataList.size()) ,5 ,BigDecimal.ROUND_HALF_UP).doubleValue())).setScale(2,BigDecimal.ROUND_HALF_UP);
        return standardDeviation ;
    }


    @LogRecord(name = "getIndexWeekData" ,description = "查询股票信息统计数据")
    @PostMapping("/getIndexWeekData")
    public Object getIndexWeekData(String indexCode , Integer dataIndex ,String startDay , String endDay){
        String months = (dataIndex + 1) + "";

        List<IndexStatic_VO> list = indexDealDataService.getIndexWeekRateStaticList( indexCode , months , startDay , endDay);

        List<String> titleArr = new ArrayList<>();
        List<BigDecimal> upperArr = new ArrayList<>();
        List<BigDecimal> downArr = new ArrayList<>();
        for(IndexStatic_VO static_vo : list){
            titleArr.add("第" + static_vo.getPointTime() + "周");
            upperArr.add(static_vo.getIncreaseRate());
            downArr.add(static_vo.getDecreaseRate());
        }

        List<IndexStatic_VO> averList = indexDealDataService.getIndexWeekValueStaticList( indexCode , months , startDay , endDay);

        List<BigDecimal> changeArr = new ArrayList<>();
        List<BigDecimal> shockArr = new ArrayList<>();
        for(IndexStatic_VO static_vo : averList){
            if(static_vo.getChangeAverage().compareTo(BigDecimal.ZERO) < 0){
                changeArr.add(static_vo.getChangeAverage().multiply(BigDecimal.valueOf(-1)));
            }else{
                changeArr.add(static_vo.getChangeAverage());
            }

            if(static_vo.getShockAverage().compareTo(BigDecimal.ZERO) < 0){
                shockArr.add(static_vo.getShockAverage().multiply(BigDecimal.valueOf(-1)));
            }else{
                shockArr.add(static_vo.getShockAverage());
            }
        }

        Map<String,Object> map = new HashMap<String ,Object>();
        map.put("titleArr",titleArr.toArray());
        map.put("upperArr",upperArr.toArray());
        map.put("downArr",downArr.toArray());
        map.put("changeArr",changeArr.toArray());
        map.put("shockArr",shockArr.toArray());
        return ResponseEntity.ok(MapSuccess("查询成功！",map));
    }

    @LogRecord(name = "getIndexDayData" ,description = "查询股票信息统计数据")
    @PostMapping("/getIndexDayData")
    public Object getIndexDayData(String indexCode , Integer dataIndex ,String startDay , String endDay){
        String week = (dataIndex + 1) + "";

        List<IndexStatic_VO> list = indexDealDataService.getIndexDayRateStaticList( indexCode , week , startDay , endDay);

        List<String> titleArr = new ArrayList<>();
        List<BigDecimal> upperArr = new ArrayList<>();
        List<BigDecimal> downArr = new ArrayList<>();
        for(IndexStatic_VO static_vo : list){
            titleArr.add("第" + static_vo.getPointTime() + "周");
            upperArr.add(static_vo.getIncreaseRate());
            downArr.add(static_vo.getDecreaseRate());
        }

        List<IndexStatic_VO> averList = indexDealDataService.getIndexDayValueStaticList( indexCode , week , startDay , endDay);

        List<BigDecimal> changeArr = new ArrayList<>();
        List<BigDecimal> shockArr = new ArrayList<>();
        for(IndexStatic_VO static_vo : averList){
            if(static_vo.getChangeAverage().compareTo(BigDecimal.ZERO) < 0){
                changeArr.add(static_vo.getChangeAverage().multiply(BigDecimal.valueOf(-1)));
            }else{
                changeArr.add(static_vo.getChangeAverage());
            }

            if(static_vo.getShockAverage().compareTo(BigDecimal.ZERO) < 0){
                shockArr.add(static_vo.getShockAverage().multiply(BigDecimal.valueOf(-1)));
            }else{
                shockArr.add(static_vo.getShockAverage());
            }
        }

        Map<String,Object> map = new HashMap<String ,Object>();
        map.put("titleArr",titleArr.toArray());
        map.put("upperArr",upperArr.toArray());
        map.put("downArr",downArr.toArray());
        map.put("changeArr",changeArr.toArray());
        map.put("shockArr",shockArr.toArray());
        return ResponseEntity.ok(MapSuccess("查询成功！",map));
    }


    @LogRecord(name = "getForexMonthData" ,description = "查询外汇信息统计数据")
    @PostMapping("/getForexMonthData")
    public Object getForexMonthData(String indexCode , String dealPeriod ,String startDay , String endDay){
        List<IndexStatic_VO> list = forexDealDataService.getForexMonthRateStaticList( indexCode , dealPeriod , startDay , endDay);

        List<BigDecimal> upperArr = new ArrayList<>();
        List<BigDecimal> downArr = new ArrayList<>();
        for(IndexStatic_VO static_vo : list){
            upperArr.add(static_vo.getIncreaseRate());
            downArr.add(static_vo.getDecreaseRate());
        }

        List<IndexStatic_VO> averList = forexDealDataService.getForexMonthValueStaticList( indexCode , dealPeriod , startDay , endDay);

        List<BigDecimal> changeArr = new ArrayList<>();
        List<BigDecimal> shockArr = new ArrayList<>();
        for(IndexStatic_VO static_vo : averList){
            if(static_vo.getChangeAverage().compareTo(BigDecimal.ZERO) < 0){
                changeArr.add(static_vo.getChangeAverage().multiply(BigDecimal.valueOf(-1)));
            }else{
                changeArr.add(static_vo.getChangeAverage());
            }

            if(static_vo.getShockAverage().compareTo(BigDecimal.ZERO) < 0){
                shockArr.add(static_vo.getShockAverage().multiply(BigDecimal.valueOf(-1)));
            }else{
                shockArr.add(static_vo.getShockAverage());
            }
        }

        Map<String,Object> map = new HashMap<String ,Object>();
        map.put("upperArr",upperArr.toArray());
        map.put("downArr",downArr.toArray());
        map.put("changeArr",changeArr.toArray());
        map.put("shockArr",shockArr.toArray());
        return ResponseEntity.ok(MapSuccess("查询成功！",map));
    }

    @LogRecord(name = "getForexWeekData" ,description = "查询外汇信息统计数据")
    @PostMapping("/getForexWeekData")
    public Object getForexWeekData(String indexCode , Integer dataIndex ,String startDay , String endDay){
        String months = (dataIndex + 1) + "";

        List<IndexStatic_VO> list = forexDealDataService.getForexMonthRateStaticList( indexCode , months , startDay , endDay);

        List<String> titleArr = new ArrayList<>();
        List<BigDecimal> upperArr = new ArrayList<>();
        List<BigDecimal> downArr = new ArrayList<>();
        for(IndexStatic_VO static_vo : list){
            titleArr.add("第" + static_vo.getPointTime() + "周");
            upperArr.add(static_vo.getIncreaseRate());
            downArr.add(static_vo.getDecreaseRate());
        }

        List<IndexStatic_VO> averList = forexDealDataService.getForexMonthValueStaticList( indexCode , months , startDay , endDay);

        List<BigDecimal> changeArr = new ArrayList<>();
        List<BigDecimal> shockArr = new ArrayList<>();
        for(IndexStatic_VO static_vo : averList){
            if(static_vo.getChangeAverage().compareTo(BigDecimal.ZERO) < 0){
                changeArr.add(static_vo.getChangeAverage().multiply(BigDecimal.valueOf(-1)));
            }else{
                changeArr.add(static_vo.getChangeAverage());
            }

            if(static_vo.getShockAverage().compareTo(BigDecimal.ZERO) < 0){
                shockArr.add(static_vo.getShockAverage().multiply(BigDecimal.valueOf(-1)));
            }else{
                shockArr.add(static_vo.getShockAverage());
            }
        }

        Map<String,Object> map = new HashMap<String ,Object>();
        map.put("titleArr",titleArr.toArray());
        map.put("upperArr",upperArr.toArray());
        map.put("downArr",downArr.toArray());
        map.put("changeArr",changeArr.toArray());
        map.put("shockArr",shockArr.toArray());
        return ResponseEntity.ok(MapSuccess("查询成功！",map));
    }


    @LogRecord(name = "getMessagePriceStaticData" ,description = "查询消息影响统计数据")
    @PostMapping("/getMessagePriceStaticData")
    public Object getMessagePriceStaticData(String messageType, String timeType, String directType){
        List<MessageStatic_VO> list = messagePriceStaticService.getMessagePriceStaticData( messageType, timeType, directType);
        List<String> title = new ArrayList<>();
        List<Integer> count = new ArrayList<>();
        for(MessageStatic_VO static_vo : list){
            title.add(static_vo.getTitle());
            count.add(static_vo.getCount());
        }
        Map<String,Object> map = new HashMap<String ,Object>();
        map.put("title",title.toArray());
        map.put("count",count.toArray());
        return ResponseEntity.ok(MapSuccess("查询成功！",map));
    }

    @LogRecord(name = "getMessageStaticCount" ,description = "查询消息影响统计数据比例")
    @PostMapping("/getMessageStaticCount")
    public Object getMessageStaticCount(String messageType){
        List<MessageStatic_VO> list = messagePriceStaticService.getMessageStaticCount( messageType );
        BigDecimal total_minus_3 = BigDecimal.ZERO ;
        BigDecimal total_minus_2 = BigDecimal.ZERO ;
        BigDecimal total_minus_1 = BigDecimal.ZERO ;
        BigDecimal total_equal_0 = BigDecimal.ZERO ;
        BigDecimal total_plus_1 = BigDecimal.ZERO ;
        BigDecimal total_plus_2 = BigDecimal.ZERO ;
        BigDecimal total_plus_3 = BigDecimal.ZERO ;

        for(MessageStatic_VO static_vo : list){
            switch (static_vo.getTimeType()){
                case -3 : total_minus_3 = total_minus_3.add(static_vo.getStaticNum()); break;
                case -2 : total_minus_2 = total_minus_2.add(static_vo.getStaticNum()); break;
                case -1 : total_minus_1 = total_minus_1.add(static_vo.getStaticNum()); break;
                case  0 : total_equal_0 = total_equal_0.add(static_vo.getStaticNum()); break;
                case  1 : total_plus_1 = total_plus_1.add(static_vo.getStaticNum()); break;
                case  2 : total_plus_2 = total_plus_2.add(static_vo.getStaticNum()); break;
                case  3 : total_plus_3 = total_plus_3.add(static_vo.getStaticNum()); break;
            }
        }
        List<BigDecimal> minusList = new ArrayList<>();
        List<BigDecimal> equalList = new ArrayList<>();
        List<BigDecimal> plusList = new ArrayList<>();
        for(MessageStatic_VO static_vo : list){
            if(static_vo.getTimeType() == -3){
                if(static_vo.getDirectType() == -1){
                    minusList.add(static_vo.getStaticNum().multiply(BigDecimal.valueOf(100)).divide(total_minus_3 , 2 ,BigDecimal.ROUND_HALF_UP));
                }
                if(static_vo.getDirectType() ==  0){
                    equalList.add(static_vo.getStaticNum().multiply(BigDecimal.valueOf(100)).divide(total_minus_3 , 2 ,BigDecimal.ROUND_HALF_UP));
                }
                if(static_vo.getDirectType() ==  1){
                    plusList.add(static_vo.getStaticNum().multiply(BigDecimal.valueOf(100)).divide(total_minus_3 , 2 ,BigDecimal.ROUND_HALF_UP));
                }
            }

            if(static_vo.getTimeType() == -2){
                if(static_vo.getDirectType() == -1){
                    minusList.add(static_vo.getStaticNum().multiply(BigDecimal.valueOf(100)).divide(total_minus_2 , 2 ,BigDecimal.ROUND_HALF_UP));
                }
                if(static_vo.getDirectType() ==  0){
                    equalList.add(static_vo.getStaticNum().multiply(BigDecimal.valueOf(100)).divide(total_minus_2 , 2 ,BigDecimal.ROUND_HALF_UP));
                }
                if(static_vo.getDirectType() ==  1){
                    plusList.add(static_vo.getStaticNum().multiply(BigDecimal.valueOf(100)).divide(total_minus_2 , 2 ,BigDecimal.ROUND_HALF_UP));
                }
            }

            if(static_vo.getTimeType() == -1){
                if(static_vo.getDirectType() == -1){
                    minusList.add(static_vo.getStaticNum().multiply(BigDecimal.valueOf(100)).divide(total_minus_1 , 2 ,BigDecimal.ROUND_HALF_UP));
                }
                if(static_vo.getDirectType() ==  0){
                    equalList.add(static_vo.getStaticNum().multiply(BigDecimal.valueOf(100)).divide(total_minus_1 , 2 ,BigDecimal.ROUND_HALF_UP));
                }
                if(static_vo.getDirectType() ==  1){
                    plusList.add(static_vo.getStaticNum().multiply(BigDecimal.valueOf(100)).divide(total_minus_1 , 2 ,BigDecimal.ROUND_HALF_UP));
                }
            }

            if(static_vo.getTimeType() == 0){
                if(static_vo.getDirectType() == -1){
                    minusList.add(static_vo.getStaticNum().multiply(BigDecimal.valueOf(100)).divide(total_equal_0 , 2 ,BigDecimal.ROUND_HALF_UP));
                }
                if(static_vo.getDirectType() ==  0){
                    equalList.add(static_vo.getStaticNum().multiply(BigDecimal.valueOf(100)).divide(total_equal_0 , 2 ,BigDecimal.ROUND_HALF_UP));
                }
                if(static_vo.getDirectType() ==  1){
                    plusList.add(static_vo.getStaticNum().multiply(BigDecimal.valueOf(100)).divide(total_equal_0 , 2 ,BigDecimal.ROUND_HALF_UP));
                }
            }

            if(static_vo.getTimeType() == 1){
                if(static_vo.getDirectType() == -1){
                    minusList.add(static_vo.getStaticNum().multiply(BigDecimal.valueOf(100)).divide(total_plus_1 , 2 ,BigDecimal.ROUND_HALF_UP));
                }
                if(static_vo.getDirectType() ==  0){
                    equalList.add(static_vo.getStaticNum().multiply(BigDecimal.valueOf(100)).divide(total_plus_1 , 2 ,BigDecimal.ROUND_HALF_UP));
                }
                if(static_vo.getDirectType() ==  1){
                    plusList.add(static_vo.getStaticNum().multiply(BigDecimal.valueOf(100)).divide(total_plus_1 , 2 ,BigDecimal.ROUND_HALF_UP));
                }
            }

            if(static_vo.getTimeType() == 2){
                if(static_vo.getDirectType() == -1){
                    minusList.add(static_vo.getStaticNum().multiply(BigDecimal.valueOf(100)).divide(total_plus_2 , 2 ,BigDecimal.ROUND_HALF_UP));
                }
                if(static_vo.getDirectType() ==  0){
                    equalList.add(static_vo.getStaticNum().multiply(BigDecimal.valueOf(100)).divide(total_plus_2 , 2 ,BigDecimal.ROUND_HALF_UP));
                }
                if(static_vo.getDirectType() ==  1){
                    plusList.add(static_vo.getStaticNum().multiply(BigDecimal.valueOf(100)).divide(total_plus_2 , 2 ,BigDecimal.ROUND_HALF_UP));
                }
            }

            if(static_vo.getTimeType() == 3){
                if(static_vo.getDirectType() == -1){
                    minusList.add(static_vo.getStaticNum().multiply(BigDecimal.valueOf(100)).divide(total_plus_3 , 2 ,BigDecimal.ROUND_HALF_UP));
                }
                if(static_vo.getDirectType() ==  0){
                    equalList.add(static_vo.getStaticNum().multiply(BigDecimal.valueOf(100)).divide(total_plus_3 , 2 ,BigDecimal.ROUND_HALF_UP));
                }
                if(static_vo.getDirectType() ==  1){
                    plusList.add(static_vo.getStaticNum().multiply(BigDecimal.valueOf(100)).divide(total_plus_3 , 2 ,BigDecimal.ROUND_HALF_UP));
                }
            }
        }


        List<String> title = new ArrayList<>();
        title.add("前3天");
        title.add("前2天");
        title.add("前1天");
        title.add("当天");
        title.add("后1天");
        title.add("后2天");
        title.add("后3天");

        Map<String,Object> map = new HashMap<String ,Object>();
        map.put("title",title.toArray());
        map.put("minusList",minusList.toArray());
        map.put("equalList",equalList.toArray());
        map.put("plusList",plusList.toArray());
        return ResponseEntity.ok(MapSuccess("查询成功！",map));
    }


    @LogRecord(name = "getIndexTimeData" ,description = "查询分时统计数据")
    @PostMapping("/getIndexTimeData")
    public Object getIndexTimeData(String indexCode , String dealPeriod ,String startDay , String endDay){
        List<IndexStatic_VO> list = indexDealDataService.getIndexTimeRateStaticList( indexCode, dealPeriod , startDay , endDay);
        BigDecimal totalCount = BigDecimal.ZERO ;

        List<String> title = new ArrayList<>();
        List<BigDecimal> average = new ArrayList<>();
        List<BigDecimal> line = new ArrayList<>();
        for(IndexStatic_VO static_vo : list){
            totalCount = totalCount.add(static_vo.getDealCount());
        }

        BigDecimal maxValue = BigDecimal.ZERO;
        for(IndexStatic_VO static_vo : list){
            title.add(static_vo.getPointTime());
            BigDecimal caculate = static_vo.getDealCountAverage().divide(BigDecimal.valueOf(10000) ,2 ,BigDecimal.ROUND_HALF_UP);
            if(caculate.compareTo(maxValue) > 0){
                maxValue = caculate;
            }
            average.add(caculate);
            line.add(static_vo.getDealCount().divide(totalCount ,6 ,BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100)).setScale(2,BigDecimal.ROUND_HALF_UP));
        }
        Map<String,Object> map = new HashMap<String ,Object>();
        map.put("title",title.toArray());
        map.put("average",average.toArray());
        map.put("line",line.toArray());
        map.put("maxValue",maxValue.add(BigDecimal.valueOf(500)));
        return ResponseEntity.ok(MapSuccess("查询成功！",map));
    }

    @LogRecord(name = "getIndexTimeValueData" ,description = "查询分时统计数据")
    @PostMapping("/getIndexTimeValueData")
    public Object getIndexTimeValueData(String indexCode , String dealPeriod ,String startDay , String endDay){
        List<IndexStatic_VO> list = indexDealDataService.getIndexTimeValueStaticList( indexCode, dealPeriod , startDay , endDay);
        BigDecimal totalCount = BigDecimal.ZERO ;

        List<String> title = new ArrayList<>();
        List<BigDecimal> changeRange = new ArrayList<>();
        List<BigDecimal> shockRange = new ArrayList<>();
        List<BigDecimal> changeAverage = new ArrayList<>();
        List<BigDecimal> shockAverage = new ArrayList<>();
        for(IndexStatic_VO static_vo : list){
            title.add(static_vo.getPointTime());
            changeRange.add(static_vo.getChangeRange());
            shockRange.add(static_vo.getShockRange());
            changeAverage.add(static_vo.getChangeAverage());
            shockAverage.add(static_vo.getShockAverage());
        }
        Map<String,Object> map = new HashMap<String ,Object>();
        map.put("title",title.toArray());
        map.put("changeRange",changeRange.toArray());
        map.put("shockRange",shockRange.toArray());
        map.put("changeAverage",changeAverage.toArray());
        map.put("shockAverage",shockAverage.toArray());
        return ResponseEntity.ok(MapSuccess("查询成功！",map));
    }


    @LogRecord(name = "getCovarIndexAndSector" ,description = "查询行业相关性统计")
    @PostMapping("/getCovarIndexAndSector")
    public Object getCovarIndexAndSector(String indexCode , String dealPeriod ,String startDay , String endDay){
        List<CovarStatic_VO> list = sectorDealDataService.caculateCovarIndexAndSector( indexCode, dealPeriod , startDay , endDay);
        return ResponseEntity.ok(MapSuccess("查询成功！",list));
    }

    @LogRecord(name = "getSectorMonthData" ,description = "查询行业统计数据")
    @PostMapping("/getSectorMonthData")
    public Object getSectorMonthData(String sectorCode , String dealPeriod ,String startDay , String endDay){
        if(StringUtils.isNotBlank(sectorCode)){
            sectorCode = sectorCode.substring(0,sectorCode.length()-1);
        }
        List<IndexStatic_VO> list = sectorDealDataService.getSectorMonthRateStaticList( sectorCode , dealPeriod , startDay , endDay);

        List<BigDecimal> upperArr = new ArrayList<>();
        List<BigDecimal> downArr = new ArrayList<>();
        for(IndexStatic_VO static_vo : list){
            upperArr.add(static_vo.getIncreaseRate());
            downArr.add(static_vo.getDecreaseRate());
        }

        List<IndexStatic_VO> averList = sectorDealDataService.getSectorMonthValueStaticList( sectorCode , dealPeriod , startDay , endDay);

        List<BigDecimal> changeArr = new ArrayList<>();
        List<BigDecimal> shockArr = new ArrayList<>();

        BigDecimal maxChangeValue = BigDecimal.ZERO ;
        BigDecimal maxShockValue = BigDecimal.ZERO ;
        for(IndexStatic_VO static_vo : averList){
            BigDecimal changeAverage = BigDecimal.ZERO ;
            BigDecimal shockAverage = BigDecimal.ZERO ;
            if(static_vo.getChangeAverage().compareTo(BigDecimal.ZERO) < 0){
                changeAverage = static_vo.getChangeAverage().multiply(BigDecimal.valueOf(-1));
            }
            if(static_vo.getShockAverage().compareTo(BigDecimal.ZERO) < 0){
                shockAverage = static_vo.getShockAverage().multiply(BigDecimal.valueOf(-1));
            }
            changeArr.add(changeAverage);
            shockArr.add(shockAverage);
            if(changeAverage.compareTo(maxChangeValue) > 0){
                maxChangeValue = changeAverage ;
            }
            if(shockAverage.compareTo(maxShockValue) > 0){
                maxShockValue = shockAverage ;
            }
        }

        Map<String,Object> map = new HashMap<String ,Object>();
        map.put("upperArr",upperArr.toArray());
        map.put("downArr",downArr.toArray());
        map.put("changeArr",changeArr.toArray());
        map.put("shockArr",shockArr.toArray());
        map.put("maxChangeValue",maxChangeValue.add(BigDecimal.valueOf(500)));
        map.put("maxShockValue",maxShockValue.add(BigDecimal.valueOf(500)));
        return ResponseEntity.ok(MapSuccess("查询成功！",map));
    }

    @LogRecord(name = "getSectorWeekData" ,description = "查询行业计数据")
    @PostMapping("/getSectorWeekData")
    public Object getSectorWeekData(String sectorCode , Integer dataIndex ,String startDay , String endDay){
        String months = (dataIndex + 1) + "";

        List<IndexStatic_VO> list = sectorDealDataService.getSectorWeekRateStaticList( sectorCode , months , startDay , endDay);

        List<String> titleArr = new ArrayList<>();
        List<BigDecimal> upperArr = new ArrayList<>();
        List<BigDecimal> downArr = new ArrayList<>();
        for(IndexStatic_VO static_vo : list){
            titleArr.add("第" + static_vo.getPointTime() + "周");
            upperArr.add(static_vo.getIncreaseRate());
            downArr.add(static_vo.getDecreaseRate());
        }

        List<IndexStatic_VO> averList = sectorDealDataService.getSectorWeekValueStaticList( sectorCode , months , startDay , endDay);

        List<BigDecimal> changeArr = new ArrayList<>();
        List<BigDecimal> shockArr = new ArrayList<>();
        for(IndexStatic_VO static_vo : averList){
            if(static_vo.getChangeAverage().compareTo(BigDecimal.ZERO) < 0){
                changeArr.add(static_vo.getChangeAverage().multiply(BigDecimal.valueOf(-1)));
            }else{
                changeArr.add(static_vo.getChangeAverage());
            }

            if(static_vo.getShockAverage().compareTo(BigDecimal.ZERO) < 0){
                shockArr.add(static_vo.getShockAverage().multiply(BigDecimal.valueOf(-1)));
            }else{
                shockArr.add(static_vo.getShockAverage());
            }
        }

        Map<String,Object> map = new HashMap<String ,Object>();
        map.put("titleArr",titleArr.toArray());
        map.put("upperArr",upperArr.toArray());
        map.put("downArr",downArr.toArray());
        map.put("changeArr",changeArr.toArray());
        map.put("shockArr",shockArr.toArray());
        return ResponseEntity.ok(MapSuccess("查询成功！",map));
    }

    @LogRecord(name = "getSectorDayData" ,description = "查询行业计数据")
    @PostMapping("/getSectorDayData")
    public Object getSectorDayData(String sectorCode , Integer dataIndex ,String startDay , String endDay){
        String week = (dataIndex + 1) + "";

        List<IndexStatic_VO> list = sectorDealDataService.getSectorDayRateStaticList( sectorCode , week , startDay , endDay);

        List<String> titleArr = new ArrayList<>();
        List<BigDecimal> upperArr = new ArrayList<>();
        List<BigDecimal> downArr = new ArrayList<>();
        for(IndexStatic_VO static_vo : list){
            titleArr.add("第" + static_vo.getPointTime() + "天");
            upperArr.add(static_vo.getIncreaseRate());
            downArr.add(static_vo.getDecreaseRate());
        }

        List<IndexStatic_VO> averList = sectorDealDataService.getSectorDayValueStaticList( sectorCode , week , startDay , endDay);

        List<BigDecimal> changeArr = new ArrayList<>();
        List<BigDecimal> shockArr = new ArrayList<>();
        for(IndexStatic_VO static_vo : averList){
            if(static_vo.getChangeAverage().compareTo(BigDecimal.ZERO) < 0){
                changeArr.add(static_vo.getChangeAverage().multiply(BigDecimal.valueOf(-1)));
            }else{
                changeArr.add(static_vo.getChangeAverage());
            }

            if(static_vo.getShockAverage().compareTo(BigDecimal.ZERO) < 0){
                shockArr.add(static_vo.getShockAverage().multiply(BigDecimal.valueOf(-1)));
            }else{
                shockArr.add(static_vo.getShockAverage());
            }
        }

        Map<String,Object> map = new HashMap<String ,Object>();
        map.put("titleArr",titleArr.toArray());
        map.put("upperArr",upperArr.toArray());
        map.put("downArr",downArr.toArray());
        map.put("changeArr",changeArr.toArray());
        map.put("shockArr",shockArr.toArray());
        return ResponseEntity.ok(MapSuccess("查询成功！",map));
    }

    @LogRecord(name = "getSectorOrderStaticList" ,description = "查询行业计数据")
    @PostMapping("/getSectorOrderStaticList")
    public Object getSectorOrderStaticList(String orderType ,String startDay , String endDay){
        List<SectorOrderStatic_VO> list = sectorDealDataService.getSectorOrderStaticList(orderType , startDay , endDay);
        Map<String ,Object> map = new HashMap<String ,Object>();
        map.put("total",list.size());
        map.put("rows",list);
        return map;
    }
}
