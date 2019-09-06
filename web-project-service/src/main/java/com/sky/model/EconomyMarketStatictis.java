package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2019/5/22.
 */
@Data
@TableName("economy_market_statictis")
public class EconomyMarketStatictis extends BaseModel<EconomyMarketStatictis> {

    /**
     * 市场编码
     */
    @TableField("market_code")
    private String marketCode ;

    /**
     * 父级市场编码
     */
    @TableField("parent_code")
    private String parentCode ;

    /**
     * 市场名称
     */
    @TableField("market_name")
    private String marketName ;

    /**
     * 类型1-产业;2-人
     */
    @TableField("market_type")
    private Integer marketType ;

    /**
     * 市场描述
     */
    @TableField("market_analysis")
    private String marketAnalysis ;

}
