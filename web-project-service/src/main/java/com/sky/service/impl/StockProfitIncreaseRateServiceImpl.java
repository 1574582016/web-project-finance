package com.sky.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.mapper.StockCompanyProductMapper;
import com.sky.mapper.StockCompanyProfitMapper;
import com.sky.mapper.StockProfitIncreaseRateMapper;
import com.sky.model.StockProfitIncreaseRate;
import com.sky.service.StockProfitIncreaseRateService;
import com.sky.vo.CompanyProfit_VO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/12/28/028.
 */
@Service
@Transactional
public class StockProfitIncreaseRateServiceImpl extends ServiceImpl<StockProfitIncreaseRateMapper,StockProfitIncreaseRate> implements StockProfitIncreaseRateService {

    @Autowired
    private StockCompanyProfitMapper profitMapper ;

    @Override
    public void caculateStockProfitIncreaseRate(String stockCode) {
        CompanyProfit_VO profitVo = profitMapper.getStockProfitSpaceYear(stockCode);
        List<StockProfitIncreaseRate> list = new ArrayList<>();
        for(int i = 0 ; i < profitVo.getSpaceYear() ; i ++){
            CompanyProfit_VO profit_vo = profitMapper.getStockProfitSeasonIncreaseRate(stockCode ,(profitVo.getMinYear() + i)+"");
            if(profit_vo != null){
                StockProfitIncreaseRate increaseRate = new StockProfitIncreaseRate();
                increaseRate.setStockCode(stockCode);
                increaseRate.setStaticYear(profitVo.getMinYear()+i);
                BigDecimal averageProfitRate = BigDecimal.ZERO;
                if(i == 0 && profit_vo != null){
                    averageProfitRate = profit_vo.getTotalProfitIncreaseRate();
                }else{
                    averageProfitRate = profitMapper.caculateProfitIncreaseRate(stockCode , "5" , (profitVo.getMinYear() + i)+"");
                }
                increaseRate.setTotalProfit(profit_vo.getTotalProfit());
                increaseRate.setTotalIncreaseRate(profit_vo.getTotalProfitIncreaseRate());
                increaseRate.setAverageIncreaseRate(averageProfitRate);
                increaseRate.setFirstIncreaseRate(profit_vo.getFirstSeasonIncreaseRate());
                increaseRate.setSecondIncreaseRate(profit_vo.getSecondSeasonIncreaseRate());
                increaseRate.setThirdIncreaseRate(profit_vo.getThirdSeasonIncreaseRate());
                increaseRate.setForthIncreaseRate(profit_vo.getForthSeasonIncreaseRate());
                list.add(increaseRate);
            }
        }
        if(list.size() > 0){
            insertBatch(list);
        }
//        System.out.println(list.toString());
    }
}
