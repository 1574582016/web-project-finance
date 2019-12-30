package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by ThinkPad on 2019/12/30.
 */
@Data
@TableName("stock_profit_avarege_rate")
public class StockProfitAvaregeRate extends BaseModel<StockProfitAvaregeRate> {

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
     *上市日期
     */
    @TableField("publish_time")
    private String publishTime ;

    /**
     *上市时间
     */
    @TableField("space_year")
    private Integer spaceYear ;

    /**
     *利润增长概率
     */
    @TableField("average_grow_rate")
    private BigDecimal averageGrowRate ;

    /**
     *利润增长值(亿)
     */
    @TableField("average_grow_profit")
    private BigDecimal averageGrowProfit ;

}
