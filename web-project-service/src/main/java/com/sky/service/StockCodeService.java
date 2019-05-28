package com.sky.service;

import com.baomidou.mybatisplus.service.IService;
import com.sky.model.StockCode;

import java.util.List;

/**
 * Created by ThinkPad on 2019/5/27.
 */
public interface StockCodeService extends IService<StockCode> {

    void spiderStockCode(String url ,String sector);
}
