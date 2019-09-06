package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2018/10/20.
 */
@Data
@TableName("tradition_market")
public class TraditionMarket extends BaseModel<TraditionMarket> {

    @TableField(value = "market_code")
    private String marketCode ;

    @TableField(value = "market_name")
    private String marketName ;

    @TableField(value = "market_type")
    private String marketType ;

    @TableField(value = "describe")
    private String describe ;

}
