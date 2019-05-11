package com.sky.api.controller;

import com.sky.api.AbstractController;
import com.sky.vo.StockStatisticsEchart_VO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by ThinkPad on 2019/5/5.
 */
@RestController
@RequestMapping("/api/statistics")
public class StatisticsApiController extends AbstractController {

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
}
