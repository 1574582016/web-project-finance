package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2019/8/31.
 */
@Data
@TableName("stock_pool_second_class")
public class StockPoolSecondClass extends BaseModel<StockPoolSecondClass> {

    /**
     *编码
     */
    @TableField("first_code")
    private String firstCode ;

    /**
     *名称
     */
    @TableField("first_name")
    private String firstName ;

    /**
     *编码
     */
    @TableField("second_code")
    private String secondCode ;

    /**
     *名称
     */
    @TableField("second_name")
    private String secondName ;

    /**
     *排序
     */
    @TableField("scond_sore")
    private Integer scondSore ;

}
