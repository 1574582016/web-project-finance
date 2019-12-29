package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2019/12/28/028.
 */
@Data
@TableName("stock_profit_increase_rate")
public class StockProfitIncreaseRate extends BaseModel<StockProfitIncreaseRate> {

    /**
     *编码
     */
    @TableField("stock_code")
    private String stockCode ;

    /**
     *统计年限
     */
    @TableField("static_year")
    private Integer staticYear ;

    /**
     *总利润
     */
    @TableField("total_profit")
    private BigDecimal totalProfit ;

    /**
     *总利润增长率
     */
    @TableField("total_increase_rate")
    private BigDecimal totalIncreaseRate ;

    /**
     *平均利润增长率
     */
    @TableField("average_increase_rate")
    private BigDecimal averageIncreaseRate ;

    /**
     *第一季度利润增长率
     */
    @TableField("first_increase_rate")
    private BigDecimal firstIncreaseRate ;

    /**
     *第二季度利润增长率
     */
    @TableField("second_increase_rate")
    private BigDecimal secondIncreaseRate ;

    /**
     *第三季度利润增长率
     */
    @TableField("third_increase_rate")
    private BigDecimal thirdIncreaseRate ;

    /**
     *第四季度利润增长率
     */
    @TableField("forth_increase_rate")
    private BigDecimal forthIncreaseRate ;
}
