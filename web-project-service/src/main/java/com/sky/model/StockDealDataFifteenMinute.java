package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by ThinkPad on 2020/1/4.
 */
@Data
@TableName("stock_deal_data_fifteen_minute")
public class StockDealDataFifteenMinute extends BaseModel<StockDealDataFifteenMinute> {

    /**
     * 周期类型
     */
    @TableField("deal_period")
    private Integer dealPeriod ;

    /**
     * 年
     */
    @TableField("point_year")
    private Integer pointYear ;

    /**
     * 月
     */
    @TableField("point_month")
    private Integer pointMonth ;

    /**
     * 周
     */
    @TableField("point_week")
    private Integer pointWeek ;

    /**
     * 日
     */
    @TableField("point_day")
    private Integer pointDay ;

    /**
     * 时间
     */
    @TableField("deal_time")
    private String dealTime ;

    /**
     * 上市代码
     */
    @TableField("stock_code")
    private String stockCode ;

    /**
     * 开盘价
     */
    @TableField("open_price")
    private BigDecimal openPrice ;

    /**
     * 收盘价
     */
    @TableField("close_price")
    private BigDecimal closePrice ;

    /**
     * 最高价
     */
    @TableField("high_price")
    private BigDecimal highPrice ;

    /**
     * 最低价
     */
    @TableField("low_price")
    private BigDecimal lowPrice ;

    /**
     * 成交量
     */
    @TableField("deal_count")
    private BigDecimal dealCount ;

    /**
     * 成交额
     */
    @TableField("deal_money")
    private String dealMoney ;

    /**
     * 振幅
     */
    @TableField("amplitude")
    private String amplitude ;

    /**
     *换手率
     */
    @TableField("hand_rate")
    private BigDecimal handRate ;
}
