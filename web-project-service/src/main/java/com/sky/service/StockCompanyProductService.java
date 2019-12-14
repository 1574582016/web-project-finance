package com.sky.service;

import com.baomidou.mybatisplus.service.IService;
import com.sky.model.StockCompanyProduct;

import java.util.List;

/**
 * Created by ThinkPad on 2019/5/27.
 */
public interface StockCompanyProductService extends IService<StockCompanyProduct> {

    List<StockCompanyProduct> getStockCompanyConstruct(String stockCode);

    void spiderStockCompanyProduct(String market ,String skuCode);

    List<StockCompanyProduct> getNewCompanyProductList( String productType , String stockCode);
}
