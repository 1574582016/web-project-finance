package com.sky.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.sky.core.model.BaseModel;

/**
 * Created by ThinkPad on 2019/5/27.
 */
@TableName("stock_company_analyse")
public class StockCompanyAnalyse extends BaseModel<StockCompanyAnalyse> {

    /**
     * 股票编码
     */
    @TableField("stock_code")
    private String stockCode ;

    /**
     * 经营要点
     */
    @TableField("operate_point")
    private String operatePoint ;

    /**
     * 要点分析
     */
    @TableField("point_analyse")
    private String pointAnalyse ;

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getOperatePoint() {
        return operatePoint;
    }

    public void setOperatePoint(String operatePoint) {
        this.operatePoint = operatePoint;
    }

    public String getPointAnalyse() {
        return pointAnalyse;
    }

    public void setPointAnalyse(String pointAnalyse) {
        this.pointAnalyse = pointAnalyse;
    }
}
