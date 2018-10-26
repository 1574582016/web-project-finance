package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;

/**
 * Created by lxl on 2018/9/28.
 */
@TableName("finance_market")
public class FinanceMarket extends BaseModel<FinanceMarket>{

    @TableField(value = "market_code")
    private String marketCode ;

    @TableField(value = "market_name")
    private String marketName ;

    @TableField(value = "parent_code")
    private String parentCode ;

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

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
