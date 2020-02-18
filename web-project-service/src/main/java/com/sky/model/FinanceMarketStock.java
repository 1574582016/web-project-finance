package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2020/2/18.
 */
@Data
@TableName("finance_market_stock")
public class FinanceMarketStock extends BaseModel<FinanceMarketStock> {

    @TableField(value = "market_code")
    private String marketCode ;

    @TableField(value = "market_name")
    private String marketName ;

    @TableField(value = "stock_code")
    private String stockCode ;

    @TableField(value = "stock_name")
    private String stockName ;

    @TableField(value = "stock_describe")
    private String stockDescribe ;

}
