package com.sky.api.controller;

import com.sky.annotation.LogRecord;
import com.sky.api.AbstractController;
import com.sky.vo.MessageStatic_VO;
import com.sky.vo.StockStatisticsEchart_VO;
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

    @LogRecord(name = "getStockStatisticsData" ,description = "查询股票信息统计数据")
    @PostMapping("/getStockStatisticsData")
    public Object getLearnQuestionList(){
        List<StockStatisticsEchart_VO> list = stockIndexService.getStockStatisticsByParame();
        List<String> rise = new ArrayList<>();
        List<String> down = new ArrayList<>();
        for(StockStatisticsEchart_VO body : list){
            String dateTime = body.getDateTime();
            Integer dataType = body.getDateType();
            if(dateTime.equals("01")){
               if(dataType == 1){
                   rise.add(body.getCountNum());
               }else{
                   down.add(body.getCountNum());
               }
            }

            if(dateTime.equals("02")){
                if(dataType == 1){
                    rise.add(body.getCountNum());
                }else{
                    down.add(body.getCountNum());
                }
            }

            if(dateTime.equals("03")){
                if(dataType == 1){
                    rise.add(body.getCountNum());
                }else{
                    down.add(body.getCountNum());
                }
            }

            if(dateTime.equals("04")){
                if(dataType == 1){
                    rise.add(body.getCountNum());
                }else{
                    down.add(body.getCountNum());
                }
            }

            if(dateTime.equals("05")){
                if(dataType == 1){
                    rise.add(body.getCountNum());
                }else{
                    down.add(body.getCountNum());
                }
            }

            if(dateTime.equals("06")){
                if(dataType == 1){
                    rise.add(body.getCountNum());
                }else{
                    down.add(body.getCountNum());
                }
            }

            if(dateTime.equals("07")){
                if(dataType == 1){
                    rise.add(body.getCountNum());
                }else{
                    down.add(body.getCountNum());
                }
            }

            if(dateTime.equals("08")){
                if(dataType == 1){
                    rise.add(body.getCountNum());
                }else{
                    down.add(body.getCountNum());
                }
            }

            if(dateTime.equals("09")){
                if(dataType == 1){
                    rise.add(body.getCountNum());
                }else{
                    down.add(body.getCountNum());
                }
            }

            if(dateTime.equals("10")){
                if(dataType == 1){
                    rise.add(body.getCountNum());
                }else{
                    down.add(body.getCountNum());
                }
            }

            if(dateTime.equals("11")){
                if(dataType == 1){
                    rise.add(body.getCountNum());
                }else{
                    down.add(body.getCountNum());
                }
            }

            if(dateTime.equals("12")){
                if(dataType == 1){
                    rise.add(body.getCountNum());
                }else{
                    down.add(body.getCountNum());
                }
            }
        }

        Map<String,Object> map = new HashMap<String ,Object>();
        map.put("rise",rise.toArray());
        map.put("down",down.toArray());
        return map;
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
}
