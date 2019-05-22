package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;

/**
 * Created by ThinkPad on 2019/5/22.
 */
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

    public String getMarketCode() {
        return marketCode;
    }

    public void setMarketCode(String marketCode) {
        this.marketCode = marketCode;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public Integer getMarketType() {
        return marketType;
    }

    public void setMarketType(Integer marketType) {
        this.marketType = marketType;
    }

    public String getMarketAnalysis() {
        return marketAnalysis;
    }

    public void setMarketAnalysis(String marketAnalysis) {
        this.marketAnalysis = marketAnalysis;
    }
}
