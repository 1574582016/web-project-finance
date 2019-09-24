package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by ThinkPad on 2019/9/24.
 */
@Data
@TableName("stock_tiger_list")
public class StockTigerList extends BaseModel<StockTigerList> {

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
     * 发布日期
     */
    @TableField("publish_time")
    private String publishTime ;


    /**
     * 涨跌幅
     */
    @TableField("upper_range")
    private BigDecimal upperRange ;

    /**
     *换手率
     */
    @TableField("hand_rate")
    private BigDecimal handRate ;


    /**
     *上榜原因
     */
    @TableField("focus_reason")
    private String focusReason ;


    /**
     *买入额
     */
    @TableField("buy_money")
    private BigDecimal buyMoney ;

    /**
     *卖出额
     */
    @TableField("sell_money")
    private BigDecimal sellMoney ;

    /**
     * 异动原因
     */
    @TableField("unusual_reason")
    private String unusualReason ;


    /**
     * 主力选择原因
     */
    @TableField("choose_reason")
    private String chooseReason ;

}
