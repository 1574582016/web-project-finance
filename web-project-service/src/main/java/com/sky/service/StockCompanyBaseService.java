package com.sky.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.sky.model.StockCompanyBase;

/**
 * Created by ThinkPad on 2019/5/27.
 */
public interface StockCompanyBaseService extends IService<StockCompanyBase> {

    void spiderStockCompanyBase(String skuCode);

    Page<StockCompanyBase> getStockCompanyBaseList(Integer page, Integer size, String stockCode, String stockName , String stockSector ,String stockExchange , String startDay ,String endDay ,String middleContrySector);

}
