package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by ThinkPad on 2019/5/16.
 */
@Data
@TableName("invest_stock_replay")
public class InvestStockReplay extends BaseModel<InvestStockReplay> {

    /**
     *复盘code
     */
    @TableField(value = "replay_code")
    private String replayCode ;

    /**
     *复盘时间
     */
    @TableField(value = "replay_time")
    private String replayTime ;

    /**
     *投资策略
     */
    @TableField(value = "invest_strategy")
    private Integer investStrategy ;

    /**
     *成功率
     */
    @TableField(value = "seccuss_rate")
    private BigDecimal seccussRate ;

    /**
     *失败率
     */
    @TableField(value = "fail_rate")
    private BigDecimal failRate ;

    /**
     *挣取点数
     */
    @TableField(value = "earn_point")
    private BigDecimal earnPoint ;

    /**
     *亏损点数
     */
    @TableField(value = "lose_point")
    private BigDecimal losePoint ;

}
