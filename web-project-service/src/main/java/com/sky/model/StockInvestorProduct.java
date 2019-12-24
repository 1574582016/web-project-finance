package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2019/12/24.
 */
@Data
@TableName("stock_investor_product")
public class StockInvestorProduct extends BaseModel<StockInvestorProduct> {

    /**
     *股票编码
     */
    @TableField("stock_code")
    private String stockCode ;

    /**
     *股票名称
     */
    @TableField("stock_name")
    private String stockName ;

    /**
     *投资数量
     */
    @TableField("invest_count")
    private String investCount ;

    /**
     *总股本
     */
    @TableField("total_count")
    private String totalCount ;

    /**
     *流通股本
     */
    @TableField("flow_count")
    private String flowCount ;

    /**
     *统计时间
     */
    @TableField("static_time")
    private String staticTime ;

    /**
     *起始时间
     */
    @TableField("start_time")
    private String startTime ;
}
