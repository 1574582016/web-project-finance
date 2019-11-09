package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by ThinkPad on 2019/11/9.
 */
@Data
@TableName("stock_chose_strategy")
public class StockChoseStrategy extends BaseModel<StockChoseStrategy> {

    /**
     * 策略类型
     */
    @TableField("strategy_type")
    private Integer strategyType ;

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
     * 时间
     */
    @TableField("deal_time")
    private String dealTime ;

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
     *20日品均价
     */
    @TableField("average_price")
    private BigDecimal averagePrice ;

    /**
     *2倍标准差
     */
    @TableField("standar_price")
    private BigDecimal standarPrice ;

    /**
     *上轨价格
     */
    @TableField("top_price")
    private BigDecimal topPrice ;

    /**
     *下轨价格
     */
    @TableField("bottom_price")
    private BigDecimal bottomPrice ;

    /**
     *距离上轨价格
     */
    @TableField("top_distance")
    private BigDecimal topDistance ;

    /**
     *距离中规价格
     */
    @TableField("middle_distance")
    private BigDecimal middleDistance ;

    /**
     *距离下轨价格
     */
    @TableField("bottom_distance")
    private BigDecimal bottomDistance ;

    /**
     *5日平均振幅
     */
    @TableField("average_stock")
    private BigDecimal averageStock ;

    /**
     *是否上涨
     */
    @TableField("is_upper")
    private Integer isUpper ;

    /**
     *是否触顶
     */
    @TableField("is_top")
    private Integer isTop ;

    /**
     *是否触中线
     */
    @TableField("is_middle")
    private Integer isMiddle ;

    /**
     *是否触底
     */
    @TableField("is_bottom")
    private Integer isBottom ;
}
