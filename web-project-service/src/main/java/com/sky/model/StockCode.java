package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2019/5/27.
 */
@Data
@TableName("stock_code")
public class StockCode extends BaseModel<StockCode> {

    /**
     * 股票编码
     */
    @TableField("stock_code")
    private String stockCode ;

    /**
     * 股票名称
     */
    @TableField("stock_name")
    private String stockName ;

    /**
     * 交易所
     */
    @TableField("stock_market")
    private String stockMarket ;

    /**
     * 行业
     */
    @TableField("stock_sector")
    private String stockSector;


    @TableField("market_type")
    private Integer marketType ;

}
