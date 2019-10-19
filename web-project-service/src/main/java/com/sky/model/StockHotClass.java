package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2019/10/19.
 */
@Data
@TableName("stock_hot_class")
public class StockHotClass extends BaseModel<StockHotClass> {

    /**
     * 热点编码
     */
    @TableField("hot_code")
    private String hotCode ;

    /**
     * 热点名称
     */
    @TableField("hot_name")
    private String hotName ;

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
}
