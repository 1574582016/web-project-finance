package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

/**
 * Created by lxl on 2018/9/28.
 */
@Data
@TableName("finance_market")
public class FinanceMarket extends BaseModel<FinanceMarket>{

    @TableField(value = "market_code")
    private String marketCode ;

    @TableField(value = "market_name")
    private String marketName ;

    @TableField(value = "parent_code")
    private String parentCode ;

    @TableField(value = "describe")
    private String describe ;

    @TableField(value = "order_num")
    private Integer orderNum ;

    @TableField(value = "market_type")
    private Integer marketType ;

}
