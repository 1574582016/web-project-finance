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


    @LogRecord(name = "getContryMacroEconomyMonth" ,description = "查询国际经济数据按月查询")
    @PostMapping("/getContryMacroEconomyMonth")
    public Object getContryMacroEconomyMonth(String contry , String indexCodes ,String startDay ,String endDay){
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
            List<MacroEconomy_VO> list = contryMacroEconomyIndexService.getContryMacroEconomyMonth( contry ,  indexCode , startDay , endDay);
            for(int j = 0 ; j < list.size() ; j++){
                MacroEconomy_VO economy_vo = list.get(j);
                if(i == 0){
                    titleArr.add(economy_vo.getPublishMonth());
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



    @LogRecord(name = "getUsMarkitPMIIndex" ,description = "查询美国Markit的PMI指标")
    @PostMapping("/getUsMarkitPMIIndex")
    public Object getUsMarkitPMIIndex(String startDay ,String endDay){
        Map<String,Object> resultMap = new HashedMap();
        List<String> titleArr = new ArrayList<String>();
        List<String> nameArr = new ArrayList<String>();
        List<JSONObject> dataArr = new ArrayList<>();

        List<MacroEconomy_VO> list1 = contryMacroEconomyIndexService.getUsMarkitPMIIndex(null ,"829" ,"3" ,startDay , endDay);
        nameArr.add(list1.get(0).getClassName()+"—预期");
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("name" , list1.get(0).getClassName()+"—预期");
        jsonObject1.put("type" , "line");
        jsonObject1.put("stack" , "8290");
        List<BigDecimal> arrayList1 = new ArrayList<BigDecimal>();
        for(MacroEconomy_VO economy_vo : list1){
            titleArr.add(economy_vo.getPublishMonth());
            arrayList1.add(economy_vo.getPublishValue());
        }
        jsonObject1.put("data" ,arrayList1);
        dataArr.add(jsonObject1);

        List<MacroEconomy_VO> list2 = contryMacroEconomyIndexService.getUsMarkitPMIIndex(null ,"1062" ,"3" ,startDay , endDay);
        nameArr.add(list2.get(0).getClassName()+"—预期");
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("name" , list2.get(0).getClassName()+"—预期");
        jsonObject2.put("type" , "line");
        jsonObject2.put("stack" , "10620");
        List<BigDecimal> arrayList2 = new ArrayList<BigDecimal>();
        for(MacroEconomy_VO economy_vo : list2){
            arrayList2.add(economy_vo.getPublishValue());
        }
        jsonObject2.put("data" ,arrayList2);
        dataArr.add(jsonObject2);

        List<MacroEconomy_VO> list3 = contryMacroEconomyIndexService.getUsMarkitPMIIndex(null ,"829" ,"1" ,startDay , endDay);
        nameArr.add(list3.get(0).getClassName()+"—实际");
        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("name" , list3.get(0).getClassName()+"—实际");
        jsonObject3.put("type" , "line");
        jsonObject3.put("stack" , "8291");
        List<BigDecimal> arrayList3 = new ArrayList<BigDecimal>();
        for(MacroEconomy_VO economy_vo : list3){
            arrayList3.add(economy_vo.getPublishValue());
        }
        jsonObject3.put("data" ,arrayList3);
        dataArr.add(jsonObject3);

        List<MacroEconomy_VO> list4 = contryMacroEconomyIndexService.getUsMarkitPMIIndex(null ,"1062" ,"1" ,startDay , endDay);
        nameArr.add(list4.get(0).getClassName()+"—实际");
        JSONObject jsonObject4 = new JSONObject();
        jsonObject4.put("name" , list4.get(0).getClassName()+"—实际");
        jsonObject4.put("type" , "line");
        jsonObject4.put("stack" , "8291");
        List<BigDecimal> arrayList4 = new ArrayList<BigDecimal>();
        for(MacroEconomy_VO economy_vo : list4){
            arrayList4.add(economy_vo.getPublishValue());
        }
        jsonObject4.put("data" ,arrayList4);
        dataArr.add(jsonObject4);

        resultMap.put("titleArr",titleArr);
        resultMap.put("nameArr",nameArr);
        resultMap.put("dataArr",dataArr);
        return ResponseEntity.ok(MapSuccess("操作成功",resultMap));
    }

}
