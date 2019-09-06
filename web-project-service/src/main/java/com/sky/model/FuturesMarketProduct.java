package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2019/6/25.
 */
@Data
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

}
