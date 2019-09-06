package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by ThinkPad on 2019/5/16.
 */
@Data
@TableName("invest_forex_replay_detail")
public class InvestForexReplayDetail extends BaseModel<InvestForexReplayDetail> {

    /**
     *复盘code
     */
    @TableField(value = "replay_code")
    private String replayCode ;

    /**
     *投资类型1-做多;2-做空
     */
    @TableField(value = "invest_type")
    private String investType ;

    /**
     *开始时间
     */
    @TableField(value = "start_time")
    private Date startTime ;

    /**
     *结束时间
     */
    @TableField(value = "end_time")
    private Date endTime ;

    /**
     *k线数量
     */
    @TableField(value = "line_number")
    private Integer lineNumber ;

    /**
     *获取点数
     */
    @TableField(value = "gain_point")
    private BigDecimal gainPoint ;

    /**
     *备注
     */
    @TableField(value = "remark")
    private String remark ;

}
