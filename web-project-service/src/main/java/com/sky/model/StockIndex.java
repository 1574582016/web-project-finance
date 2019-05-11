package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;

/**
 * Created by ThinkPad on 2019/5/5.
 */
@TableName("stock_index")
public class StockIndex extends BaseModel<StockIndex> {

    /**
     * 时间
     */
    @TableField("data_time")
    private String dataTime ;

    /**
     * 数据标记
     */
    @TableField("data_sign")
    private String dataSign ;

    /**
     * 指标类型
     */
    @TableField("index_type")
    private Integer indexType ;

    /**
     * 时间类型
     */
    @TableField("time_type")
    private Integer timeType ;

    /**
     * 开盘点数
     */
    @TableField("start_point")
    private String startPoint ;

    /**
     * 收盘点数
     */
    @TableField("end_point")
    private String endPoint ;

    /**
     * 最高点数
     */
    @TableField("high_point")
    private String highPoint ;

    /**
     * 最低点数
     */
    @TableField("low_point")
    private String lowPoint ;

    /**
     * 成交量
     */
    @TableField("deal_number")
    private String dealNumber ;

    /**
     * 成交额
     */
    @TableField("deal_money")
    private String dealMoney ;

    /**
     * 振幅
     */
    @TableField("amplitude")
    private String amplitude ;

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public Integer getIndexType() {
        return indexType;
    }

    public void setIndexType(Integer indexType) {
        this.indexType = indexType;
    }

    public Integer getTimeType() {
        return timeType;
    }

    public void setTimeType(Integer timeType) {
        this.timeType = timeType;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getHighPoint() {
        return highPoint;
    }

    public void setHighPoint(String highPoint) {
        this.highPoint = highPoint;
    }

    public String getLowPoint() {
        return lowPoint;
    }

    public void setLowPoint(String lowPoint) {
        this.lowPoint = lowPoint;
    }

    public String getDealNumber() {
        return dealNumber;
    }

    public void setDealNumber(String dealNumber) {
        this.dealNumber = dealNumber;
    }

    public String getDealMoney() {
        return dealMoney;
    }

    public void setDealMoney(String dealMoney) {
        this.dealMoney = dealMoney;
    }

    public String getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(String amplitude) {
        this.amplitude = amplitude;
    }
}
