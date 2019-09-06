package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2019/5/5.
 */
@Data
@TableName("stock_index")
public class StockIndex extends BaseModel<StockIndex> {

    /**
     * 时间
     */
    @TableField("data_time")
    private String dataTime ;

    /**
     * 数据标记
     */
    @TableField("data_sign")
    private String dataSign ;

    /**
     * 指标类型
     */
    @TableField("index_type")
    private Integer indexType ;

    /**
     * 时间类型
     */
    @TableField("time_type")
    private Integer timeType ;

    /**
     * 开盘点数
     */
    @TableField("start_point")
    private String startPoint ;

    /**
     * 收盘点数
     */
    @TableField("end_point")
    private String endPoint ;

    /**
     * 最高点数
     */
    @TableField("high_point")
    private String highPoint ;

    /**
     * 最低点数
     */
    @TableField("low_point")
    private String lowPoint ;

    /**
     * 成交量
     */
    @TableField("deal_number")
    private String dealNumber ;

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

}
