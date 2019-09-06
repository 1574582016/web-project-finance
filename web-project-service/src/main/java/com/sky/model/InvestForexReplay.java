package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by ThinkPad on 2019/5/16.
 */
@Data
@TableName("invest_forex_replay")
public class InvestForexReplay extends BaseModel<InvestForexReplay> {

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
    private String investStrategy ;

    /**
     *货币对
     */
    @TableField(value = "currency_pairs")
    private String currencyPairs ;

    /**
     *时间周期1-1分钟,2-5分钟,3-15分钟,4-30分钟,5-1小时,6-4小时,7-12小时,8-24时
     */
    @TableField(value = "time_cycle")
    private String timeCycle ;

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
     *平盘率
     */
    @TableField(value = "flat_rate")
    private BigDecimal flatRate ;

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


    @TableField(exist = false)
    List<InvestForexReplayDetail> detailList ;

}
