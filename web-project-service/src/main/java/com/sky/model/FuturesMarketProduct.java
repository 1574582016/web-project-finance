package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;

/**
 * Created by ThinkPad on 2019/6/25.
 */
@TableName("futures_market_product")
public class FuturesMarketProduct extends BaseModel<FuturesMarketProduct> {

    @TableField(value = "product_code")
    private String productCode ;

    @TableField(value = "market_name")
    private String marketName ;

    @TableField(value = "market_identify")
    private String marketIdentify ;

    @TableField(value = "product_name")
    private String productName ;

    @TableField(value = "product_identify")
    private String productIdentify ;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getMarketIdentify() {
        return marketIdentify;
    }

    public void setMarketIdentify(String marketIdentify) {
        this.marketIdentify = marketIdentify;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductIdentify() {
        return productIdentify;
    }

    public void setProductIdentify(String productIdentify) {
        this.productIdentify = productIdentify;
    }
}
