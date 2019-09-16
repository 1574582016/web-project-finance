package com.sky.service;

import com.baomidou.mybatisplus.service.IService;
import com.sky.model.StockCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ThinkPad on 2019/5/27.
 */
public interface StockCodeService extends IService<StockCode> {

    void spiderStockCode(String classCode ,String sector);

    List<StockCode> getEmptyStockProdectList(String stockSector);

    List<StockCode> getEmptyStockCompanyList(String stockSector);

    List<StockCode> getEmptyStockAnalyseList(String stockSector);
}
