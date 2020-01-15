package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.core.utils.DateUtils;
import com.sky.core.utils.FileUtils;
import com.sky.mapper.ForexDealDataMapper;
import com.sky.mapper.ForexDealDataOneMinuteMapper;
import com.sky.model.ForexDealData;
import com.sky.model.ForexDealDataOneMinute;
import com.sky.service.ForexDealDataService;
import com.sky.vo.IndexStatic_VO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThinkPad on 2019/9/21.
 */
@Service
@Transactional
public class ForexDealDataServiceImpl extends ServiceImpl<ForexDealDataMapper,ForexDealData> implements ForexDealDataService {

    @Autowired
    private ForexDealDataOneMinuteMapper forexDealDataOneMinuteMapper ;

    @Override
    public List<IndexStatic_VO> getForexMonthRateStaticList(String indexCode, String dealPeriod, String startDay, String endDay) {
        return baseMapper.getForexMonthRateStaticList( indexCode, dealPeriod, startDay, endDay);
    }

    @Override
    public List<IndexStatic_VO> getForexMonthValueStaticList(String indexCode, String dealPeriod, String startDay, String endDay) {
        return baseMapper.getForexMonthValueStaticList( indexCode, dealPeriod, startDay, endDay);
    }

    @Override
    public List<IndexStatic_VO> getForexWeekRateStaticList(String indexCode, String month, String startDay, String endDay) {
        return baseMapper.getForexWeekRateStaticList( indexCode, month, startDay, endDay);
    }

    @Override
    public List<IndexStatic_VO> getForexWeekValueStaticList(String indexCode, String month, String startDay, String endDay) {
        return baseMapper.getForexWeekValueStaticList( indexCode, month, startDay, endDay);
    }

    @Override
    public List<ForexDealDataOneMinute> analysisForexDataFile(String fileName) {
        String text = FileUtils.readFileContent("E://XAUUSD.txt");
        String[] strs = text.split("-");
        List<ForexDealDataOneMinute> list = new ArrayList<>();
        for(int i = 1 ; i < strs.length ; i++){
            String str = strs[i];
            String[] arr = str.split(",");
            ForexDealDataOneMinute dealData = new ForexDealDataOneMinute();
            String day = "" ;
            String time = "" ;
            for(int j = 0 ; j < arr.length ; j++){
                switch (j){
                    case 0 :
                        String forexCode = arr[j];
                        dealData.setForexCode(forexCode);
                        break;
                    case 1 :
                        day = caculateDay(arr[j]);
                        break;
                    case 2 :
                        time = caculateTime(arr[j]);
                        break;
                    case 3 :
                        String open = arr[j];
                        dealData.setOpenPrice(new BigDecimal(open));
                        break;
                    case 4 :
                        String high = arr[j];
                        dealData.setHighPrice(new BigDecimal(high));
                        break;
                    case 5 :
                        String low = arr[j];
                        dealData.setLowPrice(new BigDecimal(low));
                        break;
                    case 6 :
                        String close = arr[j];
                        dealData.setClosePrice(new BigDecimal(close));
                        break;
                }
            }
            dealData.setDealTime(day + " " + time);
            if (DateUtils.parseDate(dealData.getDealTime()).getTime() >= DateUtils.parseDate("2010-01-01 00:00:00").getTime()){
                list.add(dealData);
            }
        }
        return list ;
    }

    private String caculateDay(String dayString){
        return dayString.substring(0,4) + "-" + dayString.substring(4,6) + "-" + dayString.substring(6,8);
    }

    private String caculateTime(String timeString){
        return timeString.substring(0,2) + ":" + timeString.substring(2,4) + ":" + timeString.substring(4,6);
    }
}
