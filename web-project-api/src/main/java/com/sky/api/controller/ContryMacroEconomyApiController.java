package com.sky.api.controller;

import com.sky.annotation.LogRecord;
import com.sky.api.AbstractController;
import com.sky.model.ForexNewsStatictis;
import com.sky.vo.MacroEconomy_VO;
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
    public Object getContryMacroEconomy(String contry , String indexCode ,String startDay ,String endDay){
        List<MacroEconomy_VO> list = contryMacroEconomyIndexService.getContryMacroEconomy( contry ,  indexCode , startDay , endDay);
        List<String> title = new ArrayList<String>();
        List<BigDecimal> arrayList = new ArrayList<BigDecimal>();
        String name = "";
        for(MacroEconomy_VO economy_vo : list){
            if(StringUtils.isBlank(name)){
                name = economy_vo.getClassName();
            }
            title.add(economy_vo.getPublishDay());
            arrayList.add(economy_vo.getPublishValue());
        }
        Map<String,Object> resultMap = new HashedMap();
        resultMap.put("title",title);
        resultMap.put("name",name);
        resultMap.put("arrayList",arrayList);
        return ResponseEntity.ok(MapSuccess("操作成功",resultMap));
    }
}
