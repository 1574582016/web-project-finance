package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2020/1/13.
 */
@Data
@TableName("stock_money_flow")
public class StockMoneyFlow extends BaseModel<StockMoneyFlow> {

    /**
     *时间
     */
    @TableField("deal_time")
    private String dealTime ;

    /**
     *编码
     */
    @TableField("stock_code")
    private String stockCode ;

    /**
     *主力资金净流入
     */
    @TableField("magor_money")
    private String magorMoney ;

    /**
     *超大单资金净流入
     */
    @TableField("super_money")
    private String superMoney ;

    /**
     *大单资金净流入
     */
    @TableField("big_money")
    private String bigMoney ;

    /**
     *中单资金净流入
     */
    @TableField("middle_money")
    private String middleMoney ;

    /**
     *小单资金净流入
     */
    @TableField("small_money")
    private String smallMoney ;
}
