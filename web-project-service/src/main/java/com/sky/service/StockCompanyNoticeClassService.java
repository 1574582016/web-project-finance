package com.sky.service;

import com.baomidou.mybatisplus.service.IService;
import com.sky.model.StockCompanyNoticeClass;

/**
 * Created by ThinkPad on 2019/9/5.
 */
public interface StockCompanyNoticeClassService extends IService<StockCompanyNoticeClass> {

    String getStockNoticeClassCode(String bigClass ,String middleClass ,String nticeType );
}
