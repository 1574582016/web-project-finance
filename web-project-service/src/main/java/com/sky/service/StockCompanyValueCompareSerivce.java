package com.sky.service;

import com.baomidou.mybatisplus.service.IService;
import com.sky.model.StockCompanyValueCompare;

import java.util.List;

/**
 * Created by ThinkPad on 2019/10/6.
 */
public interface StockCompanyValueCompareSerivce extends IService<StockCompanyValueCompare> {

    StockCompanyValueCompare spiderStockCompanyValueCompare(String staticYear ,String market , String marketNum,String stockCode ,String companyName);
}
