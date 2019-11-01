package com.sky.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.sky.annotation.LogRecord;
import com.sky.api.AbstractController;
import com.sky.core.utils.DateUtils;
import com.sky.model.ForexNewsStatictis;
import com.sky.vo.MacroEconomy_VO;
import com.sky.vo.WeekLossJobCountVO;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ThinkPad on 2019/10/28.
 */
@RestController
@RequestMapping("/api/macroEconomy")
public class ContryMacroEconomyApiController extends AbstractController {

    @LogRecord(name = "getContryMacroEconomy" ,description = "查询国际经济数据")
    @PostMapping("/getContryMacroEconomy")
    public Object getContryMacroEconomy(String contry , String indexCodes ,String startDay ,String endDay){
        Map<String,Object> resultMap = new HashedMap();
        List<String> titleArr = new ArrayList<String>();
        List<String> nameArr = new ArrayList<String>();
        List<JSONObject> dataArr = new ArrayList<>();
        String[] codes = indexCodes.split(",");
        for(int i = 0 ; i < codes.length ; i++){
            String indexCode = codes[i];
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("type" , "line");
            jsonObject.put("stack" , indexCode);
            List<BigDecimal> arrayList = new ArrayList<BigDecimal>();
            List<MacroEconomy_VO> list = contryMacroEconomyIndexService.getContryMacroEconomy( contry ,  indexCode , startDay , endDay);
            for(int j = 0 ; j < list.size() ; j++){
                MacroEconomy_VO economy_vo = list.get(j);
                if(i == 0){
                    titleArr.add(economy_vo.getPublishDay());
                }
                if(j == 0){
                    nameArr.add(economy_vo.getClassName());
                    jsonObject.put("name" , economy_vo.getClassName());
                }
                arrayList.add(economy_vo.getPublishValue());
            }
            jsonObject.put("data" ,arrayList);
            dataArr.add(jsonObject);
        }
        resultMap.put("titleArr",titleArr);
        resultMap.put("nameArr",nameArr);
        resultMap.put("dataArr",dataArr);
        return ResponseEntity.ok(MapSuccess("操作成功",resultMap));
    }

    @LogRecord(name = "getWeekLossJobCount" ,description = "查询每周失业人数")
    @PostMapping("/getWeekLossJobCount")
    public Object getWeekLossJobCount(String startDay ,String endDay){
        startDay = "2019-01-01";
        endDay = null;
        Map<String,Object> resultMap = new HashedMap();
        List<String> titleArr = new ArrayList<String>();
        List<String> nameArr = new ArrayList<String>();
        List<JSONObject> dataArr = new ArrayList<>();
        List<WeekLossJobCountVO> firstLossJobList = contryMacroEconomyIndexService.getWeekLossJobCount("294" ,startDay,endDay);
        List<WeekLossJobCountVO> continueLossJobList = contryMacroEconomyIndexService.getWeekLossJobCount("522" , DateUtils.formatDate(DateUtils.addMonths(DateUtils.parseDate(startDay ,"yyyy-MM-dd") ,-1) , "yyyy-MM-dd"),endDay);
        List<BigDecimal> firstList = new ArrayList<>();
        List<BigDecimal> secondList = new ArrayList<>();
        List<BigDecimal> thirdList = new ArrayList<>();
        List<BigDecimal> forthList = new ArrayList<>();
        List<BigDecimal> fifthList = new ArrayList<>();
        for(int i = 0 ; i < firstLossJobList.size() ; i++){
            WeekLossJobCountVO firstLossJobVo = firstLossJobList.get(i);
            WeekLossJobCountVO continueLossJobVoLast = continueLossJobList.get(i);
            WeekLossJobCountVO continueLossJobVoNow = continueLossJobList.get(i+1);
            titleArr.add(firstLossJobVo.getPublishMonth());

            BigDecimal firstActualCount = BigDecimal.ZERO ;
            if(continueLossJobVoLast.getFifthWeekValue() != null && continueLossJobVoLast.getFifthWeekValue().compareTo(BigDecimal.ZERO) > 0){
                firstActualCount = firstActualCount.add(firstLossJobVo.getFirstWeekValue().add(continueLossJobVoNow.getFirstWeekValue().subtract(continueLossJobVoLast.getFifthWeekValue())));
            }else{
                firstActualCount = firstActualCount.add(firstLossJobVo.getFirstWeekValue().add(continueLossJobVoNow.getFirstWeekValue().subtract(continueLossJobVoLast.getForthWeekValue())));
            }
            firstList.add(firstActualCount);

            BigDecimal secondActualCount = firstLossJobVo.getSecondWeekValue().add(continueLossJobVoNow.getSecondWeekValue().subtract(continueLossJobVoNow.getFirstWeekValue()));
            secondList.add(secondActualCount);

            BigDecimal thirdActualCount = firstLossJobVo.getThirdWeekValue().add(continueLossJobVoNow.getThirdWeekValue().subtract(continueLossJobVoNow.getSecondWeekValue()));
            thirdList.add(thirdActualCount);

            BigDecimal forthActualCount = firstLossJobVo.getForthWeekValue().add(continueLossJobVoNow.getForthWeekValue().subtract(continueLossJobVoNow.getThirdWeekValue()));
            forthList.add(forthActualCount);

            if(firstLossJobVo.getFifthWeekValue() != null && firstLossJobVo.getFifthWeekValue().compareTo(BigDecimal.ZERO) > 0){
                BigDecimal fifthActualCount = firstLossJobVo.getFifthWeekValue().add(continueLossJobVoNow.getFifthWeekValue().subtract(continueLossJobVoNow.getForthWeekValue()));
                fifthList.add(fifthActualCount);
            }else {
                fifthList.add(BigDecimal.ZERO);
            }

        }
        JSONObject firstJson = new JSONObject();
        firstJson.put("name" , "第1周");
        firstJson.put("type" , "bar");
        firstJson.put("stack" , "统计");
        firstJson.put("data" , firstList);
        dataArr.add(firstJson);

        JSONObject secondJson = new JSONObject();
        secondJson.put("name" , "第2周");
        secondJson.put("type" , "bar");
        secondJson.put("stack" , "统计");
        secondJson.put("data" , secondList);
        dataArr.add(secondJson);

        JSONObject thirdJson = new JSONObject();
        thirdJson.put("name" , "第3周");
        thirdJson.put("type" , "bar");
        thirdJson.put("stack" , "统计");
        thirdJson.put("data" , thirdList);
        dataArr.add(thirdJson);

        JSONObject forthJson = new JSONObject();
        forthJson.put("name" , "第4周");
        forthJson.put("type" , "bar");
        forthJson.put("stack" , "统计");
        forthJson.put("data" , forthList);
        dataArr.add(forthJson);

        JSONObject fifthJson = new JSONObject();
        fifthJson.put("name" , "第5周");
        fifthJson.put("type" , "bar");
        fifthJson.put("stack" , "统计");
        fifthJson.put("data" , fifthList);
        dataArr.add(fifthJson);
        nameArr.add("第1周");
        nameArr.add("第2周");
        nameArr.add("第3周");
        nameArr.add("第4周");
        nameArr.add("第5周");

        resultMap.put("titleArr",titleArr);
        resultMap.put("nameArr",nameArr);
        resultMap.put("dataArr",dataArr);
        return ResponseEntity.ok(MapSuccess("操作成功",resultMap));
    }

}
