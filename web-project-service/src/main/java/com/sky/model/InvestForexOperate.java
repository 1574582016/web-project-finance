package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by ThinkPad on 2019/5/20.
 */
@TableName("invest_forex_operate")
public class InvestForexOperate extends BaseModel<InvestForexOperate> {

    /**
     *操盘code
     */
    @TableField(value = "operate_code")
    private String operateCode ;

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

    public String getOperateCode() {
        return operateCode;
    }

    public void setOperateCode(String operateCode) {
        this.operateCode = operateCode;
    }

    public String getInvestStrategy() {
        return investStrategy;
    }

    public void setInvestStrategy(String investStrategy) {
        this.investStrategy = investStrategy;
    }

    public String getCurrencyPairs() {
        return currencyPairs;
    }

    public void setCurrencyPairs(String currencyPairs) {
        this.currencyPairs = currencyPairs;
    }

    public String getTimeCycle() {
        return timeCycle;
    }

    public void setTimeCycle(String timeCycle) {
        this.timeCycle = timeCycle;
    }

    public String getInvestType() {
        return investType;
    }

    public void setInvestType(String investType) {
        this.investType = investType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public BigDecimal getGainPoint() {
        return gainPoint;
    }

    public void setGainPoint(BigDecimal gainPoint) {
        this.gainPoint = gainPoint;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
