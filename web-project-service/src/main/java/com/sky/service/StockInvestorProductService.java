package com.sky.service;

import com.baomidou.mybatisplus.service.IService;
import com.sky.model.StockInvestorProduct;
import com.sky.vo.StockInvestor_VO;

import java.util.List;

/**
 * Created by ThinkPad on 2019/12/24.
 */
public interface StockInvestorProductService extends IService<StockInvestorProduct> {

    List<StockInvestor_VO> getStockInvestorStaticList(String stockCode);

    List<StockInvestor_VO> getStockInvestorList(String stockCode);
}
