package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.core.utils.SpiderUtils;
import com.sky.mapper.StockCompanyProductMapper;
import com.sky.model.StockCode;
import com.sky.model.StockCompanyAnalyse;
import com.sky.model.StockCompanyBase;
import com.sky.model.StockCompanyProduct;
import com.sky.service.StockCodeService;
import com.sky.service.StockCompanyProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThinkPad on 2019/5/27.
 */
@Service
@Transactional
public class StockCompanyProductServiceImpl extends ServiceImpl<StockCompanyProductMapper,StockCompanyProduct> implements StockCompanyProductService {

    @Autowired
    private StockCodeService stockCodeService;

    @Override
    public List<StockCompanyProduct> getStockCompanyConstruct(String stockCode) {
        return baseMapper.getStockCompanyConstruct(stockCode);
    }

    @Override
    public void spiderStockCompanyProduct(String market ,String skuCode) {
        String productUrl = "http://f10.eastmoney.com/BusinessAnalysis/BusinessAnalysisAjax?code=" + market + skuCode;

        String productString = SpiderUtils.HttpClientBuilderGet(productUrl);
        JSONObject productObject = JSON.parseObject(productString);
        JSONArray productArray =productObject.getJSONArray("zygcfx");
        List<StockCompanyProduct> productList = new ArrayList<StockCompanyProduct>();
        for(int j = 0 ; j < productArray.size() ; j++){
            JSONObject detailObject = productArray.getJSONObject(j);
            String date = detailObject.getString("rq");
            JSONArray sectorArray = detailObject.getJSONArray("hy");
            JSONArray regionArray = detailObject.getJSONArray("qy");
            JSONArray saleArray = detailObject.getJSONArray("cp");
            for(int x = 0 ;x < sectorArray.size() ; x++){
                JSONObject sectorObject = sectorArray.getJSONObject(x);
                StockCompanyProduct stockCompanyProduct = new StockCompanyProduct();
                stockCompanyProduct.setStockCode(skuCode);
                stockCompanyProduct.setPublishDate(date);
                stockCompanyProduct.setPruductType("行业分类");
                stockCompanyProduct.setProductName(sectorObject.getString("zygc"));
                stockCompanyProduct.setProductRevenue(sectorObject.getString("zysr"));
                stockCompanyProduct.setProductRevenueRate(sectorObject.getString("srbl"));
                stockCompanyProduct.setProductCost(sectorObject.getString("zycb"));
                stockCompanyProduct.setProductCostRate(sectorObject.getString("cbbl"));
                stockCompanyProduct.setProductProfit(sectorObject.getString("zylr"));
                stockCompanyProduct.setProductProfitRate(sectorObject.getString("lrbl"));
                stockCompanyProduct.setProductGrossRate(sectorObject.getString("mll"));
                productList.add(stockCompanyProduct);
            }

            for(int y = 0 ;y < saleArray.size() ; y++){
                JSONObject saleObject = saleArray.getJSONObject(y);
                StockCompanyProduct stockCompanyProduct = new StockCompanyProduct();
                stockCompanyProduct.setStockCode(skuCode);
                stockCompanyProduct.setPublishDate(date);
                stockCompanyProduct.setPruductType("产品分类");
                stockCompanyProduct.setProductName(saleObject.getString("zygc"));
                stockCompanyProduct.setProductRevenue(saleObject.getString("zysr"));
                stockCompanyProduct.setProductRevenueRate(saleObject.getString("srbl"));
                stockCompanyProduct.setProductCost(saleObject.getString("zycb"));
                stockCompanyProduct.setProductCostRate(saleObject.getString("cbbl"));
                stockCompanyProduct.setProductProfit(saleObject.getString("zylr"));
                stockCompanyProduct.setProductProfitRate(saleObject.getString("lrbl"));
                stockCompanyProduct.setProductGrossRate(saleObject.getString("mll"));
                productList.add(stockCompanyProduct);
            }

            for(int z = 0 ;z < regionArray.size() ; z++){
                JSONObject regionObject = regionArray.getJSONObject(z);
                StockCompanyProduct stockCompanyProduct = new StockCompanyProduct();
                stockCompanyProduct.setStockCode(skuCode);
                stockCompanyProduct.setPublishDate(date);
                stockCompanyProduct.setPruductType("地域分类");
                stockCompanyProduct.setProductName(regionObject.getString("zygc"));
                stockCompanyProduct.setProductRevenue(regionObject.getString("zysr"));
                stockCompanyProduct.setProductRevenueRate(regionObject.getString("srbl"));
                stockCompanyProduct.setProductCost(regionObject.getString("zycb"));
                stockCompanyProduct.setProductCostRate(regionObject.getString("cbbl"));
                stockCompanyProduct.setProductProfit(regionObject.getString("zylr"));
                stockCompanyProduct.setProductProfitRate(regionObject.getString("lrbl"));
                stockCompanyProduct.setProductGrossRate(regionObject.getString("mll"));
                productList.add(stockCompanyProduct);
            }
        }
        insertBatch(productList);
    }
}
