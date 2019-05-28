package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;

/**
 * Created by ThinkPad on 2019/5/27.
 */
@TableName("stock_code")
public class StockCode extends BaseModel<StockCode> {

    /**
     * 股票编码
     */
    @TableField("stock_code")
    private String stockCode ;

    /**
     * 股票名称
     */
    @TableField("stock_name")
    private String stockName ;

    /**
     * 交易所
     */
    @TableField("stock_market")
    private String stockMarket ;

    /**
     * 行业
     */
    @TableField("stock_sector")
    private String stockSector;

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockMarket() {
        return stockMarket;
    }

    public void setStockMarket(String stockMarket) {
        this.stockMarket = stockMarket;
    }

    public String getStockSector() {
        return stockSector;
    }

    public void setStockSector(String stockSector) {
        this.stockSector = stockSector;
    }
}
