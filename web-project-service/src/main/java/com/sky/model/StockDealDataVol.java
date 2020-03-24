package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import com.sky.core.model.MiddleModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by ThinkPad on 2020/3/24.
 */
@Data
@TableName("stock_deal_data_vol")
public class StockDealDataVol extends MiddleModel<StockDealDataVol> {

    /**
     * 上市代码
     */
    @TableField("stock_code")
    private String stockCode ;

    /**
     * 时间
     */
    @TableField("deal_time")
    private String dealTime ;

    /**
     * 类型
     */
    @TableField("deal_type")
    private Integer dealType ;

    /**
     * 价格
     */
    @TableField("deal_price")
    private BigDecimal dealPrice ;

    /**
     * 成交量
     */
    @TableField("deal_count")
    private BigDecimal dealCount ;
}
