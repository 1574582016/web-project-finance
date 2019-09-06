package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2019/8/31.
 */
@Data
@TableName("stock_pool_class")
public class StockPoolClass extends BaseModel<StockPoolClass> {

    /**
     *编码
     */
    @TableField("pool_code")
    private String poolCode ;

    /**
     *名称
     */
    @TableField("pool_name")
    private String poolName ;

    /**
     *父级编码
     */
    @TableField("parent_code")
    private String parentCode ;

    /**
     *排序
     */
    @TableField("pool_sore")
    private Integer poolSore ;

}
