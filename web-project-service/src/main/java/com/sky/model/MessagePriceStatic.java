package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import com.sky.core.model.MiddleModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by ThinkPad on 2019/9/18.
 */
@Data
@TableName("message_price_static")
public class MessagePriceStatic extends MiddleModel<MessagePriceStatic> {

    /**
     * 消息类型
     */
    @TableField("message_type")
    private String messageType ;

    /**
     * 时间类型
     */
    @TableField("time_type")
    private Integer timeType ;

    /**
     * 编码
     */
    @TableField("stock_code")
    private String stockCode ;

    /**
     * 方向类型1-升，2-降
     */
    @TableField("direct_type")
    private Integer directType ;

    /**
     * 差额
     */
    @TableField("diff_price")
    private BigDecimal diffPrice ;
}
