package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;

/**
 * Created by ThinkPad on 2018/10/20.
 */
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

    public String getMarketCode() {
        return marketCode;
    }

    public void setMarketCode(String marketCode) {
        this.marketCode = marketCode;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getMarketType() {
        return marketType;
    }

    public void setMarketType(String marketType) {
        this.marketType = marketType;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
