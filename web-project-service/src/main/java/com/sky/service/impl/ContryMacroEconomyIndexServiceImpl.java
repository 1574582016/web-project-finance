package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.core.utils.CommonHttpUtil;
import com.sky.mapper.ContryMacroEconomyIndexMapper;
import com.sky.model.ContryMacroEconomyClass;
import com.sky.model.ContryMacroEconomyIndex;
import com.sky.service.ContryMacroEconomyIndexService;
import com.sky.vo.WeekLossJobCountVO;
import com.sky.vo.MacroEconomy_VO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ThinkPad on 2019/10/26.
 */
@Service
@Transactional
public class ContryMacroEconomyIndexServiceImpl extends ServiceImpl<ContryMacroEconomyIndexMapper,ContryMacroEconomyIndex> implements ContryMacroEconomyIndexService {

    @Override
    public List<MacroEconomy_VO> getContryMacroEconomy(String contry, String indexCode ,String startDay ,String endDay) {
        return baseMapper.getContryMacroEconomy( contry, indexCode , startDay , endDay);
    }

    @Override
    public void spiderMacroEconomyIndex(ContryMacroEconomyClass economyClass) {
        try {
            String contryClass = economyClass.getContryClass();
            String subIndexClass = economyClass.getSubIndexClass();
            String classCode = economyClass.getClassCode();
            List<ContryMacroEconomyIndex> indexList = baseMapper.selectList(new EntityWrapper<ContryMacroEconomyIndex>().where("class_code = {0}" , classCode));
            Map<String ,String> map = new HashMap<>();
            for(ContryMacroEconomyIndex index : indexList){
                map.put(index.getPublishDay() ,index.getPublishValue());
            }
            String url = "https://sbcharts.investing.com/events_charts/us/"+ classCode +".json";
            String jsStr = CommonHttpUtil.sendGet(url);
            List<ContryMacroEconomyIndex> list = new ArrayList<>();
            JSONArray jsonArray = JSON.parseObject(jsStr).getJSONArray("attr");
            for(int i = 0 ; i < jsonArray.size() ; i ++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if(StringUtils.isBlank(map.get(jsonObject.getString("timestamp")))){
                    ContryMacroEconomyIndex economyIndex = new ContryMacroEconomyIndex();
                    economyIndex.setClassCode(classCode);
                    economyIndex.setContryClass(contryClass);
                    economyIndex.setSubIndexClass(subIndexClass);
                    economyIndex.setPublishDay(jsonObject.getString("timestamp"));
                    economyIndex.setPublishValue(jsonObject.getString("actual"));
                    list.add(economyIndex);
                }
            }
            if (list.size() > 0){
                insertBatch(list);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<WeekLossJobCountVO> getWeekLossJobCount(String indexCode, String startDay, String endDay) {
        return baseMapper.getWeekLossJobCount( indexCode, startDay, endDay);
    }

    @Override
    public List<MacroEconomy_VO> getContryMacroEconomyMonth(String contry, String indexCode, String startDay, String endDay) {
        return baseMapper.getContryMacroEconomyMonth( contry, indexCode, startDay, endDay);
    }

    @Override
    public List<MacroEconomy_VO> getUsMarkitPMIIndex(String contry, String indexCode,String week , String startDay, String endDay) {
        return baseMapper.getUsMarkitPMIIndex( contry,  indexCode, week ,  startDay,  endDay);
    }
}
