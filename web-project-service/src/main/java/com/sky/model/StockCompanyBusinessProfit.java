package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2020/5/12.
 */
@Data
@TableName("stock_company_business_profit")
public class StockCompanyBusinessProfit extends BaseModel<StockCompanyBusinessProfit> {

    /**
     * 上市代码
     */
    @TableField("stock_code")
    private String stockCode ;

    /**
     *公布日期
     */
    @TableField("publish_day")
    private String publishDay ;

    /**
     *业务总收入
     */
    @TableField("business_income")
    private String businessIncome ;

    /**
     *业务总成本
     */
    @TableField("business_cost")
    private String businessCost ;

    /**
     *业务总利润
     */
    @TableField("business_profit")
    private String businessProfit ;

    /**
     *总利润，包括其他收入利润
     */
    @TableField("total_profit")
    private String totalProfit ;

    /**
     *净利润
     */
    @TableField("pure_profit")
    private String pureProfit ;
}
