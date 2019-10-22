package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2019/10/22.
 */
@Data
@TableName("stock_company_profit")
public class StockCompanyProfit extends BaseModel<StockCompanyProfit> {

    /**
     *编码
     */
    @TableField(value = "stock_code")
    private String stockCode ;

    /**
     *公布日期
     */
    @TableField(value = "publish_day")
    private String publishDay ;

    /**
     *总收入
     */
    @TableField(value = "total_income")
    private String totalIncome ;

    /**
     *主营业务收入
     */
    @TableField(value = "business_income")
    private String businessIncome ;

    /**
     *总成本
     */
    @TableField(value = "total_cost")
    private String totalCost ;

    /**
     *主营业务成本
     */
    @TableField(value = "business_cost")
    private String businessCost ;

    /**
     *总利润，包括其他收入利润
     */
    @TableField(value = "total_profit")
    private String totalProfit ;

    /**
     *中营业利润，包括其他营业利润
     */
    @TableField(value = "business_total_profit")
    private String businessTotalProfit ;

    /**
     *收入税收
     */
    @TableField(value = "income_tax")
    private String incomeTax ;

    /**
     *归属利润，扣除其他利润分配后
     */
    @TableField(value = "belong_profit")
    private String belongProfit ;

    /**
     *最终利润，扣税损益后
     */
    @TableField(value = "final_profit")
    private String finalProfit ;
}
