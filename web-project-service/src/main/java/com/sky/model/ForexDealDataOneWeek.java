package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by ThinkPad on 2020/1/15.
 */
@Data
@TableName("forex_deal_data_one_week")
public class ForexDealDataOneWeek extends BaseModel<ForexDealDataOneWeek> {

    /**
     * 时间
     */
    @TableField("deal_time")
    private String dealTime;

    /**
     * 代码
     */
    @TableField("forex_code")
    private String forexCode;

    /**
     * 开盘价
     */
    @TableField("open_price")
    private BigDecimal openPrice;

    /**
     * 收盘价
     */
    @TableField("close_price")
    private BigDecimal closePrice;

    /**
     * 最高价
     */
    @TableField("high_price")
    private BigDecimal highPrice;

    /**
     * 最低价
     */
    @TableField("low_price")
    private BigDecimal lowPrice;

    /**
     * 成交量
     */
    @TableField("deal_count")
    private BigDecimal dealCount;
}
